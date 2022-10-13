package com.tca.TimeClockApp.Repositories;

import com.tca.TimeClockApp.Models.TimeSheetType;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetTypeRepository extends CrudRepository<TimeSheetType, Integer> {
}
