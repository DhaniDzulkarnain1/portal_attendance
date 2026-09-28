# Contributing

## Branch

| Branch      | Fungsi                                  |
|-------------|-----------------------------------------|
| `main`      | Kode production, hanya menerima merge dari `develop` atau `hotfix/*` |
| `develop`   | Integrasi fitur sebelum rilis           |
| `feature/*` | Fitur baru, dibuat dari `develop`       |
| `bugfix/*`  | Perbaikan bug, dibuat dari `develop`    |
| `hotfix/*`  | Perbaikan darurat, dibuat dari `main`   |

Contoh nama branch: `feature/be-check-in-api`, `feature/fe-login-page`, `bugfix/fe-date-format`.

## Alur Kerja

1. `git checkout develop && git pull`
2. `git checkout -b feature/nama-fitur`
3. Commit perubahan
4. Push dan buat Pull Request ke `develop`
5. Merge setelah review dan CI lulus

## Format Commit

Menggunakan [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <deskripsi>
```

- **type:** `feat`, `fix`, `docs`, `refactor`, `test`, `chore`, `ci`
- **scope:** `fe`, `be`, `db`, `docs`

Contoh:

```
feat(be): add check-in endpoint
fix(fe): correct attendance date format
```

## Aturan Kode

- Tidak menambahkan komentar di dalam kode; gunakan penamaan yang jelas
- Backend: `./gradlew build` harus lulus
- Frontend: `npm run lint` dan `npm run build` harus lulus
