
-- Queries to view entries in each table
SELECT * FROM employee;

SELECT * FROM time_sheet_type;

SELECT * FROM time_sheet;

-- Query to see how many time sheet entries are for a specified employee id
SELECT COUNT(emp_custom_id) FROM time_sheet WHERE emp_custom_id = "";