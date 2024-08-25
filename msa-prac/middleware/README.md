# Infra 환경 설정

### MySQL
```bash
# mac
$ docker run --rm --name msa-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root1234! -v ./ddl.sql:/docker-entrypoint-initdb.d/init.sql mysql:8.0

```

### Kafka
```bash
docker run -p 9092:9092 apache/kafka:3.8.0
```

### Redis
```bash
docker run -p 6379:6379 redis:7.4.0
```
