package javaproject.BusinessApplication.web.models;

import java.time.LocalDate;
import java.util.Date;

public class DateModel {

    String dateFrom;
    String dateTo;

    public DateModel() {
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
