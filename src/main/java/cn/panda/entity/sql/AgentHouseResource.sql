create table agentHouseResource
(
      id INT(10) AUTO_INCREMENT PRIMARY KEY,
      picNum VARCHAR (1000),
      url VARCHAR (1000) NOT NULL UNIQUE,
      title VARCHAR (1000),
      publishDate VARCHAR (1000),
      pageView VARCHAR (1000),
      villageName VARCHAR (1000),
      allPrice VARCHAR (1000),
      price VARCHAR (1000),
      area VARCHAR (1000),
      bigArea VARCHAR (1000),
      littleArea VARCHAR (1000),
      agentName VARCHAR (1000),
      agentPhone VARCHAR (1000),
      agentCompany VARCHAR (1000),
      isDelete int(10)

);