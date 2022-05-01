-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2022 at 03:47 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `examcheatingdetection`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `enabled`, `name`, `password`, `phone`, `role`) VALUES
(1, 'ffggf', b'1', 'admin', '$2a$10$ZzjWmQuvx0bAs28X1dElvuNQcSxYWBUbH0QCAazxq6WHMybP4oF2W', '555', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `cheating_type`
--

CREATE TABLE `cheating_type` (
  `id` int(11) NOT NULL,
  `cheating_by_change_identity` int(11) DEFAULT NULL,
  `cheating_by_eye_movement` int(11) DEFAULT NULL,
  `cheating_by_speaking` int(11) DEFAULT NULL,
  `noise` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cheating_type`
--

INSERT INTO `cheating_type` (`id`, `cheating_by_change_identity`, `cheating_by_eye_movement`, `cheating_by_speaking`, `noise`) VALUES
(1, 1, 1, 1, 1),
(2, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `correct_answer`
--

CREATE TABLE `correct_answer` (
  `id` int(11) NOT NULL,
  `boolean_answer` bit(1) DEFAULT NULL,
  `string_answer` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `course_name`, `instructor_id`) VALUES
(1, 'Java', 1),
(2, 'spring', 1),
(3, 'php', 1),
(4, 'JavaScript', 1),
(5, 'html', 1),
(6, 'laravel', 1),
(7, 'css', 2);

-- --------------------------------------------------------

--
-- Table structure for table `course_student`
--

CREATE TABLE `course_student` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_student`
--

INSERT INTO `course_student` (`student_id`, `course_id`) VALUES
(1, 6),
(1, 5),
(1, 4),
(1, 3),
(1, 2),
(1, 1),
(2, 1),
(2, 2),
(2, 5),
(2, 6),
(3, 1),
(3, 2),
(1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `id` int(11) NOT NULL,
  `number_of_question` int(11) DEFAULT NULL,
  `updated` bit(1) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `cheating_type_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`id`, `number_of_question`, `updated`, `date`, `time`, `cheating_type_id`, `course_id`, `instructor_id`) VALUES
(1, 4, b'1', '2022-05-01', 2, 1, 1, 1),
(2, 2, b'1', '2022-06-02', 5, 2, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `exam_student`
--

CREATE TABLE `exam_student` (
  `student_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam_student`
--

INSERT INTO `exam_student` (`student_id`, `exam_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `student_id` int(11) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`student_id`, `file_name`) VALUES
(1, '23045.jpg'),
(2, 'mostafa.jpg'),
(3, '23049.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `email`, `enabled`, `name`, `password`, `phone`, `role`, `image`) VALUES
(1, 'ahmed@yahoo.com', b'1', 'ahmed', '$2a$10$9q1ordwKFV1W0uHHT11yqeSaEhmHhib51KHP28VzfmKrY2QCGHOc.', '114624208', 'ROLE_INSTRUCTOR', 'IMG-20191220-WA0127.jpg'),
(2, 'Amr@yahoo.com', b'1', 'amr', '$2a$10$Bwx61hhNV964TG0E/lGcI.i4NQemnLnZzJfI28B/Q3PbDqKF3W8b.', '114624208', 'ROLE_INSTRUCTOR', '23045.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `content`, `title`, `student_id`) VALUES
(1, 'i enter exam and answered all question and my result didt\'t appear', 'result not found ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  `correct_answer_bool` bit(1) DEFAULT NULL,
  `eye_movment` bit(1) DEFAULT NULL,
  `option_a` varchar(255) DEFAULT NULL,
  `option_b` varchar(255) DEFAULT NULL,
  `option_c` varchar(255) DEFAULT NULL,
  `option_d` varchar(255) DEFAULT NULL,
  `question_head` varchar(255) DEFAULT NULL,
  `question_time` int(11) DEFAULT NULL,
  `time_for_cheating` int(11) DEFAULT NULL,
  `type` bit(1) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `correct_answer`, `correct_answer_bool`, `eye_movment`, `option_a`, `option_b`, `option_c`, `option_d`, `question_head`, `question_time`, `time_for_cheating`, `type`, `exam_id`) VALUES
(1, 'A', NULL, b'0', 'mostafa', 'ali', 'ahmed', 'yousef', 'what is your name', 20, 20, b'0', 1),
(2, 'B', NULL, b'0', '88', '22', '99', '50', 'how old are you', 20, 20, b'0', 1),
(3, 'A', b'1', b'0', '', '', '', '', 'yes', 20, 20, b'1', 1),
(4, 'A', b'0', b'0', '', '', '', '', 'no', 20, 20, b'1', 1),
(5, 'A', b'1', b'0', '', '', '', '', 'yes', 0, 0, b'1', 2),
(6, 'A', b'0', b'0', '', '', '', '', 'no', 20, 20, b'1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `replay`
--

CREATE TABLE `replay` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `message_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `replay`
--

INSERT INTO `replay` (`id`, `content`, `message_id`) VALUES
(1, 'ok i will solve this problem ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `exam_result` int(11) DEFAULT NULL,
  `num_of_question` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`id`, `exam_result`, `num_of_question`, `exam_id`, `student_id`) VALUES
(1, 3, 4, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `email`, `enabled`, `name`, `password`, `phone`, `role`) VALUES
(1, 'mostafamagdy@gmail.com', b'1', 'mostafa', '$2a$10$XIwseVdsNJ2NCMth2SY2ReprX9YjxeRBzrrLnCbkVfMCpLkpNPFHW', '114624208', 'ROLE_STUDENT'),
(2, 'yousef@yahoo.com', b'1', 'yousef', '$2a$10$Pt9ibseptoSZ9rbhXq8FMu9itWluB5eEfvnPjW2b1ghGv1jTm/s7O', '114624208', 'ROLE_STUDENT'),
(3, 'ali@yahoo.com', b'1', 'Ali', '$2a$10$JCFvoG1w8L5Q5KWqonwCW.Bsc0ud0ajbWmqZcYEUT5TwXCG.ux1Wa', '114624208', 'ROLE_STUDENT');

-- --------------------------------------------------------

--
-- Table structure for table `student_answer`
--

CREATE TABLE `student_answer` (
  `id` int(11) NOT NULL,
  `answer_is_correct` bit(1) DEFAULT NULL,
  `boolean_answer` bit(1) DEFAULT NULL,
  `string_answer` varchar(255) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_answer`
--

INSERT INTO `student_answer` (`id`, `answer_is_correct`, `boolean_answer`, `string_answer`, `exam_id`, `student_id`) VALUES
(1, b'0', b'0', NULL, 1, 1),
(2, b'1', b'0', 'B', 1, 1),
(3, b'1', b'1', NULL, 1, 1),
(4, b'1', b'0', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_cheating_type`
--

CREATE TABLE `student_cheating_type` (
  `id` int(11) NOT NULL,
  `cheating_by_change_identity` int(11) DEFAULT NULL,
  `cheating_by_eye_movement` int(11) DEFAULT NULL,
  `cheating_by_speaking` int(11) DEFAULT NULL,
  `noise` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cheating_type`
--
ALTER TABLE `cheating_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `correct_answer`
--
ALTER TABLE `correct_answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqk2yq2yk124dhlsilomy36qr9` (`instructor_id`);

--
-- Indexes for table `course_student`
--
ALTER TABLE `course_student`
  ADD KEY `FKlmj50qx9k98b7li5li74nnylb` (`course_id`),
  ADD KEY `FK4xxxkt1m6afc9vxp3ryb0xfhi` (`student_id`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaxrbp8fw1hlmi5y1ptutel0ap` (`cheating_type_id`),
  ADD KEY `FKiub3ue9cklcyyra24v9ns656n` (`course_id`),
  ADD KEY `FK23nklkf992jpo1hxqmpa16t77` (`instructor_id`);

--
-- Indexes for table `exam_student`
--
ALTER TABLE `exam_student`
  ADD KEY `FKsxtyhwwfhtjpf5pqx8p5ogm0w` (`exam_id`),
  ADD KEY `FKrb2qpwwcicth4xdhurwcp8rd` (`student_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD KEY `FKkdnkltse884wuiqwkt4cpuo9m` (`student_id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK560equk6lmuky93blpeb1saew` (`student_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhupso6ldavcx993tfnrjsdl1p` (`exam_id`);

--
-- Indexes for table `replay`
--
ALTER TABLE `replay`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiooaiioljc8qwjlxhpnnrm8wh` (`message_id`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7sc8ejc10fh429vn3gfpkhem9` (`exam_id`),
  ADD KEY `FK952u5w97magsligxcxohuwf5f` (`student_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_answer`
--
ALTER TABLE `student_answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ar1y3e3sbdegryolkqta89k5` (`exam_id`),
  ADD KEY `FKr8wd05u8yc3ocudxugs5bk50v` (`student_id`);

--
-- Indexes for table `student_cheating_type`
--
ALTER TABLE `student_cheating_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKme1bkluh757y40hnj7hwxpi1c` (`exam_id`),
  ADD KEY `FK73khek9r80kha9ssg7r2ebfe1` (`student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cheating_type`
--
ALTER TABLE `cheating_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `correct_answer`
--
ALTER TABLE `correct_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `replay`
--
ALTER TABLE `replay`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `student_answer`
--
ALTER TABLE `student_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `student_cheating_type`
--
ALTER TABLE `student_cheating_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
