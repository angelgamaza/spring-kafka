# Kafka Consumer - Producer Example - Spring Boot

This project provides an example about Apache Kafka using Spring Boot 3.4.2.

The service exposes a RESTful API at port 8081 with the base paths:

- `POST /api/v1/kafka/consumer` – API Methods for Kafka Consumer start/stop.
- `POST /api/v1/kafka/producer` – API Methods for Kafka Producer messages send.

All the application is containerized with Docker and Docker Compose and the Apache Kafka instance runs in Kraft mode (without a needed Zookeeper instance). The Kafka instance is accessible at port 9092.

## Prerequisites

To run this application, you must have the following:

- Docker and Docker Compose.
- Basic knowledge of Docker and Kafka.
- A working internet connection to pull Docker images.

Optional (for manually runs):

- Java 21.
- Maven 3.9.3.

## How to Run

1. Start the Containers:

```bash
docker-compose up --build
```

This command will build the Spring Boot application image (using the provided Dockerfile) and start both the Kafka broker and the Spring Boot application.

2. Verify the Setup:

- **Kafka**: The instance will be running and listening on port 9092. You can use Kafka CLI tools or clients to interact with the broker.
- **Spring Boot Application**: Access the application at <http://localhost:8081>.

## Stopping the Services

To stop the containers, run:

```bash
docker-compose down
```

## API Reference

### Consumer API

This API is designed for the Consumer management and provides methods to start and stop the Apache Kafka Consumer listeners at:

- `POST /api/v1/kafka/consumer/start` – Start the Kafka Consumer listeners.
- `POST /api/v1/kafka/consumer/stop` – Stop the Kafka Consumer listeners.

#### Parameters

- `listenerId (string, required)`: The ID of the listener to start/stop.

#### Topics

The available topics for the application are:

- **topic1**: With listener ID `topic1-listener`.
- **topic2**: With listener ID `topic2-listener`.

#### Usage example

```bash
curl -X POST "http://localhost:8081/api/v1/kafka/consumer/start?listenerId=topic1-listener"
```

#### Example response

- **200 OK**: Returns a message with the correct operation result.

```json
{
  "message": "Listener with ID [topic1-listener] started successfully"
}
```

- **500 Internal Server Error**: Returns an error message if the listener does not exist.

```json
{
  "type": "about:blank",
  "title": "Internal server error",
  "status": 500,
  "detail": "Unhandled exception with message: There was an error starting the listener with ID [topic-listener], cause was: NullPointerException",
  "instance": "/api/v1/kafka/consumer/start",
  "description": "Server response with an Internal Error"
}
```

### Producer API

This API is designed to manage the Producer and provides a method to send messages to the existing Apache Kafka topics.

The method for messages sent is:

- `POST /api/v1/kafka/producer/publish/{topic}/partition/{partition}` – Sends a message to the specified topic and partition.

#### Parameters

- `topic (string, required (in path))`: The topic for the message to be sent.
- `partition (integer, required (in path))`: The topic partition for the message to be sent.

#### Body

The body of the request must be a JSON object with the following fields:

```json
{
  "id": "string",
  "message": "string",
  "description": "string"
}
```

#### Usage example

```bash
curl -X POST "http://localhost:8081/api/v1/kafka/producer/publish/topic1/partition/2" -H "accept: application/json" -H "Content-Type: application/json" -d '{
  "id": "82iaO92AK_0",
  "message": "Hello world!",
  "description": "This is a test message"
}'
```

#### Example response

- **200 OK**: Returns a message with the correct operation result.

```json
{
  "message": "Message successfully sent to topic: [topic1] and partition: [2]"
}
```

This operation always return a 200 OK result because is executed asynchronously and the response is not threated.

## Author

Ángel Gamaza - <angel.gamaza@gmail.com>
