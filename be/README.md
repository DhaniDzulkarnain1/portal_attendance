# Portal Attendance - Backend

REST API Portal Attendance menggunakan Kotlin Spring Boot.

## Tech Stack

- Kotlin
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL

## Struktur Folder

```
src/main/kotlin/com/portalattendance/
├── config/
├── controller/
├── dto/
│   ├── request/
│   └── response/
├── entity/
├── exception/
├── repository/
└── service/

src/main/resources/
└── application.yml

database/
└── schema.sql
```

## Database

Buat database `portal_attendance`, lalu jalankan `database/schema.sql` melalui DBeaver atau psql:

```bash
psql -U postgres -d portal_attendance -f database/schema.sql
```

## Menjalankan

```bash
./gradlew bootRun
```

Windows:

```powershell
.\gradlew.bat bootRun
```

API berjalan di `http://localhost:8080/api`.

## Konfigurasi Database

| Variable      | Default                                              |
|---------------|------------------------------------------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5432/portal_attendance` |
| `DB_USERNAME` | `postgres`                                           |
| `DB_PASSWORD` | `postgres`                                           |
