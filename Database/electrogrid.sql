-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 12:28 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `emergency_service`
--

CREATE TABLE `emergency_service` (
  `EService_ID` int(11) NOT NULL,
  `Electricity_Meter_Failure` varchar(30) NOT NULL,
  `Other_Issue` varchar(30) NOT NULL,
  `Requested_Date` date NOT NULL,
  `Required_Time_Period` varchar(100) NOT NULL,
  `Phone_Number` int(10) NOT NULL,
  `Problem_Description` varchar(250) NOT NULL,
  `Address` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emergency_service`
--

INSERT INTO `emergency_service` (`EService_ID`, `Electricity_Meter_Failure`, `Other_Issue`, `Requested_Date`, `Required_Time_Period`, `Phone_Number`, `Problem_Description`, `Address`) VALUES
(1, 'yes', 'NO', '2021-09-14', 'within 12 hours', 748889996, 'Electricity meter has burned suddenly', 'No:23,Colombo 03'),
(2, 'No', 'Yes', '2022-02-26', 'within three days', 789631235, 'There is a problem in main switch', 'Salmal Mawatha,Rathmalana'),
(5, 'yes', 'NO', '2022-04-16', 'within 02 hours', 714569328, 'Our electricity meter has burned as a result of thundering', 'No:23,Palawaththa,Baththaramulla'),
(10, 'Yes', 'No', '2022-03-23', 'within a week hours', 751472583, 'Our electricity meter is heating alot', 'No:25/4D,Salmal Road,Mathara'),
(18, 'Yes', 'No', '2022-04-10', 'within 05 hours', 714564568, 'Our electricity meter has burned suddenly and destroyed', 'No:45/1A,Temple Road,Kegalle'),
(30, 'yes', 'no', '2022-05-10', 'tommorow', 751422413, 'burned', 'N0:23,colombo'),
(32, 'yes', 'no', '2022-03-15', 'within_today', 711233212, 'EletricityMeterDoesNotWorkProperly', 'No%3A25%2F1%2CRathmalana');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emergency_service`
--
ALTER TABLE `emergency_service`
  ADD PRIMARY KEY (`EService_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emergency_service`
--
ALTER TABLE `emergency_service`
  MODIFY `EService_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
