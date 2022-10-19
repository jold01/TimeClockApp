package com.tca.TimeClockApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.tca.TimeClockApp.Models.Employee;

public interface EmployeeRepository  extends CrudRepository<Employee, String> {
    //Used to interface with the DB connectivity in the Employee table
}
