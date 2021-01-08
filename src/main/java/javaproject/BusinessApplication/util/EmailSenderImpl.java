package javaproject.BusinessApplication.util;

import com.sun.mail.smtp.SMTPTransport;
import javaproject.BusinessApplication.data.entities.Product;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSenderImpl implements EmailSender {

    private static final String SMTP_SERVER = "smtp.abv.bg";
    private static final String USERNAME = "no-reply.techno_world@abv.bg";
    private static final String PASSWORD = System.getenv("EMAIL_PASSWORD");

    private static final String DECREASING_SUBJECT = "Product with decreasing quantity";
    private static final String EXHAUSTED_SUBJECT = "Product with exhausted quantity";

    @Override
    public void send(String email, Product product){

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.ssl.enable", true);

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(USERNAME));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));

            if (product.getQuantity()==0){
                msg.setSubject(EXHAUSTED_SUBJECT);
                msg.setText(String.format("Product '%s' is out of stock!"
                        ,product.getType()+" "+product.getModel()));
            }else {
                msg.setSubject(DECREASING_SUBJECT);
                msg.setText(String.format("Product '%s' has a reduced quantity: %d!"
                        ,product.getType()+" "+product.getModel(),product.getQuantity()));
            }

            msg.setSentDate(new Date());
            MyThread myThread=new MyThread(session,msg);
            myThread.start();
        } catch (MessagingException ignored) {
        }
    }

    private class MyThread extends Thread {
        private final Session session;
        private final Message msg;

        private MyThread(Session session,Message msg){
            this.session=session;
            this.msg=msg;
        }


        public void run() {
            try {
                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
                t.connect(SMTP_SERVER, USERNAME, PASSWORD);
                t.sendMessage(msg, msg.getAllRecipients());
                t.close();
            } catch (MessagingException ignored) {
            }
        }
    }
}

