<h1>Тестовое задание на вакансию "Java-разработчк" в "Фабрика решений"</h1>
Инструкция по запуску:
1. Из корня проекта запустиить команду sudo mvn install
2. Из корня проекта запустиить команду sudo docker-compose up

Все собиралось под системой Linux security onion. Сбока локально осуществляется следующим образом:
1. Исправить конфигурационные файлы у каждого микросервиса (заменить доменные имена на корректные ip-адреса)
2. Порядок запуска: eureka -> config-server -> api

<h2>Api</h2>
http://localhost:9090/api/v1/admin/questions/all - получение всех вопросов
http://localhost:9090/api/v1/admin/questions/{id} - получение конкретного вопроса
http://localhost:9090/api/v1/admin/quiz/all - получение всех опросов
http://localhost:9090/api/v1/admin/quiz/{id} - получение конкретного опроса
http://localhost:9090/api/v1/user/quiz/{id} - получение конкретного опроса для пользователя
http://localhost:9090/api/v1/user/quiz/all - получение всех опросов для пользователя

POST http://localhost:9090/api/v1/admin/questions/ - добавление вопроса
POST http://localhost:9090/api/v1/admin/quiz/ - добавление опроса

DELETE http://localhost:9090/api/v1/admin/quiz/{id} - удаление опроса
DELETE http://localhost:9090/api/v1/admin/questions/{id} - удаление вопроса
