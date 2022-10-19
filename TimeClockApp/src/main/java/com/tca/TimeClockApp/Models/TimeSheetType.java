package com.tca.TimeClockApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class TimeSheetType {
    //This class maps to the time_sheet_type table in DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String time_sheet_id;

    private String type_name;
}
