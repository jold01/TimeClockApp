package com.tca.TimeClockApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSResponse {

    private List<TimeSheet> timeSheetResults;

    private double totalHours;

}
