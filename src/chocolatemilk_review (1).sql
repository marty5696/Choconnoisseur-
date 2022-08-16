-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2022 at 10:00 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chocolatemilk_review`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `account_id` int(5) NOT NULL,
  `last` char(20) DEFAULT NULL,
  `first` char(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_id`, `last`, `first`, `address`, `city`, `state`, `email`) VALUES
(6, 'Grosso', 'Martin', '109 W 1st Street', 'Debary', 'FL', 'martygrosso@gmail.com'),
(7, 'choc', 'king', 'king choc lane', 'Orlando', 'FL', 'kingchoc@gmail.com'),
(10, 'Martinez', 'Isaiah', 'lives close lane', 'Orlando', 'Fl', 'isaiahlivesclose@gmail.com'),
(12, 'Oville', 'Kelly', '234 hello lane', 'Orlando', 'FL', 'KO@gmail.com'),
(14, 'Milk', 'Chocolate', 'Chocolate Milk Lane', 'Orlando', 'FL', 'chocolatemilk@gmail.com'),
(16, 'Williams', 'Ash', 'Peach Lane', 'Orlando', 'FL', 'ashpeach@gmail.com'),
(17, 'Clarson', 'Shawn', 'Shawn Lane ', 'Orlando', 'FL', 'shawnlane@gmail.com'),
(18, 'Grosso', 'Martin', '108 Bayside Lane', 'Orlando', 'FL', 'bayside@gmail.com'),
(19, 'Grosso', 'Martin', '234 Bayside Lane', 'Orlando', 'FL', 'bayside@gmail.com'),
(20, 'Haneski', 'Shawn', '101 Music Lane', 'Orlando', 'FL', 'music@gmail.com'),
(22, 'Clarson', 'Kyle', 'Music Row', 'Atlanta ', 'GA', 'Atlantamusic@gmail.com'),
(23, 'Haneski', 'Marty', '234 music lane', 'Sanford', 'FL', 'musiclane@gmail.com'),
(24, 'a;lwkejf', 'a;wlekfj', 'musci', 'sanf', 'fl', 'a;lkfj'),
(25, 'hello', 'there', 'hello there lane ', 'orlando', 'fl', 'hellothere@gmail.com'),
(27, 'Smith', 'Joe', '456 Chocolate Street', 'Miami', 'FL', 'miamiChocFL@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `item_id` int(10) NOT NULL,
  `item_type` varchar(15) DEFAULT NULL,
  `item_cost` decimal(10,2) DEFAULT NULL,
  `category` varchar(15) DEFAULT NULL,
  `size` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `merchandise`
--

INSERT INTO `merchandise` (`item_id`, `item_type`, `item_cost`, `category`, `size`) VALUES
(3, 'shirt', '8.00', 'Clothing', 'S'),
(6, 'Clothing', '18.00', 'Shirt', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `merchsales`
--

CREATE TABLE `merchsales` (
  `sale_id` int(20) NOT NULL,
  `date_sold` date DEFAULT NULL,
  `sale_amount` varchar(10) DEFAULT NULL,
  `item_id` int(10) DEFAULT NULL,
  `account_id` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `merchsales`
--

INSERT INTO `merchsales` (`sale_id`, `date_sold`, `sale_amount`, `item_id`, `account_id`) VALUES
(4, '2020-01-15', '2.0', 2, 1200),
(5, '2022-02-20', '12.00', 3, NULL),
(6, '2022-03-13', '15.0', 2, 1000),
(8, '2022-03-03', '20.0', 6, 1000),
(9, '2022-03-03', '10.0', 2, 1000),
(13, '2022-01-01', '2.0', 6, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `productreviews`
--

CREATE TABLE `productreviews` (
  `review_numbs` int(10) NOT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `description_milk` varchar(30) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `location_id` varchar(30) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `business_name` varchar(25) DEFAULT NULL,
  `grocery_store` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productreviews`
--

INSERT INTO `productreviews` (`review_numbs`, `brand`, `description_milk`, `cost`, `location_id`, `country`, `state`, `city`, `business_name`, `grocery_store`) VALUES
(2, 'TG LEE', 'Does Job', '2.00', '407', 'United States', 'FL', 'Orlando', 'Wal-Mart', 'Y'),
(3, 'Shamrock Farms', 'Does Job', '1.75', '407', 'United States', 'FL', 'Orlando', 'Dollar General', 'Y'),
(4, 'Cricket', 'Thick Bold Great', '2.00', '456.0', 'United States', 'Texas', 'Dallas', 'Kroger', 'Kroger'),
(6, 'Gustafson', 'AMAZINGLY DELICIOUS', '2.75', '386.0', 'United States', 'FL', 'Orange City', 'Wal Mart', 'Y'),
(7, 'Gustafson', 'Amzing', '2.89', '954', 'United Staetes', 'FL', 'Orlando', 'Publix', 'Y'),
(9, 'TG LEE', 'Does the job', '2.00', '954', 'United States', 'FL', 'Miami', 'Winn Dixie', 'Y'),
(14, 'Promise Land', 'SO GOOD AND PERFECT', '2.00', '30188', 'United States', 'GA', 'Atlanta', 'Publix', 'Y');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `merchandise`
--
ALTER TABLE `merchandise`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `merchsales`
--
ALTER TABLE `merchsales`
  ADD PRIMARY KEY (`sale_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `productreviews`
--
ALTER TABLE `productreviews`
  ADD PRIMARY KEY (`review_numbs`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `account_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `merchandise`
--
ALTER TABLE `merchandise`
  MODIFY `item_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `merchsales`
--
ALTER TABLE `merchsales`
  MODIFY `sale_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `productreviews`
--
ALTER TABLE `productreviews`
  MODIFY `review_numbs` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
