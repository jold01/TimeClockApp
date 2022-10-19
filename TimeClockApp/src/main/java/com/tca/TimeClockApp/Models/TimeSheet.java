package com.tca.TimeClockApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TimeSheetKeys.class)
public class TimeSheet {

    //This class maps to time_sheet table in DB
    //This uses TimeSheetKeys to handle the composite keys

    @Id
    private String emp_custom_id;

    @Id
    private int time_sheet_id;

    @Id
    private Timestamp time_submitted;
}
