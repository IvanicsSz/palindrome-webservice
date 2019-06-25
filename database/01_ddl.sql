--
-- Database schema
--
CREATE TABLE palindrome
(
  id                VARCHAR(40) PRIMARY KEY NOT NULL,
  user_content      VARCHAR(255),
  time_stamp        TIMESTAMP,
  palindrome_size   INT
);
