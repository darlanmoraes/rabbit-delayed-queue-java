# RABBIT DELAYED QUEUE

This project created a delayed queue that is used to process items in the future. The messages are delayed for 10 seconds, but this value can be increased up until 2 days.

### Dependencies

- **docker-compose**: `1.26.0`;
- **docker**: `19.03.12`;
- **java**: `14`;
- **gradle**: `6.5`;

### How to run

Run the following commands. After the `curl`, you should see in the `logs` that a message is sent to **`Rabbit`** and just after 10 seconds the message is read again.

- `cd rabbit-delayed-queue-java`
- `docker-compose up`
- `gradle build`
- `gradle bootRun`
- `curl -X GET -v http://localhost:8080/rabbit`