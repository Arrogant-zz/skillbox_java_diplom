package main.data.request;

import lombok.Data;

import java.util.Calendar;

@Data
public class CalendarRequest {
    private int year = Calendar.getInstance().get(Calendar.YEAR);
}
