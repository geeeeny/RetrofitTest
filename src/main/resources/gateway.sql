DROP TABLE SENSORS;

CREATE TABLE SENSORS( 
	sensor_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
	name TEXT,
	value REAL, 
	type TEXT NOT NULL, 
	location TEXT, 
	reg_date TEXT 
); 

select * from SENSORS; 

commit;