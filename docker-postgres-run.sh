docker run --name todolist-postgres -e POSTGRES_PASSWORD=12345 -e POSTGRES_USER=todolist -v vol1:/volume -p 5432:5432 -d postgres:latest
