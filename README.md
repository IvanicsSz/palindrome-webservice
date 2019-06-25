README
======

[Requirements](Requirements.md)

# Palindrome Webservice

Palindrome webservice is used to broadcasts messages through Websockets for listening clients.
The service is calculating longest palindrome contained within the content property.
Requests are persisted in database.

## Installation

Clone the repo.

To build the project, use the provided Maven script:
```
mvnw clean package
```

To run the project in Docker run:
```
docker-compose up
```

## Usage

Subscribe to a webservice in localhost:8080 with Subscribe button.

Send a POST request to: http://localhost:8080/api/content/send


with curl:

```
curl --header "Content-Type: application/json" \
--request POST \
--data '{"content": "abrakadabra","timestamp": "2018-10-09 00:12:12+0100"}' \
http://localhost:8080/api/content/send
```

with Postman send POST request, json type, body content:

```
{
"content": "abrakadabra",
"timestamp": "2018-10-09 00:12:12+0100"
}
```

You will see the new content on the subscribed user's webpage.

Send GET request to http://localhost:8080/api/contents endpoint,
to get all database entries with the longest palindrome value on it.


## Database structure

[Database structure](Requirements.md)


