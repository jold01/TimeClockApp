package com.tca.TimeClockApp.Controllers;


import com.tca.TimeClockApp.Models.*;
import com.tca.TimeClockApp.Services.TCAService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
public class TCAController {

    @Autowired
    private TCAService tcaService;

    //Gets a list of all employees in employee table
    @GetMapping(path="/getallemployees")
    public @ResponseBody Iterable<Employee> getAllEmployees(){
        return tcaService.getAllEmployees();
    }

    //Returns all the types of shifts in the time_sheet_type table
    @GetMapping(path="/getallshifttypes")
    public @ResponseBody Iterable<TimeSheetType> getAllShiftTypes(){
        return tcaService.getAllTimeShiftTypes();
    }

    //Generates a new employee entry in the employee table
    @PostMapping(path="/createNewEmployee")
    public @ResponseBody Response createNewEmployee(@RequestBody Employee newEmp){
        return tcaService.createNewEmployee(newEmp);
    }

    //Allows users to submit a new timesheet entry
    @PostMapping(path="/insertTimeSheetEntry")
    public @ResponseBody Response newTimeSheetEntry(@RequestParam String emp_custom_id, int time_sheet_type_id){
        return tcaService.insertTimeSheetEntry(emp_custom_id, time_sheet_type_id);

    }

    @GetMapping(path="/getLatestTimeSheetById")
    public @ResponseBody TimeSheet getLatestTSById(@RequestParam String emp_custom_id){
        return tcaService.getLatestTSById(emp_custom_id);
    }

    @GetMapping(path="/getEntriesById")
    public @ResponseBody TSResponse getEntriesById(@RequestParam String emp_custom_id){
        return tcaService.getEntriesById(emp_custom_id);
    }


}
