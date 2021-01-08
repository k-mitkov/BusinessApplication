package javaproject.BusinessApplication.util;

import javaproject.BusinessApplication.exeptions.CustomTwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetSenderImpl implements TweetSender{

    private static final String EXCEPTION_MESSAGE=
            "Something went wrong! Please check your internet connection and try again.";

    private static final String CUSTOMER_KEY = System.getenv("CUSTOMER_KEY");
    private static final String CUSTOMER_SECRET = System.getenv("CUSTOMER_SECRET");
    private static final String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static final String ACCESS_TOKEN_SECRET = System.getenv("ACCESS_TOKEN_SECRET");


    @Override
    public void tweet(String message) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();

            twitter.setOAuthConsumer(CUSTOMER_KEY, CUSTOMER_SECRET);
            AccessToken accessToken = new AccessToken(TweetSenderImpl.ACCESS_TOKEN,
                    ACCESS_TOKEN_SECRET);
            twitter.setOAuthAccessToken(accessToken);
            twitter.updateStatus(message);

        } catch (TwitterException te) {
            throw new CustomTwitterException(EXCEPTION_MESSAGE);
        }

    }
}
