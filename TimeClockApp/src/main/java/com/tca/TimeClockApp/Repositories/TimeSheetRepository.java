package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheet;
import com.tca.TimeClockApp.Models.TimeSheetKeys;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, TimeSheetKeys> {

    @Procedure
    TimeSheet sp_SelectRecentTSEntryById(String emp_custom_id);

    @Procedure
    List<TimeSheet> sp_SelectEntriesById(String emp_custom_id);

    @Procedure
    void sp_DeleteTSEntriesById(String emp_custom_id);
}
