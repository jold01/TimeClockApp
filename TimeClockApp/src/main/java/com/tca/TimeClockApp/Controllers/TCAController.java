package com.tca.TimeClockApp.Controllers;


import com.tca.TimeClockApp.Models.Employee;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetTypeRepository;
import com.tca.TimeClockApp.Services.TCAService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TCAController {

    @Autowired
    private TCAService tcaService;


    @GetMapping(path="/getallemployees")
    public @ResponseBody Iterable<Employee> getAllEmployees(){
        return tcaService.getAllEmployees();
    }
}
