# Sprint_4-Scooter_UI-test

## О проекте
Автотесты для учебного проекта **"Самокат"** (курс Яндекс.Практикума).  
Проект написан на **Java + Selenium + JUnit4 + Maven** и покрывает функционал веб-приложения:
- оформление заказа самоката,
- проверка валидации формы,
- работа раздела «Вопросы о важном» (FAQ),
- проверка поиска заказа.

## Технологии
- Java 11
- Selenium WebDriver 4.x
- JUnit4 (Parameterized + Rule)
- Maven Surefire Plugin
- Page Object Model (POM)

## Запусти тесты
По умолчанию тесты идут в Chrome: mvn clean test

Для запуска в Firefox: mvn clean test -Dbrowser=firefox

## Примеры сценариев
FaqTest – проверка открытия ответов на вопросы в блоке FAQ

OrderForWhomPageTest – оформление заказа и проверка ошибок формы

ScooterTest – проверка поиска несуществующего заказа

## Структура проекта
Samokat

├── src

│   └── main/java/ru/yandex/practicum/pages     # Page Object'ы

│   └── main/java/ru/yandex/practicum/pages/util      # Вспомогательные классы/утилиты

│   └── test/java/ru/yandex/practicum/test      # JUnit

├── pom.xml                                     # зависимости Maven

└── README.md                                   # описание проекта



