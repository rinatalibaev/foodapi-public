# 🍽 Recipe Service API

Backend-сервис для работы с рецептами, ингредиентами и пользовательским холодильником.  
Поддерживает избранные рецепты, комментарии, шаги приготовления и учет ингредиентов.

---

## 🚀 Технологии

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Liquibase** — миграции БД
- **PostgreSQL**
- **Keycloak** — аутентификация и авторизация
- **MapStruct** — маппинг DTO ↔ Entity
- **OpenAPI 3 (Swagger)**

---

## 🧱 Архитектура

Проект построен по принципам **Clean Architecture / DDD**:

- `entity` — JPA сущности
- `domain` — бизнес-модели
- `dto` — входные/выходные контракты API
- `repository` — доступ к БД
- `service` — бизнес-логика
- `mapper` — MapStruct мапперы

---

## Запуск
1. В консоли выполнить команды:

`./gradlew clean build`

`docker build -t food-app --no-cache .`

`docker compose up --build --force-recreate --no-deps -d`

2. Настроить keycloak - http://localhost:8282 (создать реалм, скоуп, клиента, пользователя, мапперы)

3. Указать правильные KEYCLOAK_ADMIN_CLIENT_ID и KEYCLOAK_CREDENTIALS_SECRET в [docker-compose.yml](docker-compose.yml)

4. Minio - http://localhost:9001 -> создать бакет food-bucket
