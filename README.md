# Breeks-server
Клиент-серверное приложение *для трекинга привычек и планирования*.
## Описание
Серверная часть Breeks - приложения *для трекинга привычек и планирования*.  
Ссылки:  
* [Десктоп Breeks](https://github.com/BreeksApp/Breeks-desktop)
* [Презентация приложения](https://github.com/BreeksApp/Breeks-presentation)
## Запуск тестов
`mvn clean test`  

Как Unit, так и интеграционных. Они находятся в директории `/src/tests/project/tests/`

## Сборка проекта
`mvn clean compile`  

Рекомендуется запускать в IntelliJ IDEA.

## Запуск проекта
`mvn -Dspring-boot.run.folders=./ spring-boot:run` в корне  

Рекомендуется запускать в IntelliJ IDEA.
