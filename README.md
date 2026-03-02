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
1. Собрать проект любым возможным способом выполнив ./gradlew clean build
В консоли выполнить команду docker build -t food-app --no-cache .

2. docker compose up --build --force-recreate --no-deps -d

3. Настроить keycloak - http://localhost:8282 (создать реалм, скоуп, клиента, пользователя, мапперы)

4. Minio - http://localhost:9001 -> создать бакет food-bucket
