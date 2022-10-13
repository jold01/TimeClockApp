package com.tca.TimeClockApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@IdClass(TimeSheetKeys.class)
public class TimeSheet {

    @Id
    private String emp_custom_id;

    @Id
    private String time_sheet_id;

    private LocalDateTime time_submitted;
}
