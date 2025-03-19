create schema if not exists diet_service;

create table if not exists diet_service.t_bju_user(
    id            serial primary key,
    c_user_name varchar(50) not null check (length(trim(c_user_name)) >= 3),
    c_gender varchar not null,
    c_email varchar(50) not null check (length(trim(c_email)) >= 5),
    c_age INT not null check (c_age > 0) check (c_age <= 120),
    c_weight INT not null check (c_weight > 0) check (c_weight < 300),
    c_height INT not null check (c_height > 80) check (c_height < 250),
    c_target varchar not null,
    c_bmr varchar not null
    );

create table if not exists diet_service.t_bju_dish(
    id            serial primary key,
    c_dish_name varchar(50) not null check (length(trim(c_dish_name)) >= 3),
    c_calories_per_serving INT not null check (c_calories_per_serving > 0) check (c_calories_per_serving <= 10000),
    c_proteins INT not null check (c_proteins >= 0) check (c_proteins <= 100),
    c_fats INT not null check (c_fats >= 0) check (c_fats <= 100),
    c_carbohydrates INT not null check (c_carbohydrates >= 0) check (c_carbohydrates <= 100)
    );