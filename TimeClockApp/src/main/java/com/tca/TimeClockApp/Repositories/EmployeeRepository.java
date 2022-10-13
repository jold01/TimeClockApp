package com.tca.TimeClockApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.tca.TimeClockApp.Models.Employee;

public interface EmployeeRepository  extends CrudRepository<Employee, String> {

}
