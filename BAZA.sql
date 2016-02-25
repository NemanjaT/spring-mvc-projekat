-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: evimedik
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `klinika`
--

DROP TABLE IF EXISTS `klinika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klinika` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `telefon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klinika`
--

LOCK TABLES `klinika` WRITE;
/*!40000 ALTER TABLE `klinika` DISABLE KEYS */;
INSERT INTO `klinika` VALUES (1,'Dom Zdravlja \"Rakovica\"','Neka Adresa 1a','1234-567');
/*!40000 ALTER TABLE `klinika` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `pol` enum('m','z') DEFAULT NULL,
  `broj_knjizice` varchar(11) DEFAULT NULL,
  `lozinka` varchar(45) DEFAULT NULL,
  `jmbg` varchar(13) DEFAULT NULL,
  `osiguranje_ime_prezime` varchar(45) NOT NULL,
  `osiguranje_srodstvo` varchar(45) NOT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `telefon` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tip` enum('administrator','lekar opste prakse','lekar specijalista','pacijent') NOT NULL DEFAULT 'pacijent',
  `specijalista_tip_id` int(11) DEFAULT NULL,
  `klinika_id` int(11) DEFAULT NULL,
  `prihvacen` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `broj_knjizice_UNIQUE` (`broj_knjizice`),
  UNIQUE KEY `jmbg_UNIQUE` (`jmbg`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES (1,'Nemanja','Tozic','m','24900713652','lozinka','2809994710290','Goran Tozic','Otac','Stevana Lukovica 16','069/146-65-67','nemanjat94@hotmail.com','administrator',NULL,NULL,1),(2,'Milos','Andjelkovic','m','12345678901','lozinka','0101994123456','Darko Andjelkovic','Otac','Baric Nedodjija 2z','063/142-58-96','a.misos@gmail.com','lekar opste prakse',NULL,NULL,1),(3,'Sinisa','Trifunovic','m','23456789012','lozinka','0101994234567','Jelena Trifunovic','Majka','Atomska Livada 4b','061/783-852','trifunovic.sinisa@gmail.com','lekar specijalista',1,1,1),(22,'Dejana','Djukic','z','78945612307','lozinka','0101994345678','Aleksa Djukic','Brat','Stevana Lukovica 17','064/123-45-78','dejanadj@hotmail.com','pacijent',NULL,1,1),(25,'Aleksa','Djukic','m','89456123078','lozinka','0101999345678','Dejana Djukic','Sestra','Stevana Lukovica 17','060/789-45-12','aleksa.uragan@gmail.com','pacijent',NULL,1,1),(26,'Branislav','Bozic','m','74185296356','lozinka','0101994456123','Brako Bozic','Otac','Selo Bele Vode 1','060/753-95-65','bozic.branislav@gmail.com','pacijent',NULL,1,1),(27,'Jelena','Metodijevic','z','75315982156','lozinka','1905994272727','Branislav Metodijevic','Otac','Popovic 14','065/852-14-36','bailamos94@gmail.com','lekar specijalista',2,1,1),(28,'Dusko','Duskovic','m','98795135782','lozinka','3103987710789','Dunav Osiguranje','Klijent','Neka Adresa 23a','065/357-159','dusko.duskic@gmail.com','pacijent',NULL,1,1),(29,'Zorica','Stemeljkovic','z','76891235977','lozinka','0506988654987','Djole Osiguranje','Brat od 2. strica','Vojvode Stepe 15b','065/775-69-24','zstem@hotmail.com','lekar specijalista',13,1,1),(31,'Radojko','Radojkovic','m','68427531982','lozinka','0101994789526','Zgrogan Zavrovic','Brat od 3. tetke','Tamo Negde 16b','064/754-89-23','radojko.radojkovic@gmail.com','pacijent',NULL,1,1),(32,'Stefan','Stankovic','m','12986537456','lozinka','0102994321654','Slobodan Popara','Ortak iz srednje (?)','Petlovac Brdovac 3','063/753-741','stefanstan@hotmail.com','lekar opste prakse',NULL,1,1),(33,'Ruzica','Ruzicic','z','36752149638','lozinka','3112000759351','','','Ruzicina ulica 3','067/743-32-36','ruzicaruzicic@yahoo.com','lekar specijalista',2,1,1),(34,'Perica','Å trbac','m','35975184265','lozinka','0101977635986','VISER','Fax','Negde Daleko 85','066/129-896','perica@viser.com','pacijent',NULL,1,1),(35,'Dusko','Radovic','m','86931754902','lozinka','0506954783621','Dunav','Klijent','Beogradski Kapitali 17','066/321-65-78','duskorad@yahoo.com','pacijent',NULL,1,0);
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nalaz`
--

DROP TABLE IF EXISTS `nalaz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nalaz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_nalaza` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `disanje` int(11) DEFAULT '50',
  `puls` int(11) DEFAULT '80',
  `telesna_temperatura` int(11) DEFAULT '36',
  `krvni_pritisak` varchar(45) DEFAULT 'sistolni',
  `mokraca` varchar(45) DEFAULT 'u redu',
  `stolica` varchar(45) DEFAULT 'u redu',
  `krvna_slika` varchar(45) DEFAULT 'u redu',
  `specifican_pregled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nalaz`
--

LOCK TABLES `nalaz` WRITE;
/*!40000 ALTER TABLE `nalaz` DISABLE KEYS */;
INSERT INTO `nalaz` VALUES (11,'2016-02-10 19:14:00',60,80,36,'sistolni','good','good','good',''),(12,'2016-02-10 19:52:38',60,80,36,'sistolni','good','good','good',''),(13,'2016-02-10 20:01:50',80,120,38,'sistolni','bad','good','bad','RMA'),(14,'2016-02-10 20:09:51',60,80,36,'sistolni','good','bad','good','kardiologija'),(15,'2016-02-10 20:23:05',60,80,36,'sistolni','good','bad','good',''),(16,'2016-02-11 23:54:06',90,160,41,'dijastolni','bad','bad','bad','probali smo sve zivo....'),(17,'2016-02-11 23:55:59',90,120,36,'sistolni','good','good','good','');
/*!40000 ALTER TABLE `nalaz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregled`
--

DROP TABLE IF EXISTS `pregled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pacijent_id` int(11) NOT NULL,
  `datum_pregleda` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dijagnoza` text,
  `naziv_bolesti` varchar(45) NOT NULL,
  `tegobe` text,
  `propisana_terapija` text,
  `datum_sledece_kontrole` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `cuvaj_pacijenta` int(11) DEFAULT '0',
  `nalaz_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregled`
--

LOCK TABLES `pregled` WRITE;
/*!40000 ALTER TABLE `pregled` DISABLE KEYS */;
INSERT INTO `pregled` VALUES (1,22,'2016-02-08 01:12:08','kasalj, glavobolja','virus 3x2cl','pritisak u gornjem delu cela, bronhitis','2ml spalmotila, kafetin po potrebi','2016-02-09 23:00:00',0,0),(2,22,'2016-02-08 01:40:19','bol u stomaku','sklupikus','jak bol u stomaku, stalno kukanje na ajfelovu kulu (??)','smesne tabletice','2016-02-15 23:00:00',1,0),(3,25,'2016-02-08 12:03:12','bolestan u glavu','bolikus glavikus','jaki bolovi u glavu, zadnjem delu tela ispod ledja i genitalicnim zonama','smesne tabletice','2016-02-09 23:00:00',0,0),(4,26,'2016-02-08 12:05:12','izpupcenje u butniskom delu noge','tumor','prilikom pritiska noge nastaje ispupcenje','operacija','2016-02-24 23:00:00',1,0),(5,26,'2016-02-08 22:29:56','povecana izbocina na nozi','tumor','povecana izbocina u odnosu na ranije','uput hirurgu.','2016-02-16 23:00:00',1,0),(6,25,'2016-02-09 16:27:50','Jaki bolovi','bolikus glavikus','Bolovi u predelima tela','Kucno lecenje','2016-02-08 23:00:00',0,0),(7,25,'2016-02-09 16:36:48','pojacani bolovi','bolikus glavikus razvijenikus','jaki bolovi u abdomenu','tecnosti','2016-02-08 23:00:00',1,0),(8,25,'2016-02-09 16:54:27','Jaki bolovi','bolikus glavikus razvijenikus','Jaki bolovi u vise delova tela','Teski lekovi','2016-02-08 23:00:00',0,0),(9,25,'2016-02-09 17:01:28','Jaki bolovi (nadam se poslednji put...)','glavikus bolikus','Jaki bolovi u stomaku i glavi','Lekovi','2016-02-08 23:00:00',1,0),(10,26,'2016-02-10 13:05:37','tumor na desnom misicu leve noge','tumor','jaki bolovi pri misicu','radijacija','2016-02-28 23:00:00',1,0),(11,22,'2016-02-10 18:01:04','Bol u stomaku','stomakus bolikus','Jaki bolovi u stomaku ispod grudnog kosa','cajevi, tecnost','2016-02-10 23:00:00',0,0),(12,26,'2016-02-10 18:49:23','veliki bol u nozi','tumor','veliki bol u levoj nozi','kucna terapija','2016-02-25 23:00:00',0,0),(13,26,'2016-02-10 18:55:45','oslabljena dejstva tumora','tumor','tumor na nozi u manjoj velicini (~3cm)','kucno lecenje','2016-02-18 23:00:00',1,0),(14,25,'2016-02-10 18:59:45','slabiji bolovi','bolikus glavikus','bol u glavi u manjim velicinama','kucno lecenje','2016-02-18 23:00:00',1,0),(15,22,'2016-02-10 19:02:43','bol u stomaku','stomkus','jaki bolovi u stomaku','kucna terapija','2016-02-18 23:00:00',1,0),(16,26,'2016-02-10 19:14:00','tumor u nozi','tumor','oslabljeni tumor','kucna ter.','2016-02-24 23:00:00',1,0),(17,25,'2016-02-10 19:52:38','bol u ledjima','kicmikus','bol u ledjima izmedju 3 i 4 prsljena','kucno lecenje','2016-02-16 23:00:00',0,1),(18,22,'2016-02-10 20:01:50','bol u stomaku','stomkus','jaci bolovi u stomaku','lekovi c345e+','2016-02-25 23:00:00',1,1),(19,26,'2016-02-10 20:09:51','bol u stomaku','h5c3','jaki bolovi u stomak u predelu zeludca','cajebi, bilje, spalmotil u slucaju gusenja','2016-02-25 23:00:00',1,14),(20,22,'2016-02-10 20:23:05','bolovi u stomaku','diareja','proliv, bolovi u stomaku','kucno lecenje, cajevi, tecnost, suva hrana','2016-02-26 23:00:00',0,15),(21,31,'2016-02-11 23:51:43','Ce umre','umrikus mnogokus','Poprilicne sanse da ce pacenik da rikne...','Zamonasi se...','2016-02-27 23:00:00',0,0),(22,31,'2016-02-11 23:54:06','stvarno ce umre...','umrikus mnogokus','stvarno ce jako da umre...','jbg..','2016-02-12 23:00:00',0,16),(23,31,'2016-02-11 23:55:59','nema mu spasa..','umrikus mnogokus','umro..','grob','2016-02-11 23:00:00',1,17);
/*!40000 ALTER TABLE `pregled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specijalista_tip`
--

DROP TABLE IF EXISTS `specijalista_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specijalista_tip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prioritet` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ime_UNIQUE` (`ime`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specijalista_tip`
--

LOCK TABLES `specijalista_tip` WRITE;
/*!40000 ALTER TABLE `specijalista_tip` DISABLE KEYS */;
INSERT INTO `specijalista_tip` VALUES (1,'Nacelnik Odeljenja',1),(2,'Kardiolog',2),(3,'Alerolog',2),(4,'Akuser',2),(5,'Anesteziolog',2),(6,'Dermatolog',2),(7,'Endokrinolog',2),(8,'Epidemolog',2),(9,'Gastroenterolog',2),(10,'Gerontolog',2),(11,'Ginekolog',2),(12,'Hematolog',2),(13,'Hirurg',2),(14,'Imunolog',2),(15,'Nefrolog',2),(16,'Oftalmolog',2),(17,'Ortoped',2),(18,'Otoringolaringolog',2),(19,'Patolog',2),(20,'Pedijatar',2),(21,'Psihijatar',2),(22,'Pulmolog',2),(23,'Radiolog',2),(24,'Reumatolog',2),(25,'Stomatolog',2),(26,'Urolog',2);
/*!40000 ALTER TABLE `specijalista_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uput`
--

DROP TABLE IF EXISTS `uput`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uput` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_pregleda` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `klinika_id` int(11) NOT NULL,
  `pacijent_id` int(11) NOT NULL,
  `specijalista_tip_id` int(11) NOT NULL,
  `pregledan` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uput`
--

LOCK TABLES `uput` WRITE;
/*!40000 ALTER TABLE `uput` DISABLE KEYS */;
INSERT INTO `uput` VALUES (1,'2016-02-15 22:18:00',1,22,1,0),(2,'2016-02-16 22:18:00',1,23,1,0),(3,'2016-02-16 20:15:29',1,26,13,0),(4,'2016-02-17 19:08:29',1,26,13,0),(5,'2016-02-18 20:15:29',1,22,13,0),(6,'2016-02-19 20:15:29',1,25,13,0),(7,'2016-02-20 20:15:29',1,26,13,0),(8,'2016-02-26 23:00:00',1,22,13,1),(9,'2016-02-27 23:00:00',1,31,2,1),(10,'2016-02-12 23:00:00',1,31,13,1);
/*!40000 ALTER TABLE `uput` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-25 18:19:55
