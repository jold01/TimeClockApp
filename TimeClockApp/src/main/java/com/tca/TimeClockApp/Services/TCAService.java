package com.tca.TimeClockApp.Services;
import com.tca.TimeClockApp.Controllers.TCAController;
import com.tca.TimeClockApp.Models.*;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetTypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.*;

import java.util.List;

@Service
@NoArgsConstructor
public class TCAService {
    //This service is used to handle all business logic between the controller and the calls to DB

    //Initializing repo connections to make calls to DB
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Autowired
    private TimeSheetTypeRepository timeSheetTypeRepository;

    private final int SECONDS_FOR_HOURS = 3600;



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
            return new Response(3);
        }
    }
    @Transactional
    public Response insertTimeSheetEntry(String emp_custom_id, int time_sheet_type_id){


        //Check id is valid
        if(!checkEmployeeId(emp_custom_id)){
            return new Response(2);
        }
        else{
            //LocalDateTime ldt = LocalDateTime.now();

            //Generate current entry time

            //OLD IMPLEMENTATION
            Timestamp ts = Timestamp.from(Instant.now());
            System.out.println("TS: "+ ts);


            LocalDateTime ldt = LocalDateTime.now();
            //System.out.println("LDT: "+ ldt);
            ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
            Timestamp tsUTC = Timestamp.valueOf(zdt.withZoneSameInstant(ZoneId.of("GMT")).toLocalDateTime());

            System.out.println("TSUTC: "+ tsUTC);

            //Auto Gemerate time in SQL FIELD instead of sending time submission here

            //Convert time from Local to UTC before submission

            //Get latest timesheet entry from DB by emp_ID and check time_sheet_type_id to
            //Using MySQL stored procedure

            //Write Condition for first time submission to have to be "start shift"

            int latestTSType = timeSheetRepository.sp_SelectRecentTSEntryById(emp_custom_id).getTime_sheet_id();
            //see if submitted id and stored id are the same. If they are, reject request
            if(latestTSType == time_sheet_type_id){
                return new Response(2);
            }else{
                //Enter Entry into DB
                timeSheetRepository.save(new TimeSheet(emp_custom_id, time_sheet_type_id, tsUTC));
                return new Response(1);
            }


        }

    }

    @Transactional
    public TimeSheet getLatestTSById(String empId){
        if(timeSheetRepository.sp_SelectRecentTSEntryById(empId) == null){
            System.out.println("OBJECT IS NULL **************");
        }
        return timeSheetRepository.sp_SelectRecentTSEntryById(empId);
    }

    @Transactional
    public TSResponse getEntriesById(String empId){

        List<TimeSheet> entries = timeSheetRepository.sp_SelectEntriesById(empId);


        int timeInSeconds = 0;

        for(int i=0; i<entries.size(); i++){
            LocalDateTime cdate = entries.get(i).getTime_submitted().toLocalDateTime();
            System.out.println(entries.get(i));
            if(i+1 < entries.size() && entries.get(i).getTime_sheet_id() == 1){
                LocalDateTime ndate = entries.get(i+1).getTime_submitted().toLocalDateTime();

                Duration duration = Duration.between(cdate, ndate);

                timeInSeconds += duration.getSeconds();

            }

        }

        double timeInHours = timeInSeconds/SECONDS_FOR_HOURS;
        System.out.println(timeInHours + " HOURS");

        return new TSResponse(entries, timeInHours);
    }




}
