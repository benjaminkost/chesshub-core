# Chesshub-core
## Overview
This repository is part of a project called ChessHub. The ChessHub project is build with a Microservice-Architecture.
The chesshub-core repository defines the service which is responsible for User-, Team-, Club- and Game-Management. 
## Installation
### Prerequisites
- Docker
- Java: >= 17
- Maven
### Setup
1. Clone the repository:
```bash
git clone https://github.com/benjaminkost/chesshub-core.git
```

2. Define .env
- copy `.env_sample` and rename it to `.env`
- define the values according to your system

3. Install dependencies and package project
```bash
mvn clean install
```
4. Start project
- navigate to webapp and run
```bash
docker compose up -d
```
## API Endpoints

| Endpoint                             | Method | Description     |
| ------------------------------------ | ------ | --------------- |
| /api/auth/register, /api/auth/signup | POST   | Register a User |
| /api/auth/login, /api/auth/signin    | GET    | Login a User    |
## Test it out

1. Open a browser and tip in: `http://localhost:8081`
2. Tip in the username and password for MySQL Server
3. Open the `chessmanagement` database
4. Open the `User` table
5. Insert a user with the command:

```bash
curl --header "Content-Type: application/json" \
--request POST \
--data {"username":"testUser","firstName": "Test","lastName": "User","email": "mail@test-user.de","password": "password","phone": "+491234567890"} \
http://localhost:8080/api/auth/register
```
## Architecture
- Framework: Spring Boot Project
- Endpoints: RESTFull APIs
- Database: MySQL
- DBMS: phpmyadmin
- DB migration: flyway
- Build and deploy: Docker compose
## Problems
Create an Issue in the Issues section in this repository.
## License
This project is licensed under the MIT License. See `License` for details.
