<h1>Тестовое задание на вакансию "Java-разработчк" в "Фабрика решений"</h1>
Инструкция по запуску:
1. Из корня проекта запустиить команду sudo mvn install
2. Из корня проекта запустиить команду sudo docker-compose up

Все собиралось под системой Linux security onion. Сбока локально осуществляется следующим образом:
1. Исправить конфигурационные файлы у каждого микросервиса (заменить доменные имена на корректные ip-адреса)
2. Порядок запуска: eureka -> config-server -> api
