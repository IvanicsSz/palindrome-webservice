#Database Structure

Palindrome database is for store sent content enriched with longest palindrome value.

#Fields:

## id
type: varchar(40)
Primary key
Unique identifier for entries, can not be null

## user_content
type: varchar(255)
Store content field from json message what was sent by the user

## time_stamp
type: timestamp
Store time in given time format

## palindrome_size
type: int
Store the longest palindrome size length in content field
