# serviceB

Simple Spring Boot service exposing an API:

- `GET http://localhost:8082/api/b/value`

## Kafka

`serviceB` consumes events from Kafka topic `demo.events` (configurable via `app.kafka.topic`).

- `GET http://localhost:8082/api/b/last-event` -> returns the last consumed message (in-memory).

## Run

### 0) Start Kafka (Docker)

From inside `serviceB` (or `serviceA`) you can run:

```bash
docker compose up -d
```

```bash
mvn spring-boot:run
```
