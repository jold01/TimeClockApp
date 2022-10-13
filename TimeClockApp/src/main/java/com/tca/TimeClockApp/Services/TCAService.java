package com.tca.TimeClockApp.Services;
import com.tca.TimeClockApp.Controllers.TCAController;
import com.tca.TimeClockApp.Models.*;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetTypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@NoArgsConstructor
public class TCAService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Autowired
    private TimeSheetTypeRepository timeSheetTypeRepository;



    public Iterable<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Iterable<TimeSheetType> getAllTimeShiftTypes(){ return timeSheetTypeRepository.findAll();}

    public boolean checkEmployeeId(String empId){
       return employeeRepository.existsById(empId);
    }

    public Response createNewEmployee(Employee newEmp){
        if(!checkEmployeeId(newEmp.getEmp_custom_id())){
            employeeRepository.save(newEmp);
            return new Response(1);
        }else{
            return new Response(2);
        }
    }




}
