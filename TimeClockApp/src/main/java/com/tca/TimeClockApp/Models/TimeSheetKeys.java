package com.tca.TimeClockApp.Models;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimeSheetKeys implements Serializable {
    //This class is used to handle the composite keys for the TimeSheet class
    private String emp_custom_id;
    private int time_sheet_id;
    private Timestamp time_submitted;
}
