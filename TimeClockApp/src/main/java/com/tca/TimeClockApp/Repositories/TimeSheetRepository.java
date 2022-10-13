package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheet;
import com.tca.TimeClockApp.Models.TimeSheetKeys;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, TimeSheetKeys> {
}
