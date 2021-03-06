-- CONNECT WITH BASKET_USER@BASKET_PWD
DROP TYPE T_LOCATION FORCE;
DROP TYPE T_MATCH FORCE;
DROP TYPE T_SEASON FORCE;
DROP TYPE T_SCOREDETAILS FORCE;
DROP TYPE T_SCOREDETAIL FORCE;
DROP TYPE T_TEAM_WITH_PLAYER FORCE;
DROP TYPE T_COACH FORCE;
DROP TYPE T_TEAM_WITH_COACH FORCE;
DROP TYPE T_PLAYER FORCE;
DROP TYPE T_TEAM_MANY FORCE;
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
DROP TYPE T_USER FORCE;

CREATE OR REPLACE TYPE T_LOCATION AS OBJECT
(
	ID INT,
    loc SDO_GEOMETRY,
    DELETED INT,
    MEMBER FUNCTION distance(locObj IN T_LOCATION) RETURN number
) not final
/
CREATE OR REPLACE TYPE BODY T_LOCATION AS
  MEMBER FUNCTION distance(locObj T_LOCATION) RETURN NUMBER IS
    d number;
    BEGIN
      select SDO_GEOM.SDO_DISTANCE(self.loc, locObj.loc, 1, 'unit=km') into d from dual;
      return d;
    END;
END;
/
CREATE OR REPLACE TYPE T_USER AS OBJECT
(
	ID INT,
	USERNAME VARCHAR(50),
	PASSWORD VARCHAR(50),
	DELETED INT
)
/
CREATE OR REPLACE TYPE T_ADDRESS AS OBJECT
(
	ADDRESS_NUMBER VARCHAR(50),
	ADDRESS_STREET VARCHAR(50),
	ADDRESS_DISTRICT VARCHAR(50),
	ADDRESS_CITY VARCHAR(50)
)
/
CREATE OR REPLACE TYPE T_OFFICE UNDER T_LOCATION
(
	OFFICE_NAME VARCHAR (50),
	OFFICE_ADDRESS T_ADDRESS  
)
/
CREATE OR REPLACE TYPE T_PERSON AS OBJECT
(
	ID INT,
	PERSON_NAME VARCHAR(50),
  DELETED INT
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
CREATE OR REPLACE TYPE T_TEAM AS OBJECT
(
	ID INT,
	TEAM_NAME VARCHAR(50),
  DELETED INT
)
/
CREATE TYPE T_TEAMS AS TABLE OF T_TEAM;
/
CREATE OR REPLACE TYPE T_CATEGORY AS OBJECT
(
	ID INT,
	CATEGORY_NAME VARCHAR(50),
	LISTTEAM T_TEAMS,
  DELETED INT
)
/
CREATE TYPE T_CATEGORIES AS TABLE OF T_CATEGORY
/
CREATE OR REPLACE TYPE T_CLUB AS OBJECT
(
	ID INT,
	CLUB_NAME VARCHAR(50),
	CLUB_OFFICE REF T_OFFICE,
	CLUB_PRESIDENT REF T_PRESIDENT,
	CLUB_VICE_PRESIDENT REF T_VICE_PRESIDENT,
	CLUB_TREASURER REF T_TREASURER,
	CLUB_SECRETARY REF T_SECRETARY,
	LISTCATEGORY T_CATEGORIES,
  DELETED INT
)
/
CREATE OR REPLACE TYPE T_TEAM_MANY AS OBJECT -- MANY - MANY TABLE
(
	MANY_TEAM REF T_TEAM,
	START_DATE DATE,
	END_DATE DATE
) NOT FINAL;
/
CREATE OR REPLACE TYPE T_TEAM_WITH_COACH AS TABLE OF T_TEAM_MANY; -- INNER TABLE
/
CREATE OR REPLACE TYPE T_COACH UNDER T_PERSON
(
	TEAM_WITH_COACH T_TEAM_WITH_COACH -- STORE ALL TEAM OF THIS COACH
) NOT FINAL;
/
CREATE OR REPLACE TYPE T_TEAM_WITH_PLAYER AS TABLE OF T_TEAM_MANY; -- INNER TABLE
/
CREATE OR REPLACE TYPE T_PLAYER UNDER T_PERSON
(
	PLAYER_LICENCE_NUMBER VARCHAR(50),
	PLAYER_BIRTHDAY DATE,
	PLAYER_ADDRESS T_ADDRESS,
	TEAM_WITH_PLAYERS T_TEAM_WITH_PLAYER -- STORE ALL TEAM OF THIS PLAYER
) NOT FINAL;
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
CREATE OR REPLACE TYPE T_SEASON AS OBJECT
(
	ID INT,
  SEASON_NAME VARCHAR(50),
	SEASON_STARTDATE DATE,
	SEASON_ENDDATE DATE,
  DELETED INT
)
/
CREATE OR REPLACE TYPE T_MATCH AS OBJECT
(
	ID INT,
	MATCH_DATE DATE,
	TEAM1 REF T_TEAM,
	TEAM2 REF T_TEAM,
	SEASON REF T_SEASON,
	DETAILS T_SCOREDETAILS,
  DELETED INT
)
/
COMMIT;