-- Used to Generate the Start and End shift Time Sheet Entry types ( Can add more for added functionality)
INSERT INTO time_sheet_type(
	type_name
)VALUES(
	"START SHIFT"
);

INSERT INTO time_sheet_type(
	type_name
)VALUES(
	"END SHIFT"
);

-- Test time sheet entry to add into time sheet table
INSERT INTO time_sheet(
	emp_custom_id,
    time_sheet_id,
    time_submitted
)VALUES(
	"jtest1",
    1,
    "2022-01-07 15:00:00"
);