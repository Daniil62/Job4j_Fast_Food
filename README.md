# Job4j_Fast_Food
## Проект курса **[Job4j](https://job4j.ru/)**, уровня "Middle", раздела "Микросервисы".
___
### Описание проекта:
- имеет каталог блюд

- может принимать заказы клиента на сайте. Либо через скачанное клиентами приложение

- предоставляет клиенту курьерскую доставку. Клиент может контролировать положение курьера

- предоставляет курьерам приложения, где они могут отчитываться о заказах, обновлять свое положение

- имеет админку в виде веб приложения, где можно оформлять поставки продуктов, а также видеть прибыль.
___
### Требования:
- Java 17
- Maven 3.8.1
- PostrgeSQL 14.4
___
### Запуск:
- ```mvn clean package -DskipTests=true```
- ```java -jar Job4j_Fast_Food-0.0.1```
### Используемые библиотеки и технологии:
- Production:
  - Spring Boot
    - Web
    - Security
    - JPA
  - PostgreSQL
  - JWT
  - Liquibase
  - Lombok
___
- Test:       
   - Spring Boot Test
     - Security Test
   - H2 Database
___
   