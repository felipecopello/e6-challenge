# 💳 e6-challenge

A Java project demonstrating a configurable **Credit Card system** using the **Builder pattern**, centralized **Validator**, and **JSON-driven JUnit 5 tests**.

---

## 🧩 Features

- **Builder Pattern:** Safe, fluent setup of credit card attributes.
- **Validator:** Ensures all configurations are valid (e.g., balance transfer logic, transaction types).
- **JSON Tests:** Parameterized tests using JSON data for flexibility.
- **Factory & Defaults:** Ready-made card configurations.

---

## 📂 Structure
```plaintext
src/
├── main/
│    └──java/
│        └──com/
│            └──episode6/
│                ├── enums/ # TransactionType enum
│                ├── models/ # AbstractCreditCard & CreditCard (builder)
│                └── utils/ # Validator and JSON utils
└── test/
    ├──java/
    │   └──com/
    │        └──episode6/
    │               ├── utils/ # Data providers
    │               ├── CreditCardValidatorTest.java
    │               ├── CreditCardParameterizedTest.java
    └── resources/
        └── data/
            ├── credit-cards.json
            └──  invalid-credit-cards.json
```

## Technologies Used

| Technology              | Purpose                                                |
|-------------------------|--------------------------------------------------------|
| **Java 17**             | Modern language features                               |
| **Lombok**              | Generates boilerplate (getters, setters, constructors) |
| **Gradle (Kotlin DSL)** | Build system and dependency management                 |
| **Spotless**            | Automatic code formatting using Google Java Style      |
| **Jacoco**              | Reporting on code and branch coverage                  |

## 🧪 Run Tests

```bash
./gradlew test
Reports:

Tests → build/reports/tests/test/index.html

Coverage → build/reports/jacoco/test/html/index.html