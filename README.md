# 🚀 Resume Evaluator & Job Recommendation System

A full-stack Java application that lets job seekers upload their resume and instantly get **AI-powered job recommendations** from a database of job descriptions using **Cohere embeddings** and **Spring Boot**.

---

## ✨ Features

✅ Upload resume (PDF or TXT) via web UI
✅ Parse resume content using **Apache Tika**
✅ Store job descriptions in **MongoDB**
✅ Use **Cohere’s Embed API** (free) to match resume ↔ job descriptions
✅ Return top 3 job matches based on semantic similarity
✅ Display scores and job details via **Thymeleaf frontend**
✅ Easy to extend and deploy (fully Java-based, no Python!)

---

## 🧠 How It Works

1. User uploads their resume.
2. Resume is parsed to plain text using Apache Tika.
3. All job descriptions are fetched from MongoDB.
4. Each job description is compared with the resume using **Cohere embeddings**.
5. Cosine similarity is used to calculate a match score.
6. Top 3 results are shown on the frontend with match score & job details.

---

## 🛠 Tech Stack

| Layer          | Tech Used                                     |
| -------------- | --------------------------------------------- |
| Backend        | Java, Spring Boot                             |
| Frontend       | Thymeleaf, HTML                               |
| Resume Parsing | Apache Tika                                   |
| Database       | MongoDB                                       |
| AI Matching    | Cohere Embed API (Free)                       |
| Build Tool     | Maven                                         |
| Deployment     | Runs locally or on any Spring-compatible host |

---

## 📂 Project Structure

```
resume-evaluator/
├── src/
│   ├── main/
│   │   ├── java/com/resumeai/
│   │   │   ├── controller/JobController.java
│   │   │   ├── services/JobService.java
│   │   │   ├── services/CohereService.java
│   │   │   ├── model/JobDescription.java
│   │   │   └── repository/JobDsRepo.java
│   │   └── resources/
│   │       ├── templates/index.html
│   │       └── application.properties
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repo

```bash
git clone https://github.com/your-username/resume-evaluator.git
cd resume-evaluator
```

### 2. Add Your Cohere API Key

Register at [cohere.ai](https://cohere.ai) and get your API key.

In `src/main/resources/application.properties`, add:

```properties
cohere.api.key=your_cohere_api_key
spring.data.mongodb.uri=mongodb://localhost:27017/Job_Database
```

### 3. Add Sample Job Descriptions

Use MongoDB Compass or add via Postman:

```json
{
  "jobTitle": "Backend Developer",
  "jobSummary": "Java developer with Spring Boot and SQL experience.",
  "keyResponsibility": "Build and maintain backend systems.",
  "requirements": "Java, Spring Boot, MySQL"
}
```

> Or use `/api/job/add` endpoint to POST jobs via JSON.

---

### 4. Run the App

```bash
mvn clean install
mvn spring-boot:run
```

### 5. Access the App

Visit: [http://localhost:8080/api/job/](http://localhost:8080/api/job/)

Upload your resume and see the top job matches instantly.

---

## 🧪 Testing

You can test with this 📎 [Download Sample Resume](https://github.com/Arnesh-02/Job-Ai/blob/main/resume-evaluator/src/main/resources/John%20Doe%20-resume.pdf)
(or use your own `.pdf` or `.txt` file).

Expected output: top 3 job descriptions sorted by match score.

---

## 📦 API Endpoints

| Method | Endpoint         | Description                    |
| ------ | ---------------- | ------------------------------ |
| `GET`  | `/api/job/`      | Resume upload page (Thymeleaf) |
| `POST` | `/api/job/match` | Upload resume & get matches    |
| `GET`  | `/api/job/all`   | Get all job descriptions       |
| `POST` | `/api/job/add`   | Add new job description (JSON) |

---

## ✅ Improvements You Can Add

* Show reason for match (skills overlap)
* Add OpenAI or Cohere Generate API for resume improvement tips
* Store history of matched jobs in DB
* Add admin panel to manage jobs

---

## 🧑‍💻 Author

**Arnesh R**
BE CSE | Backend Developer
[LinkedIn](https://linkedin.com/in/your-profile) | [GitHub](https://github.com/your-username)

---

## 📄 License

This project is open source under the [MIT License](LICENSE).

---
