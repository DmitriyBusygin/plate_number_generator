### REST-сервис, выдающий автомобильные номера.

### Использовались:
- Spring Boot
- Spring Data
- PostgreSQL
- Lombok
- Mockito
- ModelMapper

### Описание API:

Контекст приложения: `/number`

Реализованы два GET-метода: `random` и `next`

Ответ в формате plain text.

> Запрос: `GET http://localhost:8080/number/random`
> Ответ: `С141КХ 116 RUS`

> Запрос: `GET http://localhost:8080/number/next`
> Ответ: `С142КХ 116 RUS`

### Адрес развернутого приложения:
`82.148.19.91:8080/number/random`

`82.148.19.91:8080/number/next`

**Запуск приложения:**

Для запуска приложения, необходимо создать пустую БД и ввести параметры подключения
в [application.properties.origin](src%2Fmain%2Fresources%2Fapplication.properties.origin) и переименовать
файл в application.properties. При запуске приложения таблица создастся автоматически из [schema.sql](src%2Fmain%2Fresources%2Fschema.sql)