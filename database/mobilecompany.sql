CREATE TABLE Roles
(
  id 				INT(32) 	PRIMARY KEY NOT NULL,
  name 				VARCHAR(32)         NOT NULL
);

CREATE TABLE Users
(
  id 				INT(32) 	PRIMARY KEY NOT NULL AUTO_INCREMENT,
  firstName 		VARCHAR(15) 			NOT NULL,
  secondName 		VARCHAR(15) 			NOT NULL,
  dateOfBirth 		DATE 					NOT NULL,
  passportNumber 	VARCHAR(10) 			NOT NULL,
  adress 			VARCHAR(255) 			NOT NULL,
  email 			VARCHAR(64) 			NOT NULL,
  password 			VARCHAR(64) 			NOT NULL,
  optionIsBlocked 		TINYINT(1) 				NOT NULL,
  role 				INT(32) 				NOT NULL,

  FOREIGN KEY (role) REFERENCES Roles(id)
);

CREATE TABLE Options
(
  id           		INT(32) 	PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name         		VARCHAR(15),
  price         	DECIMAL(10, 2),
  connectionPrice 	DECIMAL(10, 2)
);

CREATE TABLE Tariffs
(
  id 				INT(32) 	PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name 				VARCHAR(15) 			NOT NULL,
  price 			DECIMAL(10, 2)
);

CREATE TABLE Contracts
(
  id 				INT(32) 	PRIMARY KEY NOT NULL AUTO_INCREMENT,
  number 			VARCHAR(12) 			NOT NULL,
  tariff 			INT(32) 				NOT NULL,
  client 			INT(32) 				NOT NULL,
  balance 			DECIMAL(10,2) 			NOT NULL,
  optionIsBlocked 		TINYINT(1) 				NOT NULL,
  
  FOREIGN KEY (tariff) 		REFERENCES Tariffs(id),
  FOREIGN KEY (client) 		REFERENCES Users(id)
);
  
CREATE TABLE SelectedOptions
(
  contractId 		INT(32),
  optionId 			INT(32),
  
  FOREIGN KEY (contractId) 	REFERENCES Contracts(id),
  FOREIGN KEY (optionId) 	REFERENCES Options(id)
);

CREATE TABLE AvailableOptions
(
  tariffId 			INT(32),
  optionId 			INT(32),
  FOREIGN KEY (tariffId) 	REFERENCES Tariffs(id),
  FOREIGN KEY (optionId) 	REFERENCES Options(id)
);