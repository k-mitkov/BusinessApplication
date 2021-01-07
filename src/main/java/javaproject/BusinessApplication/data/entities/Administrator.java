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


    @Override
    public String toString() {
        return "Administrator: " + this.getUsername() +
                "\n     First name: " + this.getFirstName() +
                "\n     Last name: " + this.getLastName() +
                "\n     Email: " + this.getEmail() +
                "\n";
    }
}