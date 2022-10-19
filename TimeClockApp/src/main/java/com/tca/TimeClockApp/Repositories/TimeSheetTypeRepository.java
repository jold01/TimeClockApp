package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheetType;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetTypeRepository extends CrudRepository<TimeSheetType, Integer> {
    //Used to interface with the DB connectivity in the Time_Sheet_Type table

}
