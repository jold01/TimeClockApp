package com.tca.TimeClockApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSResponse {
//This class is used to as a response object to give the client a list of timesheet entries and the total hours calculated for those hours

    private List<TimeSheet> timeSheetResults;

    private String totalHours;

}
