# 🚀 Swag Labs UI Automation Framework

---

## О проекте

**Swag Labs UI Automation Framework** — это современный, масштабируемый фреймворк для автоматизации тестирования интернет-магазина [Swag Labs](https://www.saucedemo.com/).  
Проект построен на лучших практиках индустрии и полностью интегрирован с Docker, Jenkins и Selenoid для параллельных и кроссбраузерных запусков.

---

## Структура проекта
```
src/
├── main/
│   └── java/
│       ├── pages/            # Page Object классы для всех страниц (LoginPage, ProductsPage и т.д.)
│       ├── components/       # Переиспользуемые компоненты (Header, Footer, Sidebar, CartIcon и др.)
│       ├── enums/            # Перечисления для опций, статусов, сортировок и др.
│       ├── constants/        # Константы: строки, тестовые данные, названия товаров и др.
│       └── utils/            # Утилиты (работа с датой, файлами, генерация данных, кастомные ожидания)
├── test/
│   └── java/
│       ├── base/             # Базовые классы для тестов (BaseTest, BaseApiTest и др.)
│       ├── tests/            # Все тестовые классы (LoginTest, ProductsTest, CartTest и др.)
│       │   └── ui/           # UI-тесты
│       └── testdata/         # Фикстуры, генераторы тестовых данных (по желанию)
└── resources/
    ├── config/               # Конфиги окружений, Owner .properties, browsers.json для Selenoid
    ├── selenide.properties   # Конфиг Selenide (baseUrl, browser и др.)
    ├── junit-platform.properties # Параллельность JUnit 5
    ├── allure.properties     # Конфиг Allure
    └── testdata/             # Тестовые данные (json, csv, xml и др.)

```
---

## Технологический стек

- **Java 17+**
- **Selenide** 
- **Selenoid**
- **JUnit 5** 
- **Allure Reports** 
- **Lombok, SLF4J**
- **Owner**
- **Awaitility**
- **Gradle**
- **Docker**
- **Jenkins**

---

## Архитектурные паттерны

- **Page Object Model (POM)**
- **Page Component Pattern** (Header, Footer, Sidebar)
- **SOLID, DRY, KISS**
- **Fluent API** для всех PageObject
- **Модульная структура**: pages, components, enums, constants, utils, tests

---

## Возможности

- Параллельный запуск тестов (JUnit 5 + Selenoid)
- Сбор скриншотов и видео при падении тестов
- Автоматическое формирование Allure-отчётов
- Гибкая настройка браузеров и окружений через Owner/selenide.properties
- Удобная интеграция с Jenkins CI/CD

---

## Как это выглядит

### 1. Docker-инфраструктура

![Docker Compose: Jenkins + Selenoid](src/test/resources/images/Docker.jpg)

- Jenkins, Selenoid, Selenoid UI и браузеры запускаются в контейнерах.
- Selenoid UI доступен по адресу: [http://localhost:8081](http://localhost:8081)

---

### 2. Параметризированный запуск Jenkins

![Параметризация](src/test/resources/images/Parameters.jpg)

- Pipeline flow 

![Jenkins стадии](src/test/resources/images/Stages.jpg)

---

### 3. Selenoid UI: Мониторинг браузерных сессий, VNC-доступ к браузеру

![Selenoid UI: Сессии и браузеры](src/test/resources/images/SelenoidContainers.jpg)

- В реальном времени видны все активные сессии, VNC-доступ, статус браузеров.

![Selenoid UI: VNC просмотр сессии](src/test/resources/images/SelenoidUI.jpg)

- Можно наблюдать за выполнением теста прямо из браузера через VNC.
---

### 4. Allure

![Selenoid UI: VNC просмотр сессии](src/test/resources/images/Allure.jpg)

- Можно увидеть результат прохождения тестов с шагами выполнения

---

## Best Practices

- **Нет magic strings:** все опции, товары, статусы — через enum/константы.
- **Общие элементы (header, footer, sidebar)** — вынесены в компоненты.
- **Гибкая конфигурация:** смена браузера, URL, таймаутов — без перекомпиляции.
- **Параллельность:** тесты запускаются в несколько потоков.
- **Allure:** шаги, скриншоты, видео, VNC — всё в отчёте.

---
