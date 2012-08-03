--------------------------------------------------------
--  Constraints for Table CATEGORY
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."CATEGORY" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CATEGORY" MODIFY ("CATEGORY_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CATEGORY" ADD PRIMARY KEY ("ID");
--------------------------------------------------------
--  Constraints for Table CLUB
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_OFFICE" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_PRESIDENT" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_VICE_PRESIDENT" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_TREASURER" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" MODIFY ("CLUB_SECRETARY" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."CLUB" ADD PRIMARY KEY ("ID");

--------------------------------------------------------
--  Constraints for Table COACH
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."COACH" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."COACH" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."COACH" ADD PRIMARY KEY ("ID");

--------------------------------------------------------
--  Constraints for Table MATCH
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."MATCH" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."MATCH" MODIFY ("SEASON" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."MATCH" ADD PRIMARY KEY ("ID");

--------------------------------------------------------
--  Constraints for Table OFFICE
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."OFFICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."OFFICE" MODIFY ("OFFICE_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."OFFICE" MODIFY ("OFFICE_ADDRESS" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."OFFICE" ADD PRIMARY KEY ("ID");

--------------------------------------------------------
--  Constraints for Table PLAYER
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."PLAYER" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PLAYER" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PLAYER" MODIFY ("PLAYER_LICENCE_NUMBER" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PLAYER" MODIFY ("PLAYER_BIRTHDAY" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PLAYER" MODIFY ("PLAYER_ADDRESS" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PLAYER" ADD PRIMARY KEY ("ID");
 
--------------------------------------------------------
--  Constraints for Table PRESIDENT
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."PRESIDENT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PRESIDENT" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."PRESIDENT" ADD PRIMARY KEY ("ID");  
--------------------------------------------------------
--  Constraints for Table SEASON
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."SEASON" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."SEASON" ADD PRIMARY KEY ("ID");
 
--------------------------------------------------------
--  Constraints for Table SECRETARY
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."SECRETARY" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."SECRETARY" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."SECRETARY" ADD PRIMARY KEY ("ID");
  
--------------------------------------------------------
--  Constraints for Table TEAM
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."TEAM" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."TEAM" MODIFY ("TEAM_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."TEAM" ADD PRIMARY KEY ("ID");
  
--------------------------------------------------------
--  Constraints for Table TREASURER
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."TREASURER" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."TREASURER" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."TREASURER" ADD PRIMARY KEY ("ID");
  
--------------------------------------------------------
--  Constraints for Table USERBASKET
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."USERBASKET" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."USERBASKET" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."USERBASKET" ADD PRIMARY KEY ("ID");
  
--------------------------------------------------------
--  Constraints for Table VICE_PRESIDENT
--------------------------------------------------------

  ALTER TABLE "BASKET_USER"."VICE_PRESIDENT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."VICE_PRESIDENT" MODIFY ("PERSON_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "BASKET_USER"."VICE_PRESIDENT" ADD PRIMARY KEY ("ID");
  
  COMMIT;