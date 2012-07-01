﻿DELETE CLUB_PLAYER;
DELETE TEAM_COACH;
DELETE CLUB;
DELETE CATEGORY;
DELETE TEAM;
DELETE PLAYER;
DELETE COACH;
DELETE SECRETARY;
DELETE TREASURER;
DELETE VICE_PRESIDENT;
DELETE PRESIDENT;
DELETE OFFICE;

INSERT INTO OFFICE VALUES (1,'OFFICE 1',T_ADDRESS (2,'TAN VIEN','TAN BINH','HCMC'));
INSERT INTO OFFICE VALUES (2,'OFFICE 2',T_ADDRESS (13,'LOT 14 QTSP','12','HCMC'));
INSERT INTO OFFICE VALUES (3,'OFFICE 3',T_ADDRESS (93,'CAO THANG','3','HCMC'));
INSERT INTO OFFICE VALUES (4,'OFFICE 4',T_ADDRESS (1,'LOT 10 QTSP','12','HCMC'));
INSERT INTO OFFICE VALUES (5,'OFFICE 5',T_ADDRESS (8,'NGUYEN VAN TRANG','1','HCMC'));

INSERT INTO PRESIDENT VALUES (1,'PRESIDENT 1');
INSERT INTO PRESIDENT VALUES (2,'PRESIDENT 2');
INSERT INTO PRESIDENT VALUES (3,'PRESIDENT 3');
INSERT INTO PRESIDENT VALUES (4,'PRESIDENT 4');
INSERT INTO PRESIDENT VALUES (5,'PRESIDENT 5');

INSERT INTO VICE_PRESIDENT VALUES (1,'VICE_PRESIDENT 1');
INSERT INTO VICE_PRESIDENT VALUES (2,'VICE_PRESIDENT 2');
INSERT INTO VICE_PRESIDENT VALUES (3,'VICE_PRESIDENT 3');
INSERT INTO VICE_PRESIDENT VALUES (4,'VICE_PRESIDENT 4');
INSERT INTO VICE_PRESIDENT VALUES (5,'VICE_PRESIDENT 5');

INSERT INTO TREASURER VALUES (1,'TREASURER 1');
INSERT INTO TREASURER VALUES (2,'TREASURER 2');
INSERT INTO TREASURER VALUES (3,'TREASURER 3');
INSERT INTO TREASURER VALUES (4,'TREASURER 4');
INSERT INTO TREASURER VALUES (5,'TREASURER 5');

INSERT INTO SECRETARY VALUES (1,'SECRETARY 1');
INSERT INTO SECRETARY VALUES (2,'SECRETARY 2');
INSERT INTO SECRETARY VALUES (3,'SECRETARY 3');
INSERT INTO SECRETARY VALUES (4,'SECRETARY 4');
INSERT INTO SECRETARY VALUES (5,'SECRETARY 5');

INSERT INTO COACH VALUES (1,'COACH 1');
INSERT INTO COACH VALUES (2,'COACH 2');
INSERT INTO COACH VALUES (3,'COACH 3');
INSERT INTO COACH VALUES (4,'COACH 4');
INSERT INTO COACH VALUES (5,'COACH 5');

INSERT INTO PLAYER VALUES (1,'PLAYER 1','100001',TO_DATE('18/03/1980','DD/MM/YYYY'),T_ADDRESS ('4','PHAN TON','1','HCMC'));
INSERT INTO PLAYER VALUES (2,'PLAYER 2','200001',TO_DATE('18/06/1986','DD/MM/YYYY'),T_ADDRESS ('35','PHAN KE BINH','1','HCMC'));
INSERT INTO PLAYER VALUES (3,'PLAYER 3','300001',TO_DATE('21/05/1978','DD/MM/YYYY'),T_ADDRESS ('27/3A','TRAN NHAN TONG','BA DINH','HA NOI'));
INSERT INTO PLAYER VALUES (4,'PLAYER 4','400001',TO_DATE('12/09/1984','DD/MM/YYYY'),T_ADDRESS ('9/4','YET KIEU','HOAN KIEM','HA NOI'));
INSERT INTO PLAYER VALUES (5,'PLAYER 5','500001',TO_DATE('07/05/1981','DD/MM/YYYY'),T_ADDRESS ('18A','LE VAN SY','3','HCMC'));
INSERT INTO PLAYER VALUES (6,'PLAYER 6','600001',TO_DATE('18/03/1982','DD/MM/YYYY'),T_ADDRESS ('4','PHAN TON','1','HCMC'));
INSERT INTO PLAYER VALUES (7,'PLAYER 7','700001',TO_DATE('01/06/1988','DD/MM/YYYY'),T_ADDRESS ('35','PHAN KE BINH','1','HCMC'));
INSERT INTO PLAYER VALUES (8,'PLAYER 8','800001',TO_DATE('20/05/1979','DD/MM/YYYY'),T_ADDRESS ('27/3A','TRAN NHAN TONG','BA DINH','HA NOI'));
INSERT INTO PLAYER VALUES (9,'PLAYER 9','900001',TO_DATE('16/09/1985','DD/MM/YYYY'),T_ADDRESS ('9/4','YET KIEU','HOAN KIEM','HA NOI'));
INSERT INTO PLAYER VALUES (10,'PLAYER 10','100001',TO_DATE('17/05/1985','DD/MM/YYYY'),T_ADDRESS ('18A','LE VAN SY','3','HCMC'));
INSERT INTO PLAYER VALUES (11,'PLAYER 11','110001',TO_DATE('28/03/1987','DD/MM/YYYY'),T_ADDRESS ('4','PHAN TON','1','HCMC'));
INSERT INTO PLAYER VALUES (12,'PLAYER 12','120001',TO_DATE('12/06/1983','DD/MM/YYYY'),T_ADDRESS ('35','PHAN KE BINH','1','HCMC'));
INSERT INTO PLAYER VALUES (13,'PLAYER 13','130001',TO_DATE('20/05/1974','DD/MM/YYYY'),T_ADDRESS ('27/3A','TRAN NHAN TONG','BA DINH','HA NOI'));
INSERT INTO PLAYER VALUES (14,'PLAYER 14','140001',TO_DATE('19/09/1986','DD/MM/YYYY'),T_ADDRESS ('9/4','YET KIEU','HOAN KIEM','HA NOI'));
INSERT INTO PLAYER VALUES (15,'PLAYER 15','150001',TO_DATE('17/05/1989','DD/MM/YYYY'),T_ADDRESS ('18A','LE VAN SY','3','HCMC'));
INSERT INTO PLAYER VALUES (16,'PLAYER 16','160001',TO_DATE('18/03/1980','DD/MM/YYYY'),T_ADDRESS ('4','PHAN TON','1','HCMC'));
INSERT INTO PLAYER VALUES (17,'PLAYER 17','170001',TO_DATE('18/06/1986','DD/MM/YYYY'),T_ADDRESS ('35','PHAN KE BINH','1','HCMC'));
INSERT INTO PLAYER VALUES (18,'PLAYER 18','180001',TO_DATE('21/05/1978','DD/MM/YYYY'),T_ADDRESS ('27/3A','TRAN NHAN TONG','BA DINH','HA NOI'));
INSERT INTO PLAYER VALUES (19,'PLAYER 19','190001',TO_DATE('12/09/1984','DD/MM/YYYY'),T_ADDRESS ('9/4','YET KIEU','HOAN KIEM','HA NOI'));
INSERT INTO PLAYER VALUES (20,'PLAYER 20','200001',TO_DATE('07/05/1981','DD/MM/YYYY'),T_ADDRESS ('18A','LE VAN SY','3','HCMC'));
INSERT INTO PLAYER VALUES (21,'PLAYER 21','210001',TO_DATE('18/03/1982','DD/MM/YYYY'),T_ADDRESS ('4','PHAN TON','1','HCMC'));
INSERT INTO PLAYER VALUES (22,'PLAYER 22','220001',TO_DATE('01/06/1988','DD/MM/YYYY'),T_ADDRESS ('35','PHAN KE BINH','1','HCMC'));
INSERT INTO PLAYER VALUES (23,'PLAYER 23','230001',TO_DATE('20/05/1979','DD/MM/YYYY'),T_ADDRESS ('27/3A','TRAN NHAN TONG','BA DINH','HA NOI'));
INSERT INTO PLAYER VALUES (24,'PLAYER 24','240001',TO_DATE('16/09/1985','DD/MM/YYYY'),T_ADDRESS ('9/4','YET KIEU','HOAN KIEM','HA NOI'));

-- NHẬP LIỆU BẢNG TEAM
INSERT INTO TEAM VALUES (1,'TEAM 1',T_PLAYERS());

	-- NHẬP DỮ LIỆU VÀO BẢNG NESTED TRONG BẢNG TEAM,LẤY DỮ LIỆU PLAYER TỪ BẢNG PLAYER
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=1)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=1;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=1)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=2;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=1)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=3;
	
INSERT INTO TEAM VALUES (2,'TEAM 2',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=2)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=4;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=2)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=5;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=2)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=6;

INSERT INTO TEAM VALUES (3,'TEAM 3',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=3)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=7;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=3)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=8;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=3)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=9;

INSERT INTO TEAM VALUES (4,'TEAM 4',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=4)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=10;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=4)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=11;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=4)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=12;

INSERT INTO TEAM VALUES (5,'TEAM 5',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=5)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=13;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=5)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=14;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=5)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=15;
	
INSERT INTO TEAM VALUES (6,'TEAM 6',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=6)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=16;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=6)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=17;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=6)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=18;
	
INSERT INTO TEAM VALUES (7,'TEAM 7',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=7)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=19;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=7)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=20;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=7)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=21;
	
INSERT INTO TEAM VALUES (8,'TEAM 5',T_PLAYERS());

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=8)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=22;

	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=8)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=23;
	
	INSERT INTO TABLE (SELECT T.LISTPLAYER FROM TEAM T WHERE T.TEAM_ID=8)
	SELECT *
	FROM PLAYER S 
	WHERE S.PERSON_ID=24;
	
-- NHẬP LIỆU BẢNG CATEGORY
INSERT INTO CATEGORY VALUES (1,'CATEGORY 1 - FIRST',T_TEAMS());

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=1)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=1;

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=1)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=2;

INSERT INTO CATEGORY VALUES (2,'CATEGORY 2 - SECOND',T_TEAMS());

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=2)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=3;

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=2)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=4;

INSERT INTO CATEGORY VALUES (3,'CATEGORY 3 - THIRD',T_TEAMS());

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=3)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=5;

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=3)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=6;

INSERT INTO CATEGORY VALUES (4,'CATEGORY 4 - FORTH',T_TEAMS());

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=4)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=7;

	INSERT INTO TABLE (SELECT C.LISTTEAM FROM CATEGORY C WHERE C.CATEGORY_ID=4)
	SELECT *
	FROM TEAM T
	WHERE T.TEAM_ID=8;

-- NHẬP LIỆU BẢNG CLUB
INSERT INTO CLUB VALUES (1,'CLUB 1', 
	(SELECT REF(O) FROM OFFICE O WHERE O.OFFICE_ID=1),
	(SELECT REF(P) FROM PRESIDENT P WHERE P.PERSON_ID=1),
	(SELECT REF(V) FROM VICE_PRESIDENT V WHERE V.PERSON_ID=1),
	(SELECT REF(T) FROM TREASURER T WHERE T.PERSON_ID=1),
	(SELECT REF(S) FROM SECRETARY S WHERE S.PERSON_ID=1),
	T_CATEGORIES());

	INSERT INTO TABLE (SELECT C.LISTCATEGORY FROM CLUB C WHERE C.CLUB_ID=1)
	SELECT *
	FROM CATEGORY C
	WHERE C.CATEGORY_ID=1;

	INSERT INTO TABLE (SELECT C.LISTCATEGORY FROM CLUB C WHERE C.CLUB_ID=1)
	SELECT *
	FROM CATEGORY C
	WHERE C.CATEGORY_ID=2;
	
INSERT INTO CLUB VALUES (2,'CLUB 2',
	(SELECT REF(O) FROM OFFICE O WHERE O.OFFICE_ID=2),
	(SELECT REF(P) FROM PRESIDENT P WHERE P.PERSON_ID=2),
	(SELECT REF(V) FROM VICE_PRESIDENT V WHERE V.PERSON_ID=2),
	(SELECT REF(T) FROM TREASURER T WHERE T.PERSON_ID=2),
	(SELECT REF(S) FROM SECRETARY S WHERE S.PERSON_ID=2),
	T_CATEGORIES());

	INSERT INTO TABLE (SELECT C.LISTCATEGORY FROM CLUB C WHERE C.CLUB_ID=2)
	SELECT *
	FROM CATEGORY C
	WHERE C.CATEGORY_ID=3;

	INSERT INTO TABLE (SELECT C.LISTCATEGORY FROM CLUB C WHERE C.CLUB_ID=2)
	SELECT *
	FROM CATEGORY C
	WHERE C.CATEGORY_ID=4;

-- NHẬP DỮ LIỆU BẢNG TEAM_COACH
DELETE TEAM_COACH
/
INSERT INTO TEAM_COACH VALUES (1,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=1),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=1),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (2,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=1),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=2),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (3,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=2),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=1),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (4,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=2),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=3),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (5,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=3),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=4),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (6,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=3),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=5),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (7,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=4),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=5),
	SYSDATE, SYSDATE + 365
)
/
INSERT INTO TEAM_COACH VALUES (8,
	(SELECT REF(T) FROM TEAM T WHERE T.TEAM_ID=4),
	(SELECT REF(C) FROM COACH C WHERE C.PERSON_ID=5),
	SYSDATE, SYSDATE + 365
)
/
DELETE CLUB_PLAYER
/
-- NHẬP DỮ LIỆU BẢNG CLUB_PLAYER
INSERT INTO CLUB_PLAYER VALUES (1,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=1),SYSDATE - 2000);
INSERT INTO CLUB_PLAYER VALUES (2,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=2),SYSDATE - 800);
INSERT INTO CLUB_PLAYER VALUES (3,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=3),SYSDATE - 1300);
INSERT INTO CLUB_PLAYER VALUES (4,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=4),SYSDATE - 1900);
INSERT INTO CLUB_PLAYER VALUES (5,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=5),SYSDATE - 1800);
INSERT INTO CLUB_PLAYER VALUES (6,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=6),SYSDATE - 2100);
INSERT INTO CLUB_PLAYER VALUES (7,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=7),SYSDATE - 350);
INSERT INTO CLUB_PLAYER VALUES (8,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=8),SYSDATE - 670);
INSERT INTO CLUB_PLAYER VALUES (9,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=9),SYSDATE - 200);
INSERT INTO CLUB_PLAYER VALUES (10,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=10),SYSDATE - 1000);
INSERT INTO CLUB_PLAYER VALUES (11,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=11),SYSDATE - 2000);
INSERT INTO CLUB_PLAYER VALUES (12,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=1),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=12),SYSDATE - 600);
INSERT INTO CLUB_PLAYER VALUES (13,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=13),SYSDATE - 659);
INSERT INTO CLUB_PLAYER VALUES (14,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=14),SYSDATE - 347);
INSERT INTO CLUB_PLAYER VALUES (15,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=15),SYSDATE - 865);
INSERT INTO CLUB_PLAYER VALUES (16,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=16),SYSDATE - 336);
INSERT INTO CLUB_PLAYER VALUES (17,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=17),SYSDATE - 744);
INSERT INTO CLUB_PLAYER VALUES (18,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=18),SYSDATE - 447);
INSERT INTO CLUB_PLAYER VALUES (19,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=19),SYSDATE - 743);
INSERT INTO CLUB_PLAYER VALUES (20,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=20),SYSDATE - 346);
INSERT INTO CLUB_PLAYER VALUES (21,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=21),SYSDATE - 84);
INSERT INTO CLUB_PLAYER VALUES (22,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=22),SYSDATE - 754);
INSERT INTO CLUB_PLAYER VALUES (23,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=23),SYSDATE - 333);
INSERT INTO CLUB_PLAYER VALUES (24,(SELECT REF(C) FROM CLUB C WHERE C.CLUB_ID=2),(SELECT REF(P) FROM PLAYER P WHERE P.PERSON_ID=24),SYSDATE - 451);
	