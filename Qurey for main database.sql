--creating table APPOINTMENTS
CREATE TABLE APPT
(APPTID INT IDENTITY 
,PERSONID INT
,EVENTNAME VARCHAR(50)
,REASON VARCHAR(250)
,START_TIME DATETIME2
,END_TIME DATETIME2
,COLOR VARCHAR(50)
)
--creating table FOR PERSON ID
CREATE TABLE PERSON
(PERSONID INT IDENTITY 
, LNAME VARCHAR(50)
,FNAME VARCHAR(50)
,LOGIN_NAME VARCHAR(25)
,H_PASS VARCHAR(50)
)
--creating primary keys so events and calander have a unique key
alter table APPT add primary key (APPTID)
alter table PERSON add primary key (PERSONID)

--testing person insert
INSERT PERSON
(
LNAME
,FNAME
,LOGIN_NAME
,H_PASS
)
SELECT 
'COHOON'
,'KALEB'
,'CDC'
,'juh7WNJD6NS'

--testing retrieving data
SELECT * FROM PERSON

--testing inserting appointment
INSERT APPT
(PERSONID
,EVENTNAME
,REASON
,START_TIME
,END_TIME
,COLOR
)
SELECT 
2
,'WASH CLOTHES'
,'WASH CLOTHES AT LAUNDRY MATT'
,GETDATE()
,GETDATE()
,'RED'
--testing retreriving data
SELECT * FROM APPT

--testing retriving data from appt that matches user id
SELECT 
P.FNAME, P.LNAME, A.REASON, A.START_TIME
FROM PERSON P
	JOIN APPT A
		ON P.PERSONID = A.PERSONID





 
--Testing code for deleting test appts
--DELETE APPT WHERE PERSONID=?