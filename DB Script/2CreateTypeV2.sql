-- CONNECT WITH BASKET_USER@BASKET_PWD
DROP TYPE T_MATCH FORCE;
DROP TYPE T_SCOREDETAILS FORCE;
DROP TYPE T_SCOREDETAIL FORCE;
DROP TYPE T_CLUB_PLAYER FORCE;
DROP TYPE T_TEAM_COACH FORCE;
DROP TYPE T_COACH FORCE;
DROP TYPE T_PLAYERS FORCE;
DROP TYPE T_PLAYER FORCE;
DROP TYPE T_TEAMS FORCE;
DROP TYPE T_TEAM FORCE;
DROP TYPE T_CATEGORIES FORCE;
DROP TYPE T_CATEGORY FORCE;
DROP TYPE T_CLUB FORCE;
DROP TYPE T_SECRETARY FORCE;
DROP TYPE T_TREASURER FORCE;
DROP TYPE T_VICE_PRESIDENT FORCE;
DROP TYPE T_PRESIDENT FORCE;
DROP TYPE T_PERSON FORCE;
DROP TYPE T_OFFICE FORCE;
DROP TYPE T_ADDRESS FORCE;

CREATE OR REPLACE TYPE T_ADDRESS AS OBJECT
(
	ADDRESS_NUMBER VARCHAR(50),
	ADDRESS_STREET VARCHAR(50),
	ADDRESS_DISTRICT VARCHAR(50),
	ADDRESS_CITY VARCHAR(50)
)
/
CREATE OR REPLACE TYPE T_OFFICE AS OBJECT
(
	OFFICE_ID INT,
	OFFICE_NAME VARCHAR (50),
	OFFICE_ADDRESS T_ADDRESS
)
/
CREATE OR REPLACE TYPE T_PERSON AS OBJECT
(
	PERSON_ID INT,
	PERSON_NAME VARCHAR(50)
) NOT FINAL NOT INSTANTIABLE
/
CREATE OR REPLACE TYPE T_PRESIDENT UNDER T_PERSON()
/
CREATE OR REPLACE TYPE T_VICE_PRESIDENT UNDER T_PERSON()
/
CREATE OR REPLACE TYPE T_TREASURER UNDER T_PERSON()
/
CREATE OR REPLACE TYPE T_SECRETARY UNDER T_PERSON()
/
CREATE OR REPLACE TYPE T_PLAYER UNDER T_PERSON
(
	PLAYER_LICENCE_NUMBER VARCHAR(50),
	PLAYER_BIRTHDAY DATE,
	PLAYER_ADDRESS T_ADDRESS
)
/
CREATE TYPE T_PLAYERS AS TABLE OF T_PLAYER;
/


CREATE OR REPLACE TYPE T_COACH1;
/
CREATE OR REPLACE TYPE T_TEAM1 AS OBJECT
(
	TEAM_ID INT,
	TEAM_NAME VARCHAR(50),
	LISTPLAYER T_PLAYERS,
	TEAM_COACH REF T_COACH
)
/
DROP TYPE T_COACH1 FORCE;
/
CREATE OR REPLACE TYPE T_COACH1 UNDER T_PERSON
(
	COACH_TEAM REF T_TEAM1
)
/
CREATE TABLE TEAM1 OF T_TEAM1
(
	TEAM_ID NOT NULL,
	TEAM_NAME NOT NULL,
	LISTPLAYER NULL,
	TEAM_COACH NULL,
	PRIMARY KEY (TEAM_ID)
)
NESTED TABLE LISTPLAYER STORE AS NT_TEAM_PLAYER1
/


CREATE TYPE T_TEAMS AS TABLE OF T_TEAM;
/
CREATE OR REPLACE TYPE T_CATEGORY AS OBJECT
(
	CATEGORY_ID INT,
	CATEGORY_NAME VARCHAR(50),
	LISTTEAM T_TEAMS
)
/
CREATE TYPE T_CATEGORIES AS TABLE OF T_CATEGORY
/
CREATE OR REPLACE TYPE T_CLUB AS OBJECT
(
	CLUB_ID INT,
	CLUB_NAME VARCHAR(50),
	CLUB_OFFICE REF T_OFFICE,
	CLUB_PRESIDENT REF T_PRESIDENT,
	CLUB_VICE_PRESIDENT REF T_VICE_PRESIDENT,
	CLUB_TREASURER REF T_TREASURER,
	CLUB_SECRETARY REF T_SECRETARY,
	LISTCATEGORY T_CATEGORIES
)
/
CREATE OR REPLACE TYPE T_COACH UNDER T_PERSON
(
	COACH_TEAM T_TEAMS
);
/

/
CREATE OR REPLACE TYPE T_CLUB_PLAYER AS OBJECT
(
	ID INT,
	CLUB REF T_CLUB,
	PLAYER REF T_PLAYER,
	JOINED_DATE DATE
)
/
CREATE OR REPLACE TYPE T_SCOREDETAIL AS OBJECT
(
	ID INT,
	TEAM REF T_TEAM,
	PLAYER REF T_PLAYER,
	VALUE INT
)
/
CREATE TYPE T_SCOREDETAILS AS TABLE OF T_SCOREDETAIL;
/
CREATE OR REPLACE TYPE T_MATCH AS OBJECT
(
	MATCH_ID INT,
	MATCH_DATE DATE,
	DETAILS T_SCOREDETAILS
)
/