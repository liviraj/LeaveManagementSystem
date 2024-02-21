create database lms;

use lms;

CREATE TABLE `lms`.`login` (
  `loginId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`loginId`));

    INSERT INTO `lms`.`login` (`userName`, `password`, `role`) VALUES ('admin', 'admin', 'ADMIN');
    
CREATE TABLE `lms`.`employee` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `dob` DATE NULL,
  `designation` VARCHAR(255) NULL,
  `experiance` INT NULL,
  `contactNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`employeeId`));

  ALTER TABLE `lms`.`employee` 
ADD COLUMN `gender` VARCHAR(45) NULL AFTER `contactNumber`;


  CREATE TABLE `lms`.`leavedetails` (
  `leaveId` INT NOT NULL AUTO_INCREMENT,
  `reason` VARCHAR(255) NULL,
  `leaveFrom` DATE NULL,
  `leaveTo` DATE NULL,
  `leaveType` VARCHAR(45) NULL,
  `leaveStatus` VARCHAR(45) NULL,
  `employeeId` INT NULL,
  PRIMARY KEY (`leaveId`),
  INDEX `emp_leave_fk_idx` (`employeeId` ASC) VISIBLE,
  CONSTRAINT `emp_leave_fk`
    FOREIGN KEY (`employeeId`)
    REFERENCES `lms`.`employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




