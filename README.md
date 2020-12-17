# Breeks-server
Клинет-серверное приложение *для трекинга привычек и планирования*.
## Описание
Серверная часть Breeks - приложения для планирования и трекинга привычек.  
Ссылки:  
* [Десктоп Breeks](https://github.com/BreeksApp/Breeks-desktop)
* [Презентация приложения](https://github.com/BreeksApp/Breeks-presentation)
* [Статья на хабре]()
## Запуск тестов
`mvn clean test`  

Как Unit, так и интеграционных. Они находятся в директории `/src/tests/project/tests/`

## Сборка проекта
`mvn clean compile`  

Рекомендуется запускать в IntelliJ IDEA.

## Запуск проекта
`mvn exec:java -Dexec.mainClass="project.Breeks_server"`  

Рекомендуется запускать в IntelliJ IDEA.
