-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 26 Ara 2022, 21:29:23
-- Sunucu sürümü: 10.4.27-MariaDB
-- PHP Sürümü: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `housemate`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customers`
--

CREATE TABLE `customers` (
  `customer_id` bigint(20) NOT NULL,
  `customer_age` int(11) DEFAULT NULL,
  `customer_department` varchar(255) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_gender` varchar(255) DEFAULT NULL,
  `customer_grade` int(11) DEFAULT NULL,
  `customer_hometown` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_password` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `customer_surname` varchar(255) DEFAULT NULL,
  `customer_username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `customers`
--

INSERT INTO `customers` (`customer_id`, `customer_age`, `customer_department`, `customer_email`, `customer_gender`, `customer_grade`, `customer_hometown`, `customer_name`, `customer_password`, `customer_phone`, `customer_surname`, `customer_username`) VALUES
(1, 22, 'ceng', 'deneme@gmail.com', 'male', 2, 'Bayburt', 'Onur', '1234', '0566', 'Keskin', 'hnor');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `houses`
--

CREATE TABLE `houses` (
  `house_id` bigint(20) NOT NULL,
  `floor` int(11) DEFAULT NULL,
  `furnished` varchar(255) DEFAULT NULL,
  `heat_resource` varchar(255) DEFAULT NULL,
  `house_address` varchar(255) DEFAULT NULL,
  `house_type` varchar(255) DEFAULT NULL,
  `internet_paved` varchar(255) DEFAULT NULL,
  `rent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `houses`
--

INSERT INTO `houses` (`house_id`, `floor`, `furnished`, `heat_resource`, `house_address`, `house_type`, `internet_paved`, `rent`) VALUES
(1, 2, 'yes', 'yes', 'yeni mahalle', '5+2', 'yes', 3000),
(2, 2, 'yes', 'yes', 'ikinci mahalle', '5+2', 'yes', 4000),
(12, 2, 'yes', 'yes', 'yeşil mahalle', '5+2', 'yes', 3000),
(13, 2, 'yes', 'yes', 'mavi mahalle', '5+2', 'yes', 3000);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `house_customer`
--

CREATE TABLE `house_customer` (
  `relation_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `house_owners`
--

CREATE TABLE `house_owners` (
  `owner_id` bigint(20) NOT NULL,
  `owner_age` int(11) DEFAULT NULL,
  `owner_department` varchar(255) DEFAULT NULL,
  `owner_gender` varchar(255) DEFAULT NULL,
  `owner_grade` int(11) DEFAULT NULL,
  `owner_hometown` varchar(255) DEFAULT NULL,
  `owner_mail` varchar(255) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `owner_password` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(255) DEFAULT NULL,
  `owner_surname` varchar(255) DEFAULT NULL,
  `owner_username` varchar(255) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `house_owners`
--

INSERT INTO `house_owners` (`owner_id`, `owner_age`, `owner_department`, `owner_gender`, `owner_grade`, `owner_hometown`, `owner_mail`, `owner_name`, `owner_password`, `owner_phone`, `owner_surname`, `owner_username`, `house_id`) VALUES
(1, 45, 'machine', 'female', 3, 'giresunn', 'mail', 'Arda', '6666', 'changed dep2', 'Changed Surname', 'Cemil', 1),
(3, 0, '', '', 0, '', 'me@gmail.com', 'Mesut', '1234', '', 'Türkmen', 'metu', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `luxury_attr_of_house`
--

CREATE TABLE `luxury_attr_of_house` (
  `id` bigint(20) NOT NULL,
  `count_of_bathroom` int(11) DEFAULT NULL,
  `count_of_bedroom` int(11) DEFAULT NULL,
  `count_of_owner` int(11) DEFAULT NULL,
  `count_of_salon` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `furnished` varchar(255) DEFAULT NULL,
  `heat_resource` varchar(255) DEFAULT NULL,
  `internet_paved` varchar(255) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `luxury_attr_of_house`
--

INSERT INTO `luxury_attr_of_house` (`id`, `count_of_bathroom`, `count_of_bedroom`, `count_of_owner`, `count_of_salon`, `floor`, `furnished`, `heat_resource`, `internet_paved`, `house_id`) VALUES
(14, 1, 5, 1, 2, 2, 'yes', 'yes', 'yes', 13);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `model_attributes`
--

CREATE TABLE `model_attributes` (
  `id` bigint(20) NOT NULL,
  `gpa` double DEFAULT NULL,
  `having_pet` bit(1) DEFAULT NULL,
  `luxury` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `renting_duration` int(11) DEFAULT NULL,
  `sleep_time` varchar(255) DEFAULT NULL,
  `smoking` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `model_of_customer`
--

CREATE TABLE `model_of_customer` (
  `id` bigint(20) NOT NULL,
  `gpa` double DEFAULT NULL,
  `having_pet` bit(1) DEFAULT NULL,
  `luxury` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `renting_duration` int(11) DEFAULT NULL,
  `sleep_time` varchar(255) DEFAULT NULL,
  `smoking` bit(1) DEFAULT NULL,
  `class_of_customer` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `model_of_customer`
--

INSERT INTO `model_of_customer` (`id`, `gpa`, `having_pet`, `luxury`, `price`, `renting_duration`, `sleep_time`, `smoking`, `class_of_customer`, `customer_id`) VALUES
(16, 3.5, b'0', 7, 2850, 20, '22.30', b'1', NULL, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `model_of_house`
--

CREATE TABLE `model_of_house` (
  `id` bigint(20) NOT NULL,
  `gpa` double DEFAULT NULL,
  `having_pet` bit(1) DEFAULT NULL,
  `luxury` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `renting_duration` int(11) DEFAULT NULL,
  `sleep_time` varchar(255) DEFAULT NULL,
  `smoking` bit(1) DEFAULT NULL,
  `class_of_house` varchar(255) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `model_of_house`
--

INSERT INTO `model_of_house` (`id`, `gpa`, `having_pet`, `luxury`, `price`, `renting_duration`, `sleep_time`, `smoking`, `class_of_house`, `house_id`) VALUES
(10, 2.95, b'1', 7, 3000, 20, '', b'1', NULL, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `model_of_house_owner`
--

CREATE TABLE `model_of_house_owner` (
  `id` bigint(20) NOT NULL,
  `gpa` double DEFAULT NULL,
  `having_pet` bit(1) DEFAULT NULL,
  `luxury` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `renting_duration` int(11) DEFAULT NULL,
  `sleep_time` varchar(255) DEFAULT NULL,
  `smoking` bit(1) DEFAULT NULL,
  `class_of_house_owner` varchar(255) DEFAULT NULL,
  `house_owner_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `model_of_house_owner`
--

INSERT INTO `model_of_house_owner` (`id`, `gpa`, `having_pet`, `luxury`, `price`, `renting_duration`, `sleep_time`, `smoking`, `class_of_house_owner`, `house_owner_id`) VALUES
(11, 2.5, b'0', 7, 3950, 20, '22.30', b'1', NULL, 1),
(17, 3.4, b'0', 7, 1000, 20, '22.30', b'1', NULL, 3);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Tablo için indeksler `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`house_id`);

--
-- Tablo için indeksler `house_customer`
--
ALTER TABLE `house_customer`
  ADD PRIMARY KEY (`relation_id`),
  ADD KEY `FKkvqdjbyr837vpo9nm0esb44eo` (`customer_id`),
  ADD KEY `FK4hq27vbfldq91w3ym50xs74s5` (`house_id`);

--
-- Tablo için indeksler `house_owners`
--
ALTER TABLE `house_owners`
  ADD PRIMARY KEY (`owner_id`),
  ADD KEY `FK5pjmjms9a6jvi6a1iag3xckjg` (`house_id`);

--
-- Tablo için indeksler `luxury_attr_of_house`
--
ALTER TABLE `luxury_attr_of_house`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf2i088jp7o7wl722xen75qjkd` (`house_id`);

--
-- Tablo için indeksler `model_attributes`
--
ALTER TABLE `model_attributes`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `model_of_customer`
--
ALTER TABLE `model_of_customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcqvkrauxbr9qmba7nrhdfghg9` (`customer_id`);

--
-- Tablo için indeksler `model_of_house`
--
ALTER TABLE `model_of_house`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoce91swdikstuyh29gkkmyp4d` (`house_id`);

--
-- Tablo için indeksler `model_of_house_owner`
--
ALTER TABLE `model_of_house_owner`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkq4m7m1n2bunxiu7oes7xsv6v` (`house_owner_id`);

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `house_customer`
--
ALTER TABLE `house_customer`
  ADD CONSTRAINT `FK4hq27vbfldq91w3ym50xs74s5` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`),
  ADD CONSTRAINT `FKkvqdjbyr837vpo9nm0esb44eo` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Tablo kısıtlamaları `house_owners`
--
ALTER TABLE `house_owners`
  ADD CONSTRAINT `FK5pjmjms9a6jvi6a1iag3xckjg` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`);

--
-- Tablo kısıtlamaları `luxury_attr_of_house`
--
ALTER TABLE `luxury_attr_of_house`
  ADD CONSTRAINT `FKf2i088jp7o7wl722xen75qjkd` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`);

--
-- Tablo kısıtlamaları `model_of_customer`
--
ALTER TABLE `model_of_customer`
  ADD CONSTRAINT `FKcqvkrauxbr9qmba7nrhdfghg9` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Tablo kısıtlamaları `model_of_house`
--
ALTER TABLE `model_of_house`
  ADD CONSTRAINT `FKoce91swdikstuyh29gkkmyp4d` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`);

--
-- Tablo kısıtlamaları `model_of_house_owner`
--
ALTER TABLE `model_of_house_owner`
  ADD CONSTRAINT `FKkq4m7m1n2bunxiu7oes7xsv6v` FOREIGN KEY (`house_owner_id`) REFERENCES `house_owners` (`owner_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
