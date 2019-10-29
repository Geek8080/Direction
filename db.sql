CREATE TABLE `Student`
(
  `SID` varchar(20) PRIMARY KEY,
  `Name` varchar(50),
  `EID` varchar(20),
  `UID` varchar(20),
  `Marks` integer,
  `Gender` char,
  `Class` integer,
  `Age` integer,
  `Address` varchar(100),
  `Contact` varchar(10),
  `ParentsName` Varchar(50),
  `ParentsContact` varchar(10)
);

CREATE TABLE `User`
(
  `UID` varchar(20) PRIMARY KEY,
  `Post` varchar(20),
  `Username` varchar(20),
  `Password` varchar(20),
  `Contact` varchar(10),
  `Hometown` varchar(20)
);

CREATE TABLE `Event`
(
  `EID` varchar(20) PRIMARY KEY,
  `Location` varchar(20) NOT NULL,
  `Members` varchar(200) NOT NULL,
  `Date` varchar(15) NOT NULL,
  `Budget` integer NOT NULL,
  `Attendance` integer NOT NULL,
  `Status` Varchar(20) NOT NULL
);