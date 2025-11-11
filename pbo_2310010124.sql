-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2025 at 05:53 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo_2310010124`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_penerima`
--

CREATE TABLE `data_penerima` (
  `id_penerima` int(11) NOT NULL,
  `nama_penerima` varchar(100) NOT NULL,
  `nik` varchar(20) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `id_program` int(11) DEFAULT NULL,
  `id_wilayah` int(11) DEFAULT NULL,
  `status_bantuan` enum('Aktif','Nonaktif') DEFAULT 'Aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_penerima`
--

INSERT INTO `data_penerima` (`id_penerima`, `nama_penerima`, `nik`, `alamat`, `id_program`, `id_wilayah`, `status_bantuan`) VALUES
(1, 'Siti Aminah', '1271010101010001', 'Jl. Melati No. 45, Harjosari II', 1, 1, 'Aktif'),
(2, 'Budi Santoso', '1271020202020002', 'Dusun I, Stabat Baru', 1, 2, 'Aktif'),
(3, 'Rina Wijaya', '1271030303030003', 'Jl. Raya Kisaran Barat No.12', 2, 3, 'Aktif'),
(4, 'Ahmad Lubis', '1271040404040004', 'Gg. Mawar, Sidomulyo', 2, 3, 'Aktif'),
(5, 'Sulastri', '1271040404040005', 'Jl. Sukarame, Lubuk Pakam', 5, 1, 'Aktif'),
(6, 'Lina Marpaung', '1271040404040006', 'Jl. Setia Budi No.12, Rengas Pulau', 5, 2, 'Aktif'),
(8, 'Nuraini Harahap', '1271040404040008', 'Jl. Jati No.18, Tanah Tinggi', 5, 2, 'Aktif'),
(9, 'Muhammad Iqbal', '1271040404040009', 'Gg. Kenanga No.3, Lubuk Pakam', 5, 4, 'Aktif'),
(10, 'Rudi Hartono', '1271040404040010', 'Jl. Diponegoro No.20, Stabat Baru', 2, 4, 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL,
  `keterangan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `keterangan`) VALUES
(1, 'Bantuan Sosial', 'Bantuan berupa sembako, beras, minyak goreng, dan kebutuhan pokok lainnya untuk masyarakat kurang mampu.'),
(2, 'Bantuan Pendidikan', 'Bantuan untuk siswa/mahasiswa kurang mampu, seperti beasiswa, seragam, alat tulis, atau uang sekolah.'),
(3, 'Bantuan Kesehatan', 'Bantuan obat-obatan, pelayanan kesehatan gratis, atau subsidi BPJS Kesehatan bagi masyarakat miskin.'),
(4, 'Bantuan Bencana', 'Bantuan darurat untuk korban bencana alam (banjir, gempa, kebakaran, dll).'),
(5, 'Bantuan Tunai Langsung', 'Bantuan uang tunai (seperti BLT) untuk masyarakat terdampak ekonomi atau pandemi.'),
(7, 'Bantuan Lansia', 'Program kesejahteraan sosial lanjut usia: tunjangan harian, pelayanan kesehatan, dan bantuan perawatan.'),
(8, 'Bantuan Anak Yatim/Piatu', 'Bantuan sosial untuk anak yatim piatu berupa perlengkapan sekolah, pangan, dan biaya hidup.');

-- --------------------------------------------------------

--
-- Table structure for table `program_bantuan`
--

CREATE TABLE `program_bantuan` (
  `id_program` int(11) NOT NULL,
  `nama_program` varchar(100) NOT NULL,
  `jenis_bantuan` varchar(100) DEFAULT NULL,
  `tanggal_mulai` date DEFAULT NULL,
  `tanggal_selesai` date DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `program_bantuan`
--

INSERT INTO `program_bantuan` (`id_program`, `nama_program`, `jenis_bantuan`, `tanggal_mulai`, `tanggal_selesai`, `id_kategori`) VALUES
(1, 'Bantuan Pangan Non Tunai (BPNT)', 'Pangan', '2025-01-01', '2025-12-31', 1),
(2, 'Program Indonesia Pintar (PIP)', 'Pendidikan', '2025-02-01', '2025-11-30', 2),
(4, 'Bantuan Stimulan Perumahan Swadaya (BSPS)', 'Perumahan', '2025-04-15', '2025-09-30', 4),
(5, 'BLT Dampak Inflasi', 'Tunai', '2025-01-15', '2025-03-31', 5);

-- --------------------------------------------------------

--
-- Table structure for table `wilayah`
--

CREATE TABLE `wilayah` (
  `id_wilayah` int(11) NOT NULL,
  `nama_kecamatan` varchar(100) DEFAULT NULL,
  `nama_kelurahan` varchar(100) DEFAULT NULL,
  `kode_pos` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wilayah`
--

INSERT INTO `wilayah` (`id_wilayah`, `nama_kecamatan`, `nama_kelurahan`, `kode_pos`) VALUES
(1, 'Medan Tuntungan', 'Sidomulyo', 'Sidomulyo'),
(2, 'Medan Amplas', 'Harjosari II', '20148'),
(3, 'Medan Marelan', 'Rengas Pulau', '20255'),
(4, 'Binjai Timur', 'Tanah Tinggi', '20731'),
(6, 'Tebing Tinggi', 'Bandar Sakti', '20613'),
(7, 'Langkat', 'Stabat Baru', '20811'),
(8, 'Serdang Bedagai', 'Sei Rampah', '20995'),
(9, 'Tapanuli Utara', 'Tarutung', '22411'),
(10, 'Asahan', 'Kisaran Barat', '21215');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_penerima`
--
ALTER TABLE `data_penerima`
  ADD PRIMARY KEY (`id_penerima`),
  ADD UNIQUE KEY `nik` (`nik`),
  ADD KEY `id_program` (`id_program`),
  ADD KEY `id_wilayah` (`id_wilayah`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `program_bantuan`
--
ALTER TABLE `program_bantuan`
  ADD PRIMARY KEY (`id_program`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `wilayah`
--
ALTER TABLE `wilayah`
  ADD PRIMARY KEY (`id_wilayah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_penerima`
--
ALTER TABLE `data_penerima`
  MODIFY `id_penerima` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `program_bantuan`
--
ALTER TABLE `program_bantuan`
  MODIFY `id_program` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `wilayah`
--
ALTER TABLE `wilayah`
  MODIFY `id_wilayah` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_penerima`
--
ALTER TABLE `data_penerima`
  ADD CONSTRAINT `data_penerima_ibfk_1` FOREIGN KEY (`id_program`) REFERENCES `program_bantuan` (`id_program`),
  ADD CONSTRAINT `data_penerima_ibfk_2` FOREIGN KEY (`id_wilayah`) REFERENCES `wilayah` (`id_wilayah`);

--
-- Constraints for table `program_bantuan`
--
ALTER TABLE `program_bantuan`
  ADD CONSTRAINT `program_bantuan_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
