package com.tca.TimeClockApp.Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeSheetKeys implements Serializable {
    //This class is used to handle the composite keys for the TimeSheet class
    private String emp_custom_id;
    private int time_sheet_id;
    private LocalDateTime time_submitted;
}
