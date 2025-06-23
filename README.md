# 🏥 MediBot Afrika — AI-Powered Rural Healthcare Assistant

**MediBot Afrika** is an AI-powered mobile and USSD-based healthcare assistant tailored for **community health workers (CHWs)** and underserved populations in Africa. It aims to enhance **early diagnostics**, **triage**, **referral management**, and **health education** — even in low-connectivity environments.

---

## 🌍 Project Vision

To empower CHWs in rural Africa with an intelligent assistant that bridges the healthcare access gap through localized, affordable, and tech-enabled care delivery.

---

## 📱 Key Features

| Feature                            | Description                                                                 |
|------------------------------------|-----------------------------------------------------------------------------|
| 🤖 AI Symptom Checker              | Simple diagnostic assistant using structured inputs (with FHIR compatibility).|
| 📡 Offline-First Mobile App        | Built using Android + Open Health Stack; supports offline usage and syncs when online.|
| ☎️ USSD Access                     | Lightweight USSD interface for basic triage and education in low-end phones.|
| 🧠 Health Literacy Chatbot         | Educates users about common diseases, nutrition, maternal care, etc.        |
| 🏥 Referral Engine                 | Suggests nearby facilities and specialists based on symptoms and location.  |
| 🔐 Privacy-First Architecture      | Stores health data securely; designed for ethical AI and informed consent.  |

---

## 🧩 Tech Stack

| Layer              | Technologies Used                                  |
|--------------------|-----------------------------------------------------|
| Frontend (Mobile)  | Kotlin, Android SDK, Jetpack Compose               |
| Backend/API        | Django, Django REST Framework, PostgreSQL          |
| AI/Diagnostics     | Python, Scikit-learn, HuggingFace Transformers     |
| USSD Gateway       | Africa's Talking / Twilio (for USSD/SMS)           |
| Standards          | HL7 FHIR, Open Health Stack (OHS), HAPI FHIR       |
| DevOps             | Docker, GitHub Actions (CI/CD), Render/Heroku      |

---

## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/medibot-afrika.git
cd medibot-afrika
