# 🚀 Vacancy Management Application

A backend system for managing job vacancies, designed to demonstrate real-world deployment, authentication, and database integration. Deployed on **AWS EC2**, backed by **PostgreSQL via RDS**, containerized with **Docker**, and powered by **GitHub Actions** for continuous delivery.

---

## 🎯 Features

- 🔐 JWT-based authentication for Candidates & Companies  
- 👥 User registration and login flows  
- 📄 Create, read, update, delete job vacancies  
- 🛡️ Role-based access with Spring Security  
- 🗃️ PostgreSQL database hosted on AWS RDS  
- 🚢 Containerized deployment to AWS EC2 via GitHub Actions  
- 📦 Docker image hosted on GitHub Container Registry (GHCR)  

---

## 🛠️ Tech Stack

| Layer            | Technologies                           |
|------------------|----------------------------------------|
| Backend          | Java 21, Spring Boot                   |
| Security         | JWT, Spring Security                   |
| Database         | PostgreSQL (AWS RDS)                   |
| Containerization | Docker, GitHub Container Registry      |
| Infrastructure   | GitHub Actions, AWS EC2                |
| CI/CD            | GitHub Actions Workflow                |

---

## 🧪 Local Development

```bash
# Clone repo and build
git clone https://github.com/caiquepirs/vacancy-management.git
cd vacancy-management
mvn clean install

# Build Docker image
docker build -t vacancy-management .

# Run container with environment variables
docker run -d -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://localhost:5432/your_db \
  -e DATABASE_USERNAME=your_user \
  -e DATABASE_PASSWORD=your_pass \
  -e SECRET_KEY_CANDIDATE=your_candidate_jwt_secret \
  -e SECRET_KEY_COMPANY=your_company_jwt_secret \
  --name vacancy-management \
  vacancy-management
```

---

## 📚 API Documentation

Interactive Swagger UI is available to explore all endpoints and test requests/responses.

> Access it via:

```
http://13.53.132.128:8080/swagger-ui/index.html#/
```

Make sure this path is exposed in your Spring configuration and the port is open in EC2 inbound rules.

---

## 🌍 Production Deployment

The app is deployed using GitHub Actions to an AWS EC2 instance. On push to `main`, the workflow:

1. Builds the project with Maven  
2. Creates a Docker image  
3. Publishes to GitHub Container Registry  
4. Connects to EC2 and pulls the new image  
5. Restarts the container with updated environment configs

---

## 🔐 Environment Variables

Configured via GitHub Secrets and injected during deployment:

- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `SECRET_KEY_CANDIDATE`
- `SECRET_KEY_COMPANY`

---

## 💻 Live URL

> Your app is live at:

```
http://13.53.132.128:8080/
```

Make sure port `8080` is open in the EC2 security group!

---

## 🙋 Author

Made with passion by [**Caique Pirs**](https://github.com/caiquepirs) 🧑‍💻  
Connect with me to build smart apps and cooler ideas!

---

## 🎓 Inspiration

This project was built to learn:
- Real-world backend deployment
- Automated CI/CD pipelines
- Cloud infrastructure & container orchestration

And share a bit of developer joy in the process 😄

---

## 📣 Want More?

Feel free to fork, star, or submit a PR. Got questions or ideas? Drop an issue or hit me up on GitHub!
