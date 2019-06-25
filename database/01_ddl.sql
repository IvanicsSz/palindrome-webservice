--
-- Database schema
--
CREATE DATABASE palindrome;
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE palindrome TO postgres;

CREATE TABLE palindrome
(
  id                VARCHAR(40) PRIMARY KEY NOT NULL,
  user_content      VARCHAR(255),
  time_stamp        TIMESTAMP,
  palindrome_size   INT
);
ALTER TABLE palindrome
    OWNER to postgres;
