-- Generate the Employee Table
CREATE TABLE Employee(
	emp_custom_id varchar(10) NOT NULL PRIMARY KEY,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL
);
-- Generate the Time Sheet Types table
CREATE TABLE Time_Sheet_Type(
	time_sheet_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name varchar(25)
);
-- Generate the Time Sheet table
CREATE TABLE Time_Sheet(
	emp_custom_id varchar(10) NOT NULL,
    time_sheet_id INT NOT NULL,
    time_submitted TIMESTAMP,
    PRIMARY KEY(emp_custom_id, time_sheet_id, time_submitted),
    FOREIGN KEY (emp_custom_id) REFERENCES Employee(emp_custom_id),
    FOREIGN KEY (time_sheet_id) REFERENCES Time_Sheet_Type(time_sheet_id)
    
);