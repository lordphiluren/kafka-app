# Система мониторинга с использованием Apache Kafka
Система мониторинга состоящая из двух сервисов Producer и Consumer, взаимодействие сервисов происходит при помощи Apache Kafka.

## Описание сервисов
### Producer:
- Сервис поддерживает аутентификацию и авторизацию пользователей по логину и паролю.
- Доступ к API аутентифицирован с помощью JWT токена.
- Схема базы данных создается при помощи Liquibase.
- При отправке POST запроса на /api/v1/metrics приложение собирает свои Gauge метрики с помощью Spring Actuator и отправляет их в Apache Kafka в топик metrics-topic.
- Страница с документацией Swagger будет доступна по ссылке: http://localhost:8081/swagger-ui/index.html
#### Формат сообщения отправляемого в Apache Kafka
```javascript
[
  {
    "name" : string,
    "description" : string,
    "baseUnit"    : string,
    "value" : double
  }
]
```
### Consumer:
- Сервис подписан на топик metrics-topic и получает оттуда список отправленных Producer метрик.
- Метрики сохраняются в базу данных для дальнейшего анализа.
- При отправке GET запроса на /api/v1/metrics сервер отдает список всех метрик из БД с их количественными показателями.

#### Ответ:
```javascript
[
  {
    "id" : long,
    "name" : string,
    "description" : string,
    "baseUnit"    : string,
    "values" : double[]
  }
]
```
- При отправке GET запроса на /api/v1/metrics/{id} сервер отадает конкретную метрику с ее количественными показателями.

#### Ответ:
```javascript
{
  "id" : long,
  "name" : string,
  "description" : string,
  "baseUnit"    : string,
  "values" : double[]
}
```
- Схема базы данных создается при помощи Liquibase.
- Страница с документацией Swagger будет доступна по ссылке: http://localhost:8082/swagger-ui/index.html

## Использованные технологии
- Java
- Gradle
- Spring Boot, Spring Security, Sping Web, Spring Data JPA, Spring Kafka, Spring Actuator
- Kafka
- PostgreSQL
- Liquibase
- Swagger
- Docker - контейнеры, образы, volumes, написание Dockerfile, Docker Compose

## Инструкция по запуску проекта на локальном компьютере
- Склонировать репозиторий 
```
git clone https://github.com/lordphiluren/kafka-app
```
- Перейти в папку с проектом
```
cd kafka-app
```
- Запустить приложение
```
docker-compose up
```
