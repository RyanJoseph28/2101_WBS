-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2024 at 04:31 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_user_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientinfo`
--

CREATE TABLE `clientinfo` (
  `Account_No` varchar(10) NOT NULL,
  `Account_Name` varchar(100) NOT NULL,
  `Service_Address` varchar(100) NOT NULL,
  `Contact_Number` int(100) NOT NULL,
  `Property` varchar(100) NOT NULL,
  `Meter_No` varchar(20) DEFAULT NULL,
  `Account_Status` varchar(100) NOT NULL,
  `Username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clientinfo`
--

INSERT INTO `clientinfo` (`Account_No`, `Account_Name`, `Service_Address`, `Contact_Number`, `Property`, `Meter_No`, `Account_Status`, `Username`) VALUES
('RJCWB001', 'Ryan Joseph Acbang', 'Sambungan, Calatagan, Batangas', 96754721, 'Residential', '100-001', 'Active', NULL),
('RJCWB002', 'Chedric Bascoguin', 'Lucsuhin, Calatagan, Batangas', 934213344, 'Residential', '100-002', 'Active', NULL),
('RJCWB003', 'Ariane kaye Vecinal', 'Biga, Calatagan, Batangas', 955643214, 'Residential', '100-003', 'Active', NULL),
('RJCWB004', 'Jerard Verroya', 'Gulod, Calatagan, Batangas', 943634722, 'Residential', '100-004', 'Active', NULL),
('RJCWB005', 'John Laurence Dino', 'Brgy. 3, Calatagan, Batangas', 956697943, 'Residential', '100-005', 'Active', NULL),
('RJCWB006', 'Vince Mendoza', 'Carretunan, Calatagan, Batangas', 933214476, 'Residential', '100-006', 'Active', NULL),
('RJCWB007', 'Prince Jude Arcayos', 'Lucsuhin, Calatagan, Batangas', 967655904, 'Residential', '100-007', 'Active', NULL),
('RJCWB008', 'Carl Andrey Neri', 'Brgy. 2 Calatagan Batangas', 956974390, 'Residential', '100-008', 'Active', NULL),
('RJCWB009', 'Noreen Grace Gonzales', 'Biga Calatagan Batangas', 936372647, 'Residential', '100-009', 'Active', NULL),
('RJCWB010', 'Andrew Tentia', 'Talisay Calatagan Batangas', 957527912, 'Residential', '100-010', 'Active', NULL),
('RJCWB011', 'Allend Andaya', 'Cumba, Lian Batangas', 967543905, 'Commercial', '100-011', 'Active', NULL),
('RJCWB012', 'Angela Mae Gutierrez', 'Biga Calatagan Batangas', 943215555, 'Industrial', '100-012', 'Active', NULL),
('RJCWB013', 'Ashley Mae Aseron', 'Balitoc Calatagan Batangas', 944579113, 'Commercial', '100-013', 'Active', NULL),
('RJCWB014', 'Betti Chavez', 'Humayingan Lian Batangas', 974923323, 'Industrial', '100-014', 'Active', NULL),
('RJCWB015', 'Charish Bea Calinawan', 'Kapito Lian Batangas', 953617218, 'Industrial', '100-015', 'Active', NULL),
('RJCWB016', 'Charlie Magyaya', 'Matabungkay Lian Batangas', 953617218, 'Institutional', '100-016', 'Active', NULL),
('RJCWB017', 'Chilsea Mendoza', 'Balibago Lian Batangas', 983617162, 'Residential', '100-017', 'Active', NULL),
('RJCWB018', 'Christian Anchorez', 'Rizal TUY Batangas', 956692009, 'Commercial', '100-018', 'Active', NULL),
('RJCWB019', 'Pauline Baldos', 'Brgy 2 TUY Batangas', 967236209, 'Residential', '100-019', 'Active', NULL),
('RJCWB020', 'Clinton John Masongsong', 'Malahuratan Lian Batangas', 976846657, 'Commercial', '100-020', 'Active', NULL),
('RJCWB021', 'Nikko Causapin', 'Palincaro Balayan Batangas', 956752209, 'Residential', '100-021', 'Active', NULL),
('RJCWB022', 'Jovelyn Dalisay', 'Patugo Balayan Batangas', 956747322, 'Commercial', '100-022', 'Active', NULL),
('RJCWB023', 'Kath Guevarra', 'Brgy Magabe Balayan Batangas', 921338976, 'Commercial', '100-023', 'Active', NULL),
('RJCWB024', 'Erich Bondok', 'Palikpikan Balayan Batangas', 987590434, 'Residential', '100-024', 'Active', NULL),
('RJCWB025', 'Michael Tolosa', 'Brgy 4 Calatagan Batangas', 956887934, 'Commercial', '100-025', 'Active', NULL),
('RJCWB026', 'Paolo Ramsey', 'Hukay Calatagan Batangas', 912342311, 'Residential', '100-026', 'Active', NULL),
('RJCWB027', 'Karl David Diago', 'Brgy 5 Nasugbu Batangas', 987961244, 'Commercial', '100-027', 'Active', NULL),
('RJCWB028', 'Bea Angela Abasquez', 'Wawa Nasugbu Batangas', 926374222, 'Industrial', '100-028', 'Active', NULL),
('RJCWB029', 'Julius Sari', 'Lumbangan Nasugbu Batangas', 967555219, 'Commercial', '100-029', 'Active', NULL),
('RJCWB030', 'Lance Malata', 'Brgy 11 Nasugbu Batangas', 988127669, 'Commercial', '100-030', 'Active', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `clientledger`
--

CREATE TABLE `clientledger` (
  `id` int(11) NOT NULL,
  `Account_No` varchar(100) NOT NULL,
  `Account_Name` varchar(100) NOT NULL,
  `Service_Address` varchar(100) NOT NULL,
  `Property` varchar(100) NOT NULL,
  `Meter_No` varchar(100) NOT NULL,
  `Previous_Reading` decimal(10,2) NOT NULL,
  `Current_Reading` decimal(10,2) NOT NULL,
  `Consumption` decimal(10,2) NOT NULL,
  `Rate` decimal(10,2) NOT NULL,
  `Tax_Amount` decimal(10,2) NOT NULL,
  `Base_Charge` decimal(10,2) NOT NULL,
  `Penalty_Charge` decimal(10,2) NOT NULL,
  `Billing_Period_Start` date NOT NULL,
  `Billing_Period_End` date NOT NULL,
  `Due_Date` date NOT NULL,
  `Total_Amount_Due` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clientledger`
--

INSERT INTO `clientledger` (`id`, `Account_No`, `Account_Name`, `Service_Address`, `Property`, `Meter_No`, `Previous_Reading`, `Current_Reading`, `Consumption`, `Rate`, `Tax_Amount`, `Base_Charge`, `Penalty_Charge`, `Billing_Period_Start`, `Billing_Period_End`, `Due_Date`, `Total_Amount_Due`) VALUES
(20, 'RJCWB001', 'Ryan Joseph Acbang', 'Sambungan, Calatagan, Batangas', 'Residential', '100-001', 0.00, 10.00, 10.00, 30.00, 30.00, 300.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 330.00),
(21, 'RJCWB002', 'Chedric Bascoguin', 'Lucsuhin, Calatagan, Batangas', 'Residential', '100-002', 0.00, 30.00, 30.00, 30.00, 30.00, 900.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 930.00),
(22, 'RJCWB003', 'Ariane kaye Vecinal', 'Biga, Calatagan, Batangas', 'Residential', '100-003', 0.00, 190.00, 190.00, 30.00, 30.00, 5700.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 5730.00),
(23, 'RJCWB004', 'Jerard Verroya', 'Gulod, Calatagan, Batangas', 'Residential', '100-004', 0.00, 567.00, 567.00, 30.00, 30.00, 17010.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 17040.00),
(24, 'RJCWB005', 'John Laurence Dino', 'Brgy. 3, Calatagan, Batangas', 'Residential', '100-005', 0.00, 990.00, 990.00, 30.00, 30.00, 29700.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 29730.00),
(25, 'RJCWB006', 'Vince Mendoza', 'Carretunan, Calatagan, Batangas', 'Residential', '100-006', 0.00, 999.00, 999.00, 30.00, 30.00, 29970.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 30000.00),
(26, 'RJCWB007', 'Prince Jude Arcayos', 'Lucsuhin, Calatagan, Batangas', 'Residential', '100-007', 0.00, 255.00, 255.00, 30.00, 30.00, 7650.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 7680.00),
(27, 'RJCWB008', 'Carl Andrey Neri', 'Brgy. 2 Calatagan Batangas', 'Residential', '100-008', 0.00, 988.00, 988.00, 30.00, 30.00, 29640.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 29670.00),
(28, 'RJCWB009', 'Noreen Grace Gonzales', 'Biga Calatagan Batangas', 'Residential', '100-009', 0.00, 9110.00, 9110.00, 30.00, 30.00, 273300.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 273330.00),
(29, 'RJCWB010', 'Andrew Tentia', 'Talisay Calatagan Batangas', 'Residential', '100-010', 0.00, 9871.00, 9871.00, 30.00, 30.00, 296130.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 296160.00),
(30, 'RJCWB011', 'Allend Andaya', 'Cumba, Lian Batangas', 'Commercial', '100-011', 0.00, 1.00, 1.00, 30.00, 100.00, 30.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 130.00),
(31, 'RJCWB012', 'Angela Mae Gutierrez', 'Biga Calatagan Batangas', 'Industrial', '100-012', 0.00, 120.00, 120.00, 30.00, 300.00, 3600.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3900.00),
(32, 'RJCWB013', 'Ashley Mae Aseron', 'Balitoc Calatagan Batangas', 'Commercial', '100-013', 0.00, 561.00, 561.00, 30.00, 100.00, 16830.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 16930.00),
(33, 'RJCWB015', 'Charish Bea Calinawan', 'Kapito Lian Batangas', 'Industrial', '100-015', 0.00, 132.00, 132.00, 30.00, 300.00, 3960.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 4260.00),
(34, 'RJCWB016', 'Charlie Magyaya', 'Matabungkay Lian Batangas', 'Institutional', '100-016', 0.00, 43.00, 43.00, 30.00, 50.00, 1290.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 1340.00),
(35, 'RJCWB017', 'Chilsea Mendoza', 'Balibago Lian Batangas', 'Residential', '100-017', 0.00, 31223.00, 31223.00, 30.00, 30.00, 936690.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 936720.00),
(36, 'RJCWB018', 'Christian Anchorez', 'Rizal TUY Batangas', 'Commercial', '100-018', 0.00, 123.00, 123.00, 30.00, 100.00, 3690.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3790.00),
(37, 'RJCWB019', 'Pauline Baldos', 'Brgy 2 TUY Batangas', 'Residential', '100-019', 0.00, 56.00, 56.00, 30.00, 30.00, 1680.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 1710.00),
(38, 'RJCWB020', 'Clinton John Masongsong', 'Malahuratan Lian Batangas', 'Commercial', '100-020', 0.00, 456.00, 456.00, 30.00, 100.00, 13680.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 13780.00),
(39, 'RJCWB021', 'Nikko Causapin', 'Palincaro Balayan Batangas', 'Residential', '100-021', 0.00, 87.00, 87.00, 30.00, 30.00, 2610.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 2640.00),
(40, 'RJCWB022', 'Jovelyn Dalisay', 'Patugo Balayan Batangas', 'Commercial', '100-022', 0.00, 321.00, 321.00, 30.00, 100.00, 9630.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 9730.00),
(41, 'RJCWB023', 'Kath Guevarra', 'Brgy Magabe Balayan Batangas', 'Commercial', '100-023', 0.00, 98.00, 98.00, 30.00, 100.00, 2940.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3040.00),
(42, 'RJCWB024', 'Erich Bondok', 'Palikpikan Balayan Batangas', 'Residential', '100-024', 0.00, 104.00, 104.00, 30.00, 30.00, 3120.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3150.00),
(43, 'RJCWB025', 'Michael Tolosa', 'Brgy 4 Calatagan Batangas', 'Commercial', '100-025', 0.00, 213.00, 213.00, 30.00, 100.00, 6390.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 6490.00),
(44, 'RJCWB026', 'Paolo Ramsey', 'Hukay Calatagan Batangas', 'Residential', '100-026', 0.00, 2133.00, 2133.00, 30.00, 30.00, 63990.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 64020.00),
(45, 'RJCWB027', 'Karl David Diago', 'Brgy 5 Nasugbu Batangas', 'Commercial', '100-027', 0.00, 2334.00, 2334.00, 30.00, 100.00, 70020.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 70120.00),
(46, 'RJCWB028', 'Bea Angela Abasquez', 'Wawa Nasugbu Batangas', 'Industrial', '100-028', 0.00, 433.00, 433.00, 30.00, 300.00, 12990.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 13290.00),
(47, 'RJCWB029', 'Julius Sari', 'Lumbangan Nasugbu Batangas', 'Commercial', '100-029', 0.00, 653.00, 653.00, 30.00, 100.00, 19590.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 19690.00),
(48, 'RJCWB030', 'Lance Malata', 'Brgy 11 Nasugbu Batangas', 'Commercial', '100-030', 0.00, 109.00, 109.00, 30.00, 100.00, 3270.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3370.00);

-- --------------------------------------------------------

--
-- Table structure for table `clientpayment`
--

CREATE TABLE `clientpayment` (
  `id` int(11) NOT NULL,
  `Account_No` varchar(30) NOT NULL,
  `Account_Name` varchar(30) NOT NULL,
  `Total_Amount_Due` double(10,2) NOT NULL,
  `Payment_Method` varchar(30) NOT NULL,
  `Payment_Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clientpayment`
--

INSERT INTO `clientpayment` (`id`, `Account_No`, `Account_Name`, `Total_Amount_Due`, `Payment_Method`, `Payment_Date`) VALUES
(33, 'RJCWB001', 'Ryan Joseph Acbang', 330.00, 'Cash', '2024-11-29'),
(34, 'RJCWB002', 'Chedric Bascoguin', 930.00, 'Cash', '2024-11-29'),
(35, 'RJCWB003', 'Ariane kaye Vecinal', 5730.00, 'Cash', '2024-11-29'),
(36, 'RJCWB004', 'Jerard Verroya', 17040.00, 'Cash', '2024-11-29'),
(37, 'RJCWB005', 'John Laurence Dino', 29700.00, 'Cash', '2024-11-29'),
(38, 'RJCWB006', 'Vince Mendoza', 30000.00, 'Cash', '2024-11-29'),
(39, 'RJCWB007', 'Prince Jude Arcayos', 7680.00, 'Cash', '2024-11-29'),
(40, 'RJCWB008', 'Carl Andrey Neri', 29670.00, 'Cash', '2024-11-29'),
(41, 'RJCWB009', 'Noreen Grace Gonzales', 273330.00, 'Cash', '2024-11-29'),
(42, 'RJCWB010', 'Andrew Tentia', 296160.00, 'Cash', '2024-11-29'),
(43, 'RJCWB011', 'Allend Andaya', 130.00, 'Cash', '2024-11-29'),
(44, 'RJCWB012', 'Angela Mae Gutierrez', 3900.00, 'Cash', '2024-11-29'),
(45, 'RJCWB013', 'Ashley Mae Aseron', 16930.00, 'Cash', '2024-11-29'),
(46, 'RJCWB014', 'Betti Chavez', 1200.00, 'Cash', '2024-11-29'),
(47, 'RJCWB015', 'Charish Bea Calinawan', 4260.00, 'Cash', '2024-11-29'),
(48, 'RJCWB016', 'Charlie Magyaya', 1340.00, 'Cash', '2024-11-29'),
(49, 'RJCWB017', 'Chilsea Mendoza', 936720.00, 'Cash', '2024-11-29'),
(50, 'RJCWB018', 'Christian Anchorez', 3790.00, 'Cash', '2024-11-29'),
(51, 'RJCWB019', 'Pauline Baldos', 1710.00, 'Cash', '2024-11-29'),
(52, 'RJCWB020', 'Clinton John Masongsong', 13780.00, 'Cash', '2024-11-29'),
(53, 'RJCWB021', 'Nikko Causapin', 2640.00, 'E-Payment', '2024-11-29'),
(54, 'RJCWB022', 'Jovelyn Dalisay', 9730.00, 'Cash', '2024-11-29'),
(55, 'RJCWB023', 'Kath Guevarra', 3040.00, 'Cash', '2024-11-29'),
(56, 'RJCWB024', 'Erich Bondok', 3150.00, 'Cash', '2024-11-29'),
(57, 'RJCWB025', 'Michael Tolosa', 6490.00, 'Cash', '2024-11-29'),
(58, 'RJCWB026', 'Paolo Ramsey', 64020.00, 'Cash', '2024-11-29'),
(59, 'RJCWB027', 'Karl David Diago', 70120.00, 'Cash', '2024-11-29'),
(60, 'RJCWB028', 'Bea Angela Abasquez', 13290.00, 'Cash', '2024-11-29'),
(61, 'RJCWB029', 'Julius Sari', 19690.00, 'Cash', '2024-11-29'),
(62, 'RJCWB030', 'Lance Malata', 3370.00, 'Cash', '2024-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `clientpaymentsales`
--

CREATE TABLE `clientpaymentsales` (
  `id` int(11) NOT NULL,
  `Account_No` varchar(30) NOT NULL,
  `Account_Name` varchar(30) NOT NULL,
  `Total_Amount_Due` decimal(10,2) NOT NULL,
  `Payment_Method` varchar(30) NOT NULL,
  `Payment_Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clientpaymentsales`
--

INSERT INTO `clientpaymentsales` (`id`, `Account_No`, `Account_Name`, `Total_Amount_Due`, `Payment_Method`, `Payment_Date`) VALUES
(32, 'RJCWB001', 'Ryan Joseph Acbang', 330.00, 'Cash', '2024-11-29'),
(33, 'RJCWB002', 'Chedric Bascoguin', 930.00, 'Cash', '2024-11-29'),
(34, 'RJCWB003', 'Ariane kaye Vecinal', 5730.00, 'Cash', '2024-11-29'),
(35, 'RJCWB004', 'Jerard Verroya', 17040.00, 'Cash', '2024-11-29'),
(36, 'RJCWB005', 'John Laurence Dino', 29700.00, 'Cash', '2024-11-29'),
(37, 'RJCWB006', 'Vince Mendoza', 30000.00, 'Cash', '2024-11-29'),
(38, 'RJCWB007', 'Prince Jude Arcayos', 7680.00, 'Cash', '2024-11-29'),
(39, 'RJCWB008', 'Carl Andrey Neri', 29670.00, 'Cash', '2024-11-29'),
(40, 'RJCWB009', 'Noreen Grace Gonzales', 273330.00, 'Cash', '2024-11-29'),
(41, 'RJCWB010', 'Andrew Tentia', 296160.00, 'Cash', '2024-11-29'),
(42, 'RJCWB011', 'Allend Andaya', 130.00, 'Cash', '2024-11-29'),
(43, 'RJCWB012', 'Angela Mae Gutierrez', 3900.00, 'Cash', '2024-11-29'),
(44, 'RJCWB013', 'Ashley Mae Aseron', 16930.00, 'Cash', '2024-11-29'),
(45, 'RJCWB014', 'Betti Chavez', 1200.00, 'Cash', '2024-11-29'),
(46, 'RJCWB015', 'Charish Bea Calinawan', 4260.00, 'Cash', '2024-11-29'),
(47, 'RJCWB016', 'Charlie Magyaya', 1340.00, 'Cash', '2024-11-29'),
(48, 'RJCWB017', 'Chilsea Mendoza', 936720.00, 'Cash', '2024-11-29'),
(49, 'RJCWB018', 'Christian Anchorez', 3790.00, 'Cash', '2024-11-29'),
(50, 'RJCWB019', 'Pauline Baldos', 1710.00, 'Cash', '2024-11-29'),
(51, 'RJCWB020', 'Clinton John Masongsong', 13780.00, 'Cash', '2024-11-29'),
(52, 'RJCWB021', 'Nikko Causapin', 2640.00, 'E-Payment', '2024-11-29'),
(53, 'RJCWB022', 'Jovelyn Dalisay', 9730.00, 'Cash', '2024-11-29'),
(54, 'RJCWB023', 'Kath Guevarra', 3040.00, 'Cash', '2024-11-29'),
(55, 'RJCWB024', 'Erich Bondok', 3150.00, 'Cash', '2024-11-29'),
(56, 'RJCWB025', 'Michael Tolosa', 6490.00, 'Cash', '2024-11-29'),
(57, 'RJCWB026', 'Paolo Ramsey', 64020.00, 'Cash', '2024-11-29'),
(58, 'RJCWB027', 'Karl David Diago', 70120.00, 'Cash', '2024-11-29'),
(59, 'RJCWB028', 'Bea Angela Abasquez', 13290.00, 'Cash', '2024-11-29'),
(60, 'RJCWB029', 'Julius Sari', 19690.00, 'Cash', '2024-11-29'),
(61, 'RJCWB030', 'Lance Malata', 3370.00, 'Cash', '2024-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `generatedreceipt`
--

CREATE TABLE `generatedreceipt` (
  `receiptID` int(11) NOT NULL,
  `Account_No` varchar(100) NOT NULL,
  `Account_Name` varchar(100) NOT NULL,
  `Service_Address` varchar(100) NOT NULL,
  `Property` varchar(100) NOT NULL,
  `Meter_No` varchar(100) NOT NULL,
  `Previous_Reading` decimal(10,2) NOT NULL,
  `Current_Reading` decimal(10,2) NOT NULL,
  `Consumption` decimal(10,2) NOT NULL,
  `Rate` decimal(10,2) NOT NULL,
  `Tax_Amount` decimal(10,2) NOT NULL,
  `Base_Charge` decimal(10,2) NOT NULL,
  `Penalty_Charge` decimal(10,2) NOT NULL,
  `Billing_Period_Start` date NOT NULL,
  `Billing_Period_End` date NOT NULL,
  `Due_Date` date NOT NULL,
  `Total_Amount_Due` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `generatedreceipt`
--

INSERT INTO `generatedreceipt` (`receiptID`, `Account_No`, `Account_Name`, `Service_Address`, `Property`, `Meter_No`, `Previous_Reading`, `Current_Reading`, `Consumption`, `Rate`, `Tax_Amount`, `Base_Charge`, `Penalty_Charge`, `Billing_Period_Start`, `Billing_Period_End`, `Due_Date`, `Total_Amount_Due`) VALUES
(77, 'RJCWB001', 'Ryan Joseph Acbang', 'Sambungan, Calatagan, Batangas', 'Residential', '100-001', 0.00, 10.00, 10.00, 30.00, 30.00, 300.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 330.00),
(78, 'RJCWB002', 'Chedric Bascoguin', 'Lucsuhin, Calatagan, Batangas', 'Residential', '100-002', 0.00, 30.00, 30.00, 30.00, 30.00, 900.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 930.00),
(79, 'RJCWB003', 'Ariane kaye Vecinal', 'Biga, Calatagan, Batangas', 'Residential', '100-003', 0.00, 190.00, 190.00, 30.00, 30.00, 5700.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 5730.00),
(80, 'RJCWB004', 'Jerard Verroya', 'Gulod, Calatagan, Batangas', 'Residential', '100-004', 0.00, 567.00, 567.00, 30.00, 30.00, 17010.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 17040.00),
(81, 'RJCWB005', 'John Laurence Dino', 'Brgy. 3, Calatagan, Batangas', 'Residential', '100-005', 0.00, 990.00, 990.00, 30.00, 30.00, 29700.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 29730.00),
(82, 'RJCWB006', 'Vince Mendoza', 'Carretunan, Calatagan, Batangas', 'Residential', '100-006', 0.00, 999.00, 999.00, 30.00, 30.00, 29970.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 30000.00),
(83, 'RJCWB007', 'Prince Jude Arcayos', 'Lucsuhin, Calatagan, Batangas', 'Residential', '100-007', 0.00, 255.00, 255.00, 30.00, 30.00, 7650.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 7680.00),
(84, 'RJCWB008', 'Carl Andrey Neri', 'Brgy. 2 Calatagan Batangas', 'Residential', '100-008', 0.00, 988.00, 988.00, 30.00, 30.00, 29640.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 29670.00),
(85, 'RJCWB009', 'Noreen Grace Gonzales', 'Biga Calatagan Batangas', 'Residential', '100-009', 0.00, 9110.00, 9110.00, 30.00, 30.00, 273300.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 273330.00),
(86, 'RJCWB010', 'Andrew Tentia', 'Talisay Calatagan Batangas', 'Residential', '100-010', 0.00, 9871.00, 9871.00, 30.00, 30.00, 296130.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 296160.00),
(87, 'RJCWB011', 'Allend Andaya', 'Cumba, Lian Batangas', 'Commercial', '100-011', 0.00, 1.00, 1.00, 30.00, 100.00, 30.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 130.00),
(88, 'RJCWB012', 'Angela Mae Gutierrez', 'Biga Calatagan Batangas', 'Industrial', '100-012', 0.00, 120.00, 120.00, 30.00, 300.00, 3600.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3900.00),
(89, 'RJCWB013', 'Ashley Mae Aseron', 'Balitoc Calatagan Batangas', 'Commercial', '100-013', 0.00, 561.00, 561.00, 30.00, 100.00, 16830.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 16930.00),
(90, 'RJCWB015', 'Charish Bea Calinawan', 'Kapito Lian Batangas', 'Industrial', '100-015', 0.00, 132.00, 132.00, 30.00, 300.00, 3960.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 4260.00),
(91, 'RJCWB016', 'Charlie Magyaya', 'Matabungkay Lian Batangas', 'Institutional', '100-016', 0.00, 43.00, 43.00, 30.00, 50.00, 1290.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 1340.00),
(92, 'RJCWB017', 'Chilsea Mendoza', 'Balibago Lian Batangas', 'Residential', '100-017', 0.00, 31223.00, 31223.00, 30.00, 30.00, 936690.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 936720.00),
(93, 'RJCWB018', 'Christian Anchorez', 'Rizal TUY Batangas', 'Commercial', '100-018', 0.00, 123.00, 123.00, 30.00, 100.00, 3690.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3790.00),
(94, 'RJCWB019', 'Pauline Baldos', 'Brgy 2 TUY Batangas', 'Residential', '100-019', 0.00, 56.00, 56.00, 30.00, 30.00, 1680.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 1710.00),
(95, 'RJCWB020', 'Clinton John Masongsong', 'Malahuratan Lian Batangas', 'Commercial', '100-020', 0.00, 456.00, 456.00, 30.00, 100.00, 13680.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 13780.00),
(96, 'RJCWB021', 'Nikko Causapin', 'Palincaro Balayan Batangas', 'Residential', '100-021', 0.00, 87.00, 87.00, 30.00, 30.00, 2610.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 2640.00),
(97, 'RJCWB022', 'Jovelyn Dalisay', 'Patugo Balayan Batangas', 'Commercial', '100-022', 0.00, 321.00, 321.00, 30.00, 100.00, 9630.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 9730.00),
(98, 'RJCWB023', 'Kath Guevarra', 'Brgy Magabe Balayan Batangas', 'Commercial', '100-023', 0.00, 98.00, 98.00, 30.00, 100.00, 2940.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3040.00),
(99, 'RJCWB024', 'Erich Bondok', 'Palikpikan Balayan Batangas', 'Residential', '100-024', 0.00, 104.00, 104.00, 30.00, 30.00, 3120.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3150.00),
(100, 'RJCWB025', 'Michael Tolosa', 'Brgy 4 Calatagan Batangas', 'Commercial', '100-025', 0.00, 213.00, 213.00, 30.00, 100.00, 6390.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 6490.00),
(101, 'RJCWB026', 'Paolo Ramsey', 'Hukay Calatagan Batangas', 'Residential', '100-026', 0.00, 2133.00, 2133.00, 30.00, 30.00, 63990.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 64020.00),
(102, 'RJCWB027', 'Karl David Diago', 'Brgy 5 Nasugbu Batangas', 'Commercial', '100-027', 0.00, 2334.00, 2334.00, 30.00, 100.00, 70020.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 70120.00),
(103, 'RJCWB028', 'Bea Angela Abasquez', 'Wawa Nasugbu Batangas', 'Industrial', '100-028', 0.00, 433.00, 433.00, 30.00, 300.00, 12990.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 13290.00),
(104, 'RJCWB029', 'Julius Sari', 'Lumbangan Nasugbu Batangas', 'Commercial', '100-029', 0.00, 653.00, 653.00, 30.00, 100.00, 19590.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 19690.00),
(105, 'RJCWB030', 'Lance Malata', 'Brgy 11 Nasugbu Batangas', 'Commercial', '100-030', 0.00, 109.00, 109.00, 30.00, 100.00, 3270.00, 0.00, '2024-10-29', '2024-11-29', '2024-11-29', 3370.00);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL,
  `Full_Name` varchar(30) NOT NULL,
  `Address` varchar(30) NOT NULL,
  `Email_Address` varchar(30) NOT NULL,
  `Contact_Number` int(20) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Create_Password` varchar(30) NOT NULL,
  `Confirm_Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `Full_Name`, `Address`, `Email_Address`, `Contact_Number`, `Username`, `Create_Password`, `Confirm_Password`) VALUES
(6, 'Ryan Joseph Acbang', 'Sambungan,Calatagan, Batangas', 'ryan@gmail.com', 9876543, 'ryan', '123456', '123456'),
(7, 'Chedric Bascoguin', 'Lucsuhin, Calatagan, Batangas', 'bascoguin@gmail.com', 934213344, 'chedric', '123456', '123456'),
(8, 'Ariane Kaye Vecinal', 'Biga, Calatagan, Batangas', 'Kaye@gmail.com', 987654321, 'ariane', '123456', '123456'),
(9, 'Jerard Verroya', 'Gulod, Calatagan, Batangas', 'paul@gmail.com', 987654321, 'jerard', '123456', '123456'),
(10, 'John Laurence Dino', 'Brgy 3, Calatagan Batangas', 'jao@gmail.com', 987654322, 'jao', '123456', '123456'),
(11, 'Vince Mendoza', 'Carretunan, Calatagan, Bats', 'vince@gmail.com', 987654323, 'vince', '123456', '123456'),
(12, 'Prince Arcayos', 'Lucsuhin, Calatagan, Batangas', 'pj20@gmail.com', 987654324, 'prince', '123456', '123456'),
(13, 'Carl Andrey Neri', 'Brgy 2 Calatagan, Batangas', 'carl@gmail.com', 987654325, 'carl', '123456', '123456'),
(14, 'Andrew Tentia', 'Talisay Calatagan Batangas', 'andrew@gmail.com', 987654326, 'andrew', '123456', '123456'),
(15, 'Allend Andaya', 'Cumba Lian Batangas', 'allend@gmail.com', 987654327, 'allend', '123456', '123456'),
(16, 'Angela Gutierrez', 'Biga, Cal. Bats.', 'angela@gmailcom', 987654328, 'angela', '123456', '123456'),
(17, 'Ashley Aseron', 'Balitoc Cal. Bats.', 'aseron@gmail.com', 987654329, 'ashley', '123456', '123456'),
(18, 'Betti Chavez', 'Humayingan Lian Bats.', 'bet@gmail.com', 987654320, 'betti', '123456', '123456'),
(19, 'Charish Calinawan', 'Kapito Lian Bats.', 'char@gmail.com', 987654311, 'charish', '123456', '123456'),
(20, 'Charlie Magyaya', 'Matabungkay Lian Bats', 'charlie@gmail.com', 987654312, 'charlie', '123456', '123456'),
(21, 'Chilsea Mendoza', 'Balibago, Lian, Batangas', 'chi@gmail.com', 98765434, 'chilsea', '123456', '123456'),
(22, 'Christian Anchorez', 'Rizal, Tuy, Batangas', 'christian@gmail.com', 987654567, 'christian', '123456', '123456'),
(23, 'Pauline Baldoz', 'Brgy 2 Tuy Batangas', 'pau@gmail.com', 987655687, 'pauline', '123456', '123456'),
(24, 'Clinton John Masongsong', 'Malahuratan, lian, Batangas', 'clint@gmail.com', 98765567, 'clinton', '123456', '123456'),
(25, 'Nikko Causapin', 'Palincaro, Balayan, batangas', 'nikko@gmail.com', 985677654, 'nikko', '123456', '123456'),
(26, 'Jovelyn dalisay', 'Patugo, Balayan, Batangas', 'jov@gmail.com', 98765456, 'jovelyn', '123456', '123456'),
(27, 'Kath guevarra', 'Magabe, balayan Batangas', 'kath@gmail.com', 987655676, 'kath', '123456', '123456'),
(28, 'Erich Bondok', 'Palikpikan Balayan Batangas', 'erich@gmail.com', 987654345, 'erich', '123456', '123456'),
(29, 'Michael Tolosa', 'Brgy 4 Calatagan Batangas', 'michael@gmail.com', 987654345, 'michael', '123456', '123456'),
(30, 'Paolo Ramsey', 'Hukay Cal. Bats.', 'ramsey@gmail.com', 98765434, 'paolo', '123456', '123456'),
(31, 'Karl David Diago', 'Brgy 5 Nasugbu Bats.', 'diago@gmail.com', 987654565, 'karl', '123456', '123456'),
(32, 'Bea Angela Abaquez', 'Wawa Nasugbu Bats.', 'bea@gmail.com', 987654565, 'bea', '123456', '123456'),
(33, 'Julius Sari', 'Lumbangan Nasugbu Bats.', 'jul@gmail.com', 98765434, 'julius', '123456', '123456'),
(34, 'Lance Malata', 'Brgy 11 Nasugbu Bats.', 'lance@gmail.com', 987654357, 'lance', '123456', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientinfo`
--
ALTER TABLE `clientinfo`
  ADD PRIMARY KEY (`Account_No`),
  ADD UNIQUE KEY `RJCWB001` (`Account_No`);

--
-- Indexes for table `clientledger`
--
ALTER TABLE `clientledger`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientpayment`
--
ALTER TABLE `clientpayment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientpaymentsales`
--
ALTER TABLE `clientpaymentsales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `generatedreceipt`
--
ALTER TABLE `generatedreceipt`
  ADD PRIMARY KEY (`receiptID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientledger`
--
ALTER TABLE `clientledger`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `clientpayment`
--
ALTER TABLE `clientpayment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `clientpaymentsales`
--
ALTER TABLE `clientpaymentsales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `generatedreceipt`
--
ALTER TABLE `generatedreceipt`
  MODIFY `receiptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
