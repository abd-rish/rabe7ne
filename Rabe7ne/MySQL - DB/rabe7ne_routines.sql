-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: rabe7ne
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `v_questions`
--

DROP TABLE IF EXISTS `v_questions`;
/*!50001 DROP VIEW IF EXISTS `v_questions`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_questions` AS SELECT 
 1 AS `id`,
 1 AS `partner_id`,
 1 AS `partner_name`,
 1 AS `description`,
 1 AS `ques_date`,
 1 AS `ques_date_string`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_partners`
--

DROP TABLE IF EXISTS `v_partners`;
/*!50001 DROP VIEW IF EXISTS `v_partners`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_partners` AS SELECT 
 1 AS `partner_id`,
 1 AS `name`,
 1 AS `country_code`,
 1 AS `country_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_user_points`
--

DROP TABLE IF EXISTS `v_user_points`;
/*!50001 DROP VIEW IF EXISTS `v_user_points`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_user_points` AS SELECT 
 1 AS `USER_ID`,
 1 AS `FIRST_NAME`,
 1 AS `LAST_NAME`,
 1 AS `POINTS`,
 1 AS `COUNTRY_NAME`,
 1 AS `AR_COUNTRY_NAME`,
 1 AS `CALLING_CODE`,
 1 AS `PHONE`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_user_partner_points`
--

DROP TABLE IF EXISTS `v_user_partner_points`;
/*!50001 DROP VIEW IF EXISTS `v_user_partner_points`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_user_partner_points` AS SELECT 
 1 AS `USER_ID`,
 1 AS `PARTNER_ID`,
 1 AS `FIRST_NAME`,
 1 AS `LAST_NAME`,
 1 AS `POINTS`,
 1 AS `COUNTRY_NAME`,
 1 AS `AR_COUNTRY_NAME`,
 1 AS `CALLING_CODE`,
 1 AS `PHONE`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_questions`
--

/*!50001 DROP VIEW IF EXISTS `v_questions`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_questions` AS select `q`.`id` AS `id`,`q`.`partner_id` AS `partner_id`,`p`.`name` AS `partner_name`,`q`.`description` AS `description`,`q`.`ques_date` AS `ques_date`,date_format(`q`.`ques_date`,'%m/%d/%Y') AS `ques_date_string` from (`questions` `q` join `partners` `p` on((`q`.`partner_id` = `p`.`partner_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_partners`
--

/*!50001 DROP VIEW IF EXISTS `v_partners`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_partners` AS select `p`.`partner_id` AS `partner_id`,`p`.`name` AS `name`,`p`.`country_code` AS `country_code`,`c`.`NAME` AS `country_name` from (`partners` `p` join `countries` `c` on((`p`.`country_code` = `c`.`CODE`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_user_points`
--

/*!50001 DROP VIEW IF EXISTS `v_user_points`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_user_points` AS select `u`.`user_id` AS `USER_ID`,`u`.`first_name` AS `FIRST_NAME`,`u`.`last_name` AS `LAST_NAME`,`u`.`points` AS `POINTS`,`c`.`NAME` AS `COUNTRY_NAME`,`c`.`AR_NAME` AS `AR_COUNTRY_NAME`,`c`.`CALLING_CODE` AS `CALLING_CODE`,`u`.`phone` AS `PHONE` from (`users` `u` join `countries` `c` on((`u`.`country_code` = `c`.`CODE`))) order by `u`.`points` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_user_partner_points`
--

/*!50001 DROP VIEW IF EXISTS `v_user_partner_points`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_user_partner_points` AS select `upp`.`USER_ID` AS `USER_ID`,`upp`.`PARTNER_ID` AS `PARTNER_ID`,`upp`.`FIRST_NAME` AS `FIRST_NAME`,`upp`.`LAST_NAME` AS `LAST_NAME`,`upp`.`POINTS` AS `POINTS`,`c`.`NAME` AS `COUNTRY_NAME`,`c`.`AR_NAME` AS `AR_COUNTRY_NAME`,`c`.`CALLING_CODE` AS `CALLING_CODE`,`upp`.`PHONE` AS `PHONE` from ((select `u`.`user_id` AS `USER_ID`,`uaq`.`PARTNER_ID` AS `PARTNER_ID`,`u`.`first_name` AS `FIRST_NAME`,`u`.`last_name` AS `LAST_NAME`,`u`.`country_code` AS `COUNTRY_CODE`,`u`.`phone` AS `PHONE`,`uaq`.`POINTS` AS `POINTS` from (`users` `u` join (select `ua`.`user_id` AS `USER_ID`,`q`.`partner_id` AS `PARTNER_ID`,count(0) AS `POINTS` from (`user_anss` `ua` join `questions` `q` on((`ua`.`ques_id` = `q`.`id`))) where (`ua`.`correct` = 'Y') group by `ua`.`user_id`,`q`.`partner_id`) `uaq` on((`u`.`user_id` = `uaq`.`USER_ID`))) order by `uaq`.`POINTS` desc) `upp` join `countries` `c` on((`upp`.`COUNTRY_CODE` = `c`.`CODE`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 17:03:36
