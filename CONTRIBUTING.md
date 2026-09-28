# Contributing

## Branch

| Branch      | Fungsi                                          |
|-------------|-------------------------------------------------|
| `main`      | Kode stabil yang siap digunakan                 |
| `develop`   | Integrasi fitur sebelum dirilis ke `main`       |
| `feature/*` | Pengembangan fitur baru, dibuat dari `develop`  |
| `bugfix/*`  | Perbaikan bug, dibuat dari `develop`            |
| `hotfix/*`  | Perbaikan mendesak, dibuat dari `main`          |

Contoh penamaan: `feature/be-check-in-api`, `feature/fe-login-page`, `bugfix/fe-date-format`.

## Alur Kerja

1. Perbarui branch `develop`
   ```bash
   git checkout develop
   git pull
   ```
2. Buat branch baru
   ```bash
   git checkout -b feature/nama-fitur
   ```
3. Commit perubahan
4. Push branch dan buat Pull Request dengan base `develop`

## Format Commit

Mengikuti standar [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <deskripsi>
```

- **type:** `feat`, `fix`, `docs`, `refactor`, `test`, `chore`
- **scope:** `fe`, `be`, `db`, `docs`

Contoh:

```
feat(be): add check-in endpoint
fix(fe): correct attendance date format
```

## Standar Kode

- Gunakan penamaan yang jelas dan deskriptif
- Hindari komentar di dalam kode
