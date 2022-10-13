package com.tca.TimeClockApp.Controllers;


import com.tca.TimeClockApp.Models.Employee;
import com.tca.TimeClockApp.Models.Response;
import com.tca.TimeClockApp.Models.TimeSheetType;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetTypeRepository;
import com.tca.TimeClockApp.Services.TCAService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class TCAController {

    @Autowired
    private TCAService tcaService;


    @GetMapping(path="/getallemployees")
    public @ResponseBody Iterable<Employee> getAllEmployees(){
        return tcaService.getAllEmployees();
    }

    @GetMapping(path="/getallshifttypes")
    public @ResponseBody Iterable<TimeSheetType> getAllShiftTypes(){
        return tcaService.getAllTimeShiftTypes();
    }

    @PostMapping(path="/createNewEmployee")
    public @ResponseBody Response createNewEmployee(@RequestBody Employee newEmp){
        return tcaService.createNewEmployee(newEmp);
    }
}
