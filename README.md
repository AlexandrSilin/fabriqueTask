<h1>Тестовое задание на вакансию "Java-разработчик" в "Фабрика решений"</h1>
Инструкция по запуску:
1. Из корня проекта запустиить команду sudo mvn install
2. Из корня проекта запустиить команду sudo docker-compose up

Все собиралось под системой Linux security onion. Запуск локально осуществляется следующим образом:
1. Исправить конфигурационные файлы у каждого микросервиса (заменить доменные имена на корректные ip-адреса)
2. Порядок запуска: eureka -> config-server -> api

<h2>Api</h2>
<li>GET http://localhost:9090/api/v1/admin/questions/all - получение всех вопросов
<li>GET http://localhost:9090/api/v1/admin/questions/{id} - получение конкретного вопроса
<li>GET http://localhost:9090/api/v1/admin/quiz/all - получение всех опросов
<li>GET http://localhost:9090/api/v1/admin/quiz/{id} - получение конкретного опроса
<li>GET http://localhost:9090/api/v1/user/quiz/{id} - получение конкретного опроса для пользователя
<li>GET http://localhost:9090/api/v1/user/quiz/all - получение всех опросов для пользователя
<li>POST http://localhost:9090/api/v1/admin/questions/ - добавление вопроса
<li>POST http://localhost:9090/api/v1/admin/quiz/ - добавление опроса
<li>DELETE http://localhost:9090/api/v1/admin/quiz/{id} - удаление опроса
<li>DELETE http://localhost:9090/api/v1/admin/questions/{id} - удаление вопроса
<li>GET http://localhost:9090/api/v1/login - логин
