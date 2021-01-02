package javaproject.BusinessApplication.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrator extends User {

    @Column(unique = true)
    private String email;


    public Administrator() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}