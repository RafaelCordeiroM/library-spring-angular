# Library Spring Angular

This project is a complete library management system, developed with a Spring Boot backend and Angular frontend. It allows you to create, edit, list, and delete books, authors, and categories, with a modern and responsive interface.

## Technologies Used
- **Backend:** Java, Spring Boot, Maven
- **Frontend:** Angular, Angular Material, RxJS, ngx-toastr
- **Containerization:** Docker, Docker Compose

## Project Structure
```
library-spring-angular/
├── backend/        # Spring Boot REST API
├── frontend/       # Angular application
└── docker-compose.yml
```

### Backend
- Located in `backend/`
- API for CRUD operations on books, authors, and categories
- Configuration in `application.properties`
- Built with Maven

### Frontend
- Located in `frontend/`
- Admin panel with CRUD for books, authors, and categories
- Generic components for lists and forms
- Integration with Angular Material and notifications

## How to Run

### Using Docker Compose
1. Make sure you have Docker and Docker Compose installed.
2. Run:
   ```sh
   docker-compose up --build
   ```
3. Access the frontend at `http://localhost:4200` and the backend at `http://localhost:8080`.

### Manually
#### Backend
1. Go to the `backend/` folder:
   ```sh
   cd backend
   ./mvnw spring-boot:run
   ```
2. API available at `http://localhost:8080`

#### Frontend
1. Go to the `frontend/` folder:
   ```sh
   cd frontend
   npm install
   npm start
   ```
2. Application available at `http://localhost:4200`

## Features
- Create, edit, delete, and list books
- Manage authors and categories
- Search and filter
- Responsive interface
- Success/error notifications

## Directory Structure
- `backend/src/main/java/` - Java source code
- `frontend/src/app/` - Angular source code
  - `core/` - Models, services, guards, interceptors
  - `features/` - Modules for books, authors, categories
  - `shared/` - Generic components
  - `layout/` - Layout components

## Contribution
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.
