/*
These stored procedures are used by the timesheet table to get more specific results from the DB.
I recommend loading each stored procedure in individually to confirm they have been loaded in correctly
*/

-- Used to get the latest time sheet entry and only that entry for a specified employee Id
delimiter //
CREATE PROCEDURE sp_SelectRecentTSEntryById(IN empId varchar(10))
BEGIN
	SELECT * FROM time_sheet WHERE emp_custom_id = empId ORDER BY time_submitted DESC LIMIT 1;
END;

-- Used to return an ordered list of time sheet entries specified by employee Id
delimiter //
CREATE PROCEDURE sp_SelectEntriesById(IN empId varchar(10))
BEGIN
	SELECT * FROM time_sheet WHERE emp_custom_id = empId ORDER BY time_submitted;
END;

-- Used for JUnit tests; clears out any time sheet entries created for JUnit tests
delimiter //
CREATE PROCEDURE sp_DeleteTSEntriesById(IN empId varchar(10))
BEGIN
	DELETE FROM time_sheet WHERE emp_custom_id = empId;
END;