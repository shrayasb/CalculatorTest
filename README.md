# CalculatorTest


Table used for this app

CREATE TABLE `calculations` (
  `operation` varchar(10) NOT NULL,
  `first_number` double NOT NULL,
  `second_number` double NOT NULL,
  `result` double NOT NULL,
  `calculated_on` datetime NOT NULL,
  PRIMARY KEY (`operation`,`first_number`,`second_number`)
) 
