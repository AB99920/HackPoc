DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT IDENTITY PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  is_locked BOOLEAN NOT NULL,
  is_Password_Temporary BOOLEAN NOT NULL,
  security_Answer1 VARCHAR(50),
  security_Answer2 VARCHAR(50),
  security_Answer3 VARCHAR(50),
  safe_Word VARCHAR(50),
  telephone_Number VARCHAR(50),
  failed_login_count int NOT NULL
);

INSERT INTO users VALUES
 (default,'user1','user1password',false,false,null,null,null,'safe',null,0);