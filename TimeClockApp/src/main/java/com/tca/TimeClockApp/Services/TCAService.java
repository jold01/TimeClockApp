package com.tca.TimeClockApp.Services;
import com.tca.TimeClockApp.Models.*;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetTypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final double SECONDS_FOR_HOURS = 3600.00;



    public Iterable<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Iterable<TimeSheetType> getAllTimeShiftTypes(){ return timeSheetTypeRepository.findAll();}

    public boolean checkEmployeeId(String empId){
       return employeeRepository.existsById(empId);
    }

    public Response createNewEmployee(Employee newEmp){
        //Checks if the employee Id submitted already exists in DB
        if(!checkEmployeeId(newEmp.getEmp_custom_id())){
            //Save employee object to DB and give success response object
            employeeRepository.save(newEmp);
            return new Response(1);
        }else{
            //Returns failed action response due to id already in DB
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


            //Generate current entry time
            LocalDateTime ldt = LocalDateTime.now(ZoneOffset.UTC);



            //Gets list of time sheet entries for submitted id to see if there are any submissions
            //Already made
            List<TimeSheet> lts = timeSheetRepository.sp_SelectEntriesById(emp_custom_id);
            System.out.println("LIST SIZE: " + lts.size());


            //Condition for first time submission to have to be "start shift"
            if(lts.size() == 0){


                if(time_sheet_type_id == 1){
                    //Enter Entry into DB
                    timeSheetRepository.save(new TimeSheet(emp_custom_id, time_sheet_type_id, ldt));
                    return new Response(1);
                }
                else{
                    return new Response(2);
                }
            }else {
                //Get latest timesheet entry from DB by emp_ID and check time_sheet_type_id to
                //Using MySQL stored procedure
                int latestTSType = timeSheetRepository.sp_SelectRecentTSEntryById(emp_custom_id).getTime_sheet_id();
                //see if submitted id and stored id are the same. If they are, reject request
                if (latestTSType == time_sheet_type_id) {
                    return new Response(2);
                } else {
                    //Enter Entry into DB
                    timeSheetRepository.save(new TimeSheet(emp_custom_id, time_sheet_type_id, ldt));
                    return new Response(1);
                }
            }

        }

    }

//    @Transactional
//    public TimeSheet getLatestTSById(String empId){
//        if(timeSheetRepository.sp_SelectRecentTSEntryById(empId) == null){
//            System.out.println("OBJECT IS NULL **************");
//        }
//        return timeSheetRepository.sp_SelectRecentTSEntryById(empId);
//    }

    @Transactional
    public TSResponse getEntriesById(String empId){
        //This method is used to retrieve all time sheet entries submitted by a specified employee Id
        //It calculates the total number of hours from those time sheet entries
        //It returns a TSResponse object

        //Calls SQL stored procedure to get all time sheet entry objects and put them in a list
        List<TimeSheet> entries = timeSheetRepository.sp_SelectEntriesById(empId);

        //The variable that stores each date duration comparison in seconds
        int timeInSeconds = 0;


        for(int i=0; i<entries.size(); i++){
            //Takes the LDT of the current index, and compares with the next
            //index if the current index id is 1 (Shift start) and that the next index exists
            LocalDateTime cdate = entries.get(i).getTime_submitted();
            System.out.println(entries.get(i));
            if(i+1 < entries.size() && entries.get(i).getTime_sheet_id() == 1){
                LocalDateTime ndate = entries.get(i+1).getTime_submitted();

                //Method that gets the duration between the two dates
                Duration duration = Duration.between(cdate, ndate);

                //Appends the duration time to the total time in seconds variable
                timeInSeconds += duration.getSeconds();

            }

        }
        //Converts the seconds into hours
        double timeInHours = timeInSeconds/SECONDS_FOR_HOURS;

        //Returns the list of entries and a formatted floating point of calculated hours
        return new TSResponse(entries, String.format("%.3f", timeInHours ));
    }




}
