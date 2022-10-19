package com.tca.TimeClockApp;

import com.tca.TimeClockApp.Models.Employee;
import com.tca.TimeClockApp.Models.Response;
import com.tca.TimeClockApp.Models.TSResponse;
import com.tca.TimeClockApp.Models.TimeSheet;
import com.tca.TimeClockApp.Repositories.EmployeeRepository;
import com.tca.TimeClockApp.Repositories.TimeSheetRepository;
import com.tca.TimeClockApp.Services.TCAService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TimeClockAppApplicationTests {

	@Autowired
	EmployeeRepository eRepo;

	@Autowired
	TimeSheetRepository tsRepo;


	@Autowired
	TCAService tcaService;


	@Test
	void contextLoads() {
	}
	//Test to confirm that creating a new employee can be successfully added to the DB
	@Test
	void testNewUniqueEmployee(){
		Employee testEmp = new Employee();
		testEmp.setEmp_custom_id("testEmp1");
		testEmp.setFirst_name("fname");
		testEmp.setLast_name("lname");

		assertEquals(new Response(1), tcaService.createNewEmployee(testEmp));
	}
	//Test to confirm creating a new employee with the same employee id gets rejected
	@Test
	void testRejectSameEmployeeInsertion(){
		Employee testEmp = new Employee();
		testEmp.setEmp_custom_id("testEmp1");
		testEmp.setFirst_name("fname");
		testEmp.setLast_name("lname");

		assertEquals(new Response(3), tcaService.createNewEmployee(testEmp));
	}

	//Confirming that the calculation for total hours worked is correct
	@Test
	void testTotalShiftCalculation(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//Create TimeSheet Objects
		TimeSheet ts1 = new TimeSheet();
		ts1.setEmp_custom_id("testEmp1");
		ts1.setTime_sheet_id(1);
		ts1.setTime_submitted(LocalDateTime.parse("2022-01-01 06:00:00", formatter));

		TimeSheet ts2 = new TimeSheet();
		ts2.setEmp_custom_id("testEmp1");
		ts2.setTime_sheet_id(2);
		ts2.setTime_submitted(LocalDateTime.parse("2022-01-02 06:00:00", formatter));

		TimeSheet ts3 = new TimeSheet();
		ts3.setEmp_custom_id("testEmp1");
		ts3.setTime_sheet_id(1);
		ts3.setTime_submitted(LocalDateTime.parse("2022-01-05 10:00:00", formatter));

		TimeSheet ts4 = new TimeSheet();
		ts4.setEmp_custom_id("testEmp1");
		ts4.setTime_sheet_id(2);
		ts4.setTime_submitted(LocalDateTime.parse("2022-01-06 10:00:00", formatter));

		//Submit timeSheet objects to DB
		tsRepo.save(ts1);
		tsRepo.save(ts2);
		tsRepo.save(ts3);
		tsRepo.save(ts4);

		//Run method to get total hours calculated
		TSResponse tsResponse = tcaService.getEntriesById("testEmp1");

		//Assert that the time is correct
		assertEquals("48.000", tsResponse.getTotalHours());
	}

	//Test to confirm ending a shift without starting one gets rejected
	@Test
	void testEndingShiftWithoutStarting(){
		//Create test Timesheet Object
		assertEquals(new Response(2), tcaService.insertTimeSheetEntry("testEmp1", 2));

	}

	//Test to confirm that starting a shift while before the previous shift was closed gets rejected
	@Test
	void testStartingShiftWithoutEnding(){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		//Create new timesheet object
		TimeSheet ts1 = new TimeSheet();
		ts1.setEmp_custom_id("testEmp1");
		ts1.setTime_sheet_id(1);
		ts1.setTime_submitted(LocalDateTime.parse("2022-01-07 06:00:00", formatter));

		//Save timesheet object to DB
		tsRepo.save(ts1);

		assertEquals(new Response(2), tcaService.insertTimeSheetEntry("testEmp1", 1));
	}

	//This is used to clean up all the test entries added to the DB
	@AfterAll
	@Transactional
	void cleanUpDB(){
		//Delete test timesheet entries
		tsRepo.sp_DeleteTSEntriesById("testEmp1");

		//Delete test Employee
		eRepo.deleteById("testEmp1");
		System.out.println("DONE");
	}

}
