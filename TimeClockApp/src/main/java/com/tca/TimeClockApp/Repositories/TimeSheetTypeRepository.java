package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheetType;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetTypeRepository extends CrudRepository<TimeSheetType, Integer> {

    //Create Stored Procedure to get the the most recent timestamp timesheet entry by emp_id
}
