WalkMoreFatMan — это учебный проект Rest Api приложения, для получения информации о питании пользователей,
реализованный на Java с использованием фреймворка Spring Boot. Приложение включает функционал
занесения и получения сведений о пользователях, блюдах и питании.

## Используемые технологии

* Java 21 — основной язык программирования.
* Spring Boot — реализации REST API.
* Project Lombok - для сокращения кода.
* Docker — для контейнеризации и упрощения развертывания.
* JUnit и Mockito — для модульного тестирования бизнес-логики.

## Расположение

* WalkMoreFatMan - приложение занимает порт 8087 (http://localhost:8087).

### UserController ("/WalkMoreFatMan-api/users")

* Post запросы на адрес http://localhost:8087/WalkMoreFatMan-api/users для регистрации пользователя.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/users/{userName:\S+} для получения сведений о
  зарегистрированном пользователе.
* Put запросы на адрес http://localhost:8087/WalkMoreFatMan-api/users/{userName:\S+} для обновления данных пользователя.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/users для получения списка всех зарегистрированных
  пользователей.
* Delete запросы на адрес http://localhost:8087/WalkMoreFatMan-api/users/{userName:\S+} удаление пользователя.

### DishController ("/WalkMoreFatMan-api/dishes")

* Post запросы на адрес http://localhost:8087/WalkMoreFatMan-api/dishes для регистрации блюда.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/dishes/{dishName:\S+} для получения сведений о
  зарегистрированном блюде.
*
    * Put запросы на адрес http://localhost:8087/WalkMoreFatMan-api/dishes/{dishName:\S+} для обновления данных блюде.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/dishes для получения списка всех зарегистрированных
  блюд.
* Delete запросы на адрес http://localhost:8087/WalkMoreFatMan-api/dishes/{dishName:\S+} удаление блюда.

### EatingController ("/WalkMoreFatMan-api/eating")

* Post запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating/{userName:\S+} для занесения данных о приеме
  пищи.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating для получения всех данных о всех примах пищи
  всех пользователей.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating/reports/{userName:\S+} для получения
  для получения всех данных о всех примах пищи пользователя.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating/reports/{date:\S+} для получения данных о
  приемах всех приемах пищи за конкретную дату.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating/reports/{userName:\S+}/{date:\S+} для получения
  данных о всех приемах пользователем за конкретную дату.
* Get запросы на адрес http://localhost:8087/WalkMoreFatMan-api/eating/reports/calorie_check/{userName:\S+}/{date:\S+}
  проверка совпадает ли цель питания пользователя с объемом принятой пищи за конкретную дату.
  ===========================================================================

## Установка и запуск

1. ### Скачать данный репозиторий на машину, для запуска.
2. ### Создать докер контейнер для базы данных
    ```
    docker run --name bju-db -p 5432:5432 -e POSTGRES_DB=diet_service
    -e POSTGRES_USER=bjuuser -e POSTGRES_PASSWORD=bjupassword postgres:16
   ```
3. ### Создать таблицы в базе данных
    ```
    create schema if not exists diet_service;

    create table if not exists diet_service.t_bju_user(
    id serial primary key,
    c_user_name VARCHAR(50) not null check (length(trim(c_user_name)) >= 3),
    c_gender VARCHAR(50) not null check (c_gender in ('MALE','FEMALE')),
    c_email VARCHAR(50) not null check (length(trim(c_email)) >= 5),
    c_age INT not null check (c_age > 0) check (c_age <= 120),
    c_weight INT not null check (c_weight > 0) check (c_weight < 300),
    c_height INT not null check (c_height > 80) check (c_height < 250),
    c_target VARCHAR(50) not null check (c_target in ('WEIGHT_LOSS','MAINTENANCE', 'GAIN')),
    c_bmr VARCHAR not null
    );
    
    create table if not exists diet_service.t_bju_dish(
    id serial primary key,
    c_dish_name VARCHAR(50) not null check (length(trim(c_dish_name)) >= 3),
    c_calories_per_serving INT not null check (c_calories_per_serving > 0) check (c_calories_per_serving <= 10000),
    c_proteins INT not null check (c_proteins >= 0) check (c_proteins <= 100),
    c_fats INT not null check (c_fats >= 0) check (c_fats <= 100),
    c_carbohydrates INT not null check (c_carbohydrates >= 0) check (c_carbohydrates <= 100)
    );
    
    create table if not exists diet_service.t_bju_history(
    id serial primary key,
    c_date DATE,
    c_user_id INT,
    c_dish_id VARCHAR
    );

   ```
4. ### Создать образ Docker контейнера.
    ```
    docker build -t bju_api .
    ```
5. ### Запустить контейнер с программой.
   * В файле application.yaml разкомментировать путь к бд для с создания контейнера.
   ```
   url: jdbc:postgresql://host.docker.internal:5432/diet_service
   ```
   * Закомментировать путь к бд для работы в среде разработки.
   ```
   url: jdbc:postgresql://localhost:5432/diet_service
   ```
   * Запустить контейнер.
   ```
   docker run --name WalkMoreFatMan_API -d -p 8087:8087 bju_api
   ```
## Приложение готово к работе.
