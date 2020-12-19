# Breeks-server
Клиент-серверное приложение *для трекинга привычек и планирования*.
## Описание
Серверная часть Breeks - приложения *для трекинга привычек и планирования*.  
Ссылки:  
* [Десктоп Breeks](https://github.com/BreeksApp/Breeks-desktop)
* [Презентация приложения](https://github.com/BreeksApp/Breeks-presentation)
* [Статья о приложении на Medium](https://yarpylaev.medium.com/breeks-построй-свою-неделю-333cd15a6c90)
## Запуск тестов
`mvn clean test`  

Как Unit, так и интеграционных. Они находятся в директории `/src/tests/project/tests/`

## Сборка проекта
`mvn clean compile`  

Рекомендуется запускать в IntelliJ IDEA.

## Запуск проекта
`mvn -Dspring-boot.run.folders=./ spring-boot:run` в корне  

Рекомендуется запускать в IntelliJ IDEA.
