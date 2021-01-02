package javaproject.BusinessApplication.data.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity extends BaseEntity{

    private String description;
    private Date date;

    public Activity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
