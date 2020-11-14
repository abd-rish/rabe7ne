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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `spec_code` varchar(30) NOT NULL,
  `points` int(11) NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `pass` varchar(60) NOT NULL,
  `confirm_code` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `users_country_code_idx` (`country_code`),
  CONSTRAINT `users_country_code` FOREIGN KEY (`country_code`) REFERENCES `countries` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'asd','asd','AZEDX1',1,'AZ','516','2ee4f02e-02f5-48f6-9218-ede6538b0c671586020094557',NULL),(2,'asd','asd','AZYIR2',0,'AZ','515','d3ae266c-f70c-40f3-9606-63cbfecddc2c1586020277873',NULL),(3,'عبدالله','أبوالريش','JOOFW3',1,'JO','123','eb9011d0-6691-449f-83d8-e83ae594288d1586021206245','420725'),(4,'محمود','محمود','JOQQB4',0,'JO','78820646+2','6fd40dc6-ffa1-41a6-b675-31d4e53896821586648548971',NULL),(5,'شسيشس','سبسيب','JOPVE5',0,'JO','788206465+2','11d4b5bb-9664-401a-9bd4-de217ca3524a1586648671855',NULL),(6,'aa','bb','JOKZE6',0,'JO','123456','592c39f9-98d0-4f67-b785-bed3b4409cc11586649669299',NULL),(7,'2r1','21','JOGTO7',0,'JO','7123456','6adde481-4317-4092-81f6-7e4ba71ce2951586649795433',NULL),(8,'2r1','21','JOABO8',0,'JO','788205166441','8e9aa30a-08ef-49e3-8c06-f0008d5b21fa1586649879738','894534'),(9,'w1','w2','JOGOZ9',0,'JO','7882206441','1b2ea92f-cc59-41b6-8c87-1b711f6010241586650018948',NULL),(10,'عبدالله','أبوالريش','JOMNP10',22,'JO','7882016441','67ad2993-6c4a-4f37-a29e-af1a47ffe32d1586650235714',NULL),(11,'عبدالله','أبوالريش','JOGIS11',0,'JO','788206441','927920f2-f9f7-4ab9-b0db-cf9bc8c0e63c1597671299938',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 17:03:34
