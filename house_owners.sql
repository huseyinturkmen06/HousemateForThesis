-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 24 Ara 2022, 20:56:26
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
-- Tablo için tablo yapısı `house_owners`
--

CREATE TABLE `house_owners` (
  `owner_id` bigint(20) NOT NULL,
  `owner_age` int(11) DEFAULT NULL,
  `owner_department` varchar(255) DEFAULT NULL,
  `owner_gender` varchar(255) DEFAULT NULL,
  `owner_grade` varchar(255) DEFAULT NULL,
  `owner_hometown` varchar(255) DEFAULT NULL,
  `owner_mail` varchar(255) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(255) DEFAULT NULL,
  `owner_surname` varchar(255) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL,
  `owner_password` varchar(255) DEFAULT NULL,
  `owner_username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `house_owners`
--

INSERT INTO `house_owners` (`owner_id`, `owner_age`, `owner_department`, `owner_gender`, `owner_grade`, `owner_hometown`, `owner_mail`, `owner_name`, `owner_phone`, `owner_surname`, `house_id`, `owner_password`, `owner_username`) VALUES
(1, 45, 'machine', 'female', '3', 'giresunn', 'mail', 'Changed2 Name', 'changed dep2', 'Changed Surname', 2, '1234', 'Cemil'),
(2, 20, 'changed dep', 'Male', '4', 'izmir', 'changed@email', 'Hasannn', '0534', 'Changed Surname', 2, '1234', 'changed username'),
(4, 0, '', '', '0', '', 'me@gmail.com', 'Mesut', '', 'Türkmen', 1, '1234', 'metu'),
(14, 0, '', '', '0', '', NULL, 'Donatello', '', 'Changed Surname', 1, NULL, '20');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `house_owners`
--
ALTER TABLE `house_owners`
  ADD PRIMARY KEY (`owner_id`),
  ADD KEY `FK5pjmjms9a6jvi6a1iag3xckjg` (`house_id`);

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `house_owners`
--
ALTER TABLE `house_owners`
  ADD CONSTRAINT `FK5pjmjms9a6jvi6a1iag3xckjg` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
