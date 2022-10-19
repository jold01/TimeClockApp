package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheet;
import com.tca.TimeClockApp.Models.TimeSheetKeys;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, TimeSheetKeys> {
    //Used to interface with the DB connectivity in the Time_Sheet table

    //Used to get the latest time sheet entry and only that entry for a specified employee Id
    @Procedure
    TimeSheet sp_SelectRecentTSEntryById(String emp_custom_id);

    //Used to return an ordered list of time sheet entries specified by employee Id
    @Procedure
    List<TimeSheet> sp_SelectEntriesById(String emp_custom_id);

    //Used for JUnit tests; clears out any time sheet entries created for JUnit tests
    @Procedure
    void sp_DeleteTSEntriesById(String emp_custom_id);

}
