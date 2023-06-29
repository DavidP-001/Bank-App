CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `wallet` (
  `Wallet_ID` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `value` decimal(38,2) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `wallets_ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`Wallet_ID`),
  KEY `FKa7iv1dn5tpixv4962fsks5gon` (`User_ID`),
  CONSTRAINT `FKa7iv1dn5tpixv4962fsks5gon` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `transaction` (
  `Transaction_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `dateOfTransaction` date DEFAULT NULL,
  `transactionsAsReceiver_ORDER` int(11) NOT NULL,
  `transactionsAsSender_ORDER` int(11) NOT NULL,
  `value` decimal(38,2) DEFAULT NULL,
  `receiver_ID` int(11) DEFAULT NULL,
  `Transaction_senderID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Transaction_ID`),
  KEY `FKi6wh0j7nhrs06kiwqsd7e7429` (`receiver_ID`),
  KEY `FK3nm1c87lqbvp3ctecawayuo3s` (`Transaction_senderID`),
  CONSTRAINT `FK3nm1c87lqbvp3ctecawayuo3s` FOREIGN KEY (`Transaction_senderID`) REFERENCES `user` (`User_ID`),
  CONSTRAINT `FKi6wh0j7nhrs06kiwqsd7e7429` FOREIGN KEY (`receiver_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO bank_app_db.`user` (firstName,lastName,password) VALUES
	 ('David','Priocic','$2a$10$772elZBmWsQYJwYNxHdDo.74rn1oUlpC/1rzw0brbCAnr7nNtI4aO'),
	 ('Max','Mustermann','$2a$10$BMTRyUDXZB0Oz.hcb7SwnuOrYQT9RX17quu9O2qnoKelDqAKP3gn.'),
	 ('Otto','Muster','$2a$10$fZ/YNSOR2o9OStNViieW6.JX9yBZ.CApFyJEwgZMmipKN/f0wDs/e'),
	 ('Nina','Musterfrau','$2a$10$bxxgTLb18ucuUdIyA7QTOeW0ZbTFsPC5nvSxysx9Ek1lUrg2aSdjG'),
	 ('Günther','Mayer','$2a$10$AueUDMsUnJg1q64ZRiaVe.NrnEHMh/z0YPiS5I3YRwuVqP32sgwaS');
	 
INSERT INTO bank_app_db.`transaction` (currency,dateOfTransaction,transactionsAsReceiver_ORDER,transactionsAsSender_ORDER,value,receiver_ID,Transaction_senderID) VALUES
	 ('EUR','2023-05-11',1,1,50.00,2,1),
	 ('EUR','2023-05-12',2,2,2.00,1,2),
	 ('EUR','2023-05-11',0,0,20.00,2,1),
	 ('EUR','2023-05-11',0,0,20.00,2,1),
	 ('EUR','2023-05-11',0,0,30.00,2,1),
	 ('EUR','2023-05-11',0,0,100.00,2,1),
	 ('EUR','2023-05-11',0,0,2.00,2,1),
	 ('EUR','2023-05-12',0,0,10.11,2,1),
	 ('EUR','2023-05-18',0,0,10.00,1,3),
	 ('EUR','2023-05-18',0,0,10.00,2,1);
	 
INSERT INTO bank_app_db.wallet (currency,value,User_ID,wallets_ORDER) VALUES
	 ('EUR',689.89,1,1),
	 ('USD',1325.02,1,2),
	 ('CAD',1000.00,1,3),
	 ('GBP',1000.00,1,4),
	 ('JPY',1000.00,1,5),
	 ('KRW',1000.00,1,6),
	 ('EUR',1020.11,2,7),
	 ('USD',1000.00,2,8),
	 ('CAD',1000.00,2,9),
	 ('GBP',1000.00,2,10);
INSERT INTO bank_app_db.wallet (currency,value,User_ID,wallets_ORDER) VALUES
	 ('JPY',1000.00,2,11),
	 ('KRW',1000.00,2,12),
	 ('EUR',990.00,3,13),
	 ('USD',1000.00,3,14),
	 ('CAD',1000.00,3,15),
	 ('GBP',1000.00,3,16),
	 ('JPY',1000.00,3,17),
	 ('KRW',1000.00,3,18),
	 ('EUR',1000.00,4,19),
	 ('USD',1000.00,4,20);
INSERT INTO bank_app_db.wallet (currency,value,User_ID,wallets_ORDER) VALUES
	 ('CAD',1000.00,4,21),
	 ('GBP',1000.00,4,22),
	 ('JPY',1000.00,4,23),
	 ('KRW',1000.00,4,24),
	 ('EUR',1000.00,5,25),
	 ('USD',1000.00,5,26),
	 ('CAD',1000.00,5,27),
	 ('GBP',1000.00,5,28),
	 ('JPY',1000.00,5,29),
	 ('KRW',1000.00,5,30);
