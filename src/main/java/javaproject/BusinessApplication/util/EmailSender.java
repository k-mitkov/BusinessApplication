package javaproject.BusinessApplication.util;

import javaproject.BusinessApplication.data.entities.Product;

public interface EmailSender {

    void send(String email, Product product);
}
