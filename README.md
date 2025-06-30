# router

App to route events.

## Tasks

### TASK-128 [DONE]

Make it possible to retrieve and create events via REST API.

### TASK-256 [TO DO]

Events with the same code mustn't be saved.

### TASK-512 [TO DO]

Add new event status: in queue. It can happen after receiving an event, but before taking it into processing.

### TASK-1024 [TO DO]

Change `date` property of the event to `timestamp` with precision up to milliseconds.

## Tips

### Run DB locally

> docker run --name router-db -d -p 5435:5432 -e POSTGRES_DB=router -e POSTGRES_USER=router -e POSTGRES_PASSWORD=qwerty postgres
