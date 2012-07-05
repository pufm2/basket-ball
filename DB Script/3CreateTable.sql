DROP TABLE MATCH CASCADE CONSTRAINT;
DROP TABLE SEASON CASCADE CONSTRAINT;
DROP TABLE CLUB_PLAYER CASCADE CONSTRAINT;
DROP TABLE TEAM_COACH CASCADE CONSTRAINT;
DROP TABLE COACH CASCADE CONSTRAINT;
DROP TABLE CLUB CASCADE CONSTRAINT;
DROP TABLE CATEGORY CASCADE CONSTRAINT;
DROP TABLE TEAM CASCADE CONSTRAINT;
DROP TABLE PLAYER CASCADE CONSTRAINT;
DROP TABLE SECRETARY CASCADE CONSTRAINT;
DROP TABLE TREASURER CASCADE CONSTRAINT;
DROP TABLE VICE_PRESIDENT CASCADE CONSTRAINT;
DROP TABLE PRESIDENT CASCADE CONSTRAINT;
DROP TABLE OFFICE CASCADE CONSTRAINT;
DROP TABLE USERBASKET CASCADE CONSTRAINT;

CREATE TABLE USERBASKET OF T_USER
(
	USERNAME NOT NULL,
	PASSWORD NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE OFFICE OF T_OFFICE
(
	ID NOT NULL,
	OFFICE_NAME NOT NULL,
	OFFICE_ADDRESS NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE PRESIDENT OF T_PRESIDENT
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE VICE_PRESIDENT OF T_VICE_PRESIDENT
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE TREASURER OF T_TREASURER
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE SECRETARY OF T_SECRETARY
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE PLAYER OF T_PLAYER
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PLAYER_LICENCE_NUMBER NOT NULL,
	PLAYER_BIRTHDAY NOT NULL,
	PLAYER_ADDRESS NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE TEAM OF T_TEAM
(
	ID NOT NULL,
	TEAM_NAME NOT NULL,
	LISTPLAYER NULL,
	PRIMARY KEY (ID)
)
NESTED TABLE LISTPLAYER STORE AS NT_TEAM_PLAYER
/
CREATE TABLE CATEGORY OF T_CATEGORY
(
	ID NOT NULL,
	CATEGORY_NAME NOT NULL,
	LISTTEAM NULL,
	PRIMARY KEY (ID)
)
NESTED TABLE LISTTEAM STORE AS NT_CATEGORY_TEAM
(NESTED TABLE LISTPLAYER STORE AS NT_CATEGORY_PLAYERS)
/
CREATE TABLE CLUB OF T_CLUB
(
	ID NOT NULL,
	CLUB_NAME NOT NULL,
	CLUB_OFFICE NOT NULL,
	CLUB_PRESIDENT NOT NULL,
	CLUB_VICE_PRESIDENT NOT NULL,
	CLUB_TREASURER NOT NULL,
	CLUB_SECRETARY NOT NULL,
	LISTCATEGORY NULL,
	PRIMARY KEY (ID)
)
NESTED TABLE LISTCATEGORY STORE AS NT_CLUB_CATEGORY
	(NESTED TABLE LISTTEAM STORE as NT_CLUB_TEAM
		(NESTED TABLE LISTPLAYER STORE AS NT_CLUB_PLAYER))
/
CREATE TABLE COACH OF T_COACH
(
	ID NOT NULL,
	PERSON_NAME NOT NULL,
	PRIMARY KEY (ID)
)
/
CREATE TABLE TEAM_COACH OF T_TEAM_COACH
(
	ID NOT NULL,
	TEAM NOT NULL,
	COACH NOT NULL,
	STARTDATE DEFAULT SYSDATE,
	ENDDATE DEFAULT SYSDATE + 365,
	PRIMARY KEY (ID)
)
/
CREATE TABLE CLUB_PLAYER OF T_CLUB_PLAYER
(
	ID NOT NULL,
	CLUB NOT NULL,
	PLAYER NOT NULL,
	JOINED_DATE DEFAULT SYSDATE,
	PRIMARY KEY (ID)
)
/
CREATE TABLE SEASON OF T_SEASON
(
	ID NOT NULL,
	PRIMARY KEY(ID)
)
/
CREATE TABLE MATCH OF T_MATCH
(
	ID NOT NULL,
	MATCH_DATE DEFAULT SYSDATE,
	SEASON NOT NULL,
	DETAILS NULL,
	PRIMARY KEY (ID)
)
NESTED TABLE DETAILS STORE AS NT_MATCH_DETAILS
/

