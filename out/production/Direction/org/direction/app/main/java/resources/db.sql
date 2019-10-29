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
  `Contact` integer,
  `ParentsName` Varchar(50),
  `ParentsContact` Integer
);

CREATE TABLE `User`
(
  `UID` varchar(20) PRIMARY KEY,
  `Post` varchar(20),
  `Username` varchar(20),
  `Password` varchar(20),
  `Contact` Integer,
  `Hometown` varchar(20)
);

CREATE TABLE `Event`
(
  `EID` varchar(20) PRIMARY KEY,
  `Location` varchar(20) NOT NULL,
  `Members` varchar(200) NOT NULL,
  `Date` varchar(15) NOT NULL,
  `Budget` integer NOT NULL,
  `Attendance` integer NOT NULL
);

ALTER TABLE `Student` ADD FOREIGN KEY (`EID`) REFERENCES `User` (`UID`);

ALTER TABLE `Student` ADD FOREIGN KEY (`UID`) REFERENCES `Event` (`EID`);

ALTER TABLE `Event` ADD FOREIGN KEY (`Members`) REFERENCES `User` (`UID`);

