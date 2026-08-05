# ReviewHub

Fullstack platform to rate and review movies and games, built with Spring Boot and React.

![Backend CI](https://github.com/JulioDalpiaz/reviewhub/actions/workflows/backend-ci.yml/badge.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-brightgreen)

## Overview

ReviewHub lets users browse movies and games, write reviews, rate them, and organize titles into custom lists. Built as a portfolio project to demonstrate backend architecture with Spring Boot (JPA relationships, JWT authentication, validation, testing) alongside a React frontend.

## Features

- Browse and search movies and games
- Write reviews and rate titles
- JWT authentication (register / login)
- Organize titles into custom lists
- Bean Validation on all inputs
- Automated tests (JUnit + Spring Boot Test)

## Tech Stack

| Layer | Tech |
|-------|------|
| Backend | Java 21, Spring Boot 4.1.0, Spring Data JPA, Bean Validation, Lombok |
| Frontend | React (Vite) — *under development* |
| Database | PostgreSQL |
| Auth | JWT |
| Infrastructure | Maven, Docker Compose, GitHub Actions |

## API overview (planned)

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Authenticate and receive a JWT |

### Media
| Method | Endpoint | Description |
|---|---|---|
| GET | `/media` | List media (paginated, filterable by type/category) |
| GET | `/media/{id}` | Get media details, including average rating |
| POST | `/media` | Create media (admin only) |
| PUT | `/media/{id}` | Update media (admin only) |
| DELETE | `/media/{id}` | Delete media (admin only) |

### Categories
| Method | Endpoint | Description |
|---|---|---|
| GET | `/categories` | List categories |
| POST | `/categories` | Create category (admin only) |

### Reviews
| Method | Endpoint | Description |
|---|---|---|
| POST | `/media/{id}/reviews` | Create a review for a media item |
| PUT | `/reviews/{id}` | Update a review (owner only) |
| DELETE | `/reviews/{id}` | Delete a review (owner only or admin) |
| GET | `/users/{id}/reviews` | List reviews by a user |

Full interactive documentation available via Swagger UI once implemented (`/swagger-ui.html`).

## Entity model

<!-- Add later -->

## Getting Started

Prerequisites: JDK 21, Docker (Node.js required once the frontend ships).

1. Clone the repository:

   ```sh
   git clone git@github.com:JulioDalpiaz/reviewhub.git
   cd reviewhub
   ```

2. Start PostgreSQL:

   ```sh
   docker compose up -d
   ```

3. Run the backend:

   ```sh
   cd backend
   ./mvnw spring-boot:run
   ```

4. Frontend setup instructions arrive once the React app is released.

## Project Structure

```
reviewhub/
├── backend/          # Spring Boot API
├── frontend/         # React app (in development)
├── docker-compose.yml
├── .github/workflows # CI pipeline
└── README.md
```

## Roadmap

- [ ] Entity modeling and relationships
- [ ] CRUD for media, categories, reviews
- [ ] JWT authentication and authorization
- [ ] Automated tests
- [ ] Frontend integration
- [ ] CI/CD
- [ ] Deploy

## License

MIT © Júlio Dalpiaz
