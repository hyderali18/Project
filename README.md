# TechGo - Gadget Comparison Web Application

A full-stack web application for comparing electronic gadgets like mobile phones, laptops, tablets, earphones, and speakers. Built with React + Tailwind CSS (frontend) and Spring Boot + MySQL (backend).

## 🚀 Features

- **Browse & Search**: Search gadgets by name, brand, or specifications
- **Advanced Filtering**: Filter by category, price range, brand, ratings, and more
- **Side-by-Side Comparison**: Compare up to 3 gadgets simultaneously with visual difference highlighting
- **Detailed Specifications**: View comprehensive specs for each gadget
- **User Reviews**: Read and submit reviews for gadgets
- **Responsive Design**: Works seamlessly on mobile, tablet, and desktop
- **Admin Panel**: Manage gadgets, specifications, and reviews

## 🛠️ Tech Stack

### Frontend
- **React 18** - UI framework
- **Tailwind CSS** - Utility-first CSS framework
- **React Router** - Client-side routing
- **Axios** - HTTP client for API calls
- **React Icons** - Icon library

### Backend
- **Spring Boot 3.2** - Java framework
- **Spring Data JPA** - Database abstraction
- **Spring Security** - Authentication and authorization
- **MySQL 8.0** - Database
- **Maven** - Dependency management
- **Flyway** - Database migrations

### DevOps
- **Docker & Docker Compose** - Containerization
- **Git** - Version control

## 📁 Project Structure

```
Project/
├── README.md
├── docker-compose.yml
├── frontend/                    # React application
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   ├── layout/         # Header, Footer
│   │   │   ├── common/         # GadgetCard, SearchBar
│   │   │   └── pages/          # HomePage, CategoryPage, etc.
│   │   ├── services/           # API service layer
│   │   ├── utils/              # Utility functions
│   │   ├── styles/             # Custom styles
│   │   ├── App.js
│   │   └── index.js
│   ├── package.json
│   └── tailwind.config.js
├── backend/                     # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/techgo/
│   │   │   │   ├── TechGoApplication.java
│   │   │   │   ├── controller/ # REST controllers
│   │   │   │   ├── model/      # JPA entities
│   │   │   │   ├── service/    # Business logic
│   │   │   │   ├── repository/ # Data access layer
│   │   │   │   ├── dto/        # Data transfer objects
│   │   │   │   ├── exception/  # Error handling
│   │   │   │   └── config/     # Configuration
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── db/migration/
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
└── database/                    # Database scripts
    ├── schema.sql
    └── sample_data.sql
```

## 🚀 Quick Start

### Prerequisites
- **Node.js 18+**
- **Java 17+**
- **Maven 3.8+**
- **MySQL 8.0+** (or Docker)
- **Git**

### Option 1: Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Project
   ```

2. **Start all services**
   ```bash
   docker-compose up -d
   ```

3. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080/api
   - Admin Panel: http://localhost:3000/admin

### Option 2: Local Development

#### 1. Database Setup

Start MySQL using Docker:
```bash
docker run --name techgo-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=techgo -p 3306:3306 -d mysql:8.0
```

Or use your local MySQL installation and create a database:
```sql
CREATE DATABASE techgo;
```

Import schema and sample data:
```bash
mysql -h localhost -u root -proot techgo < database/schema.sql
mysql -h localhost -u root -proot techgo < database/sample_data.sql
```

#### 2. Backend Setup

```bash
cd backend

# Install dependencies and compile
./mvnw clean install

# Start the application
./mvnw spring-boot:run
```

The backend will be available at http://localhost:8080/api

#### 3. Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm start
```

The frontend will be available at http://localhost:3000

## 📖 API Documentation

### Gadgets Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/gadgets` | Get all gadgets with pagination and filters |
| GET | `/api/gadgets/{id}` | Get gadget by ID |
| GET | `/api/gadgets/search` | Search gadgets by name |
| GET | `/api/gadgets/featured` | Get featured gadgets |
| POST | `/api/gadgets` | Create new gadget (Admin) |
| PUT | `/api/gadgets/{id}` | Update gadget (Admin) |
| DELETE | `/api/gadgets/{id}` | Delete gadget (Admin) |

### Comparison Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/comparisons` | Create new comparison |
| GET | `/api/comparisons/{id}` | Get comparison by ID |
| POST | `/api/comparisons/{id}/gadgets` | Add gadget to comparison |
| DELETE | `/api/comparisons/{id}/gadgets/{gadgetId}` | Remove gadget from comparison |
| GET | `/api/comparisons/{id}/compare` | Get side-by-side comparison data |

### Reviews Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/gadgets/{id}/reviews` | Get reviews for a gadget |
| POST | `/api/gadgets/{id}/reviews` | Add review for a gadget |
| DELETE | `/api/reviews/{id}` | Delete review (Admin) |

### Admin Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/admin/login` | Admin authentication |
| GET | `/api/admin/stats` | Get dashboard statistics |

## 🔧 Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/techgo
spring.datasource.username=root
spring.datasource.password=root

# CORS Configuration
spring.web.cors.allowed-origins=http://localhost:3000

# Admin Authentication
admin.username=admin
admin.password=admin123
```

### Frontend Configuration

Create `frontend/.env`:
```env
REACT_APP_API_BASE_URL=http://localhost:8080/api
REACT_APP_ENVIRONMENT=development
```

## 🏗️ Development Workflow

### Backend Development

1. **Create Entity**: Define JPA entity in `backend/src/main/java/com/techgo/model/`
2. **Create Repository**: Define repository interface in `backend/src/main/java/com/techgo/repository/`
3. **Create Service**: Implement business logic in `backend/src/main/java/com/techgo/service/`
4. **Create Controller**: Define REST endpoints in `backend/src/main/java/com/techgo/controller/`
5. **Add Tests**: Write unit and integration tests

### Frontend Development

1. **Create Component**: Build reusable components in `frontend/src/components/`
2. **Add Services**: Define API calls in `frontend/src/services/`
3. **Create Pages**: Implement page components in `frontend/src/components/pages/`
4. **Add Routing**: Update routes in `App.js`
5. **Style with Tailwind**: Use Tailwind CSS classes for styling

## 🐳 Docker Development

### Building Images

```bash
# Build backend image
docker build -t techgo-backend ./backend

# Build frontend image
docker build -t techgo-frontend ./frontend
```

### Running with Docker Compose

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Rebuild and start
docker-compose up --build
```

## 📊 Database Schema

### Core Tables

- **gadgets** - Main gadget information
- **gadget_specifications** - Technical specifications
- **reviews** - User reviews and ratings
- **comparison_lists** - Comparison sessions
- **comparison_items** - Gadgets in comparisons

### Key Relationships

- Gadget → Specifications (One-to-Many)
- Gadget → Reviews (One-to-Many)
- ComparisonList → ComparisonItems (One-to-Many)
- ComparisonItem → Gadget (Many-to-One)

## 🧪 Testing

### Backend Tests

```bash
cd backend
./mvnw test
```

### Frontend Tests

```bash
cd frontend
npm test
```

### API Testing

Use tools like Postman or curl to test API endpoints:

```bash
# Get all gadgets
curl http://localhost:8080/api/gadgets

# Search gadgets
curl "http://localhost:8080/api/gadgets/search?q=iphone"

# Create comparison
curl -X POST http://localhost:8080/api/comparisons \
  -H "Content-Type: application/json" \
  -d '{"name": "My Comparison"}'
```

## 🚀 Deployment

### Production Build

1. **Build Frontend**
   ```bash
   cd frontend
   npm run build
   ```

2. **Build Backend**
   ```bash
   cd backend
   ./mvnw clean package -DskipTests
   ```

3. **Deploy**
   - Frontend: Deploy `frontend/build` to web server
   - Backend: Deploy `backend/target/*.jar` to application server
   - Database: Set up production MySQL instance

### Environment Variables

Set these environment variables in production:

```bash
# Backend
SPRING_DATASOURCE_URL=jdbc:mysql://prod-db:3306/techgo
SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
ADMIN_USERNAME=${ADMIN_USERNAME}
ADMIN_PASSWORD=${ADMIN_PASSWORD}
JWT_SECRET=${JWT_SECRET}

# Frontend
REACT_APP_API_BASE_URL=https://api.yourdomain.com
REACT_APP_ENVIRONMENT=production
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Check MySQL service status
   - Verify connection string and credentials
   - Ensure database exists

2. **CORS Errors**
   - Verify frontend URL in CORS configuration
   - Check API base URL in frontend environment

3. **Build Failures**
   - Clear Maven cache: `./mvnw clean`
   - Clear npm cache: `npm cache clean --force`
   - Check Java and Node.js versions

4. **Frontend Not Loading**
   - Check if backend is running
   - Verify API endpoint connectivity
   - Check browser console for errors

### Getting Help

- Check the [Issues](../../issues) page for common problems
- Create a new issue for bugs or feature requests
- Review the logs for detailed error messages

## 🎯 Roadmap

- [ ] User authentication and profiles
- [ ] Advanced comparison features
- [ ] Product recommendation engine
- [ ] Price tracking and alerts
- [ ] Mobile app (React Native)
- [ ] Social features (share comparisons)
- [ ] Video reviews
- [ ] Integration with e-commerce platforms

---

**TechGo** - Making gadget comparison simple and informed. 🚀
