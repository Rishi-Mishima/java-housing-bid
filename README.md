# 🏡 HousingBid NL

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-PostGIS-blue.svg)](https://postgis.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An intelligent, data-driven real estate evaluation and bidding recommendation system for the Dutch housing market.

Instead of relying on fragile web scraping, **HousingBid NL** integrates directly with official Dutch government geodata APIs (**PDOK / BAG**) to extract high-accuracy building metrics, performs spatial proximity queries using **PostgreSQL + PostGIS**, and computes data-backed house valuations (`estimatedValue`) and bidding suggestions (`recommendedBid`).

---

## ✨ Key Features

- 🇳🇱 **Official Dutch Geodata Integration**: Fetch validated property attributes (usable floor area `oppervlakte`, construction year `bouwjaar`, coordinates) directly via PDOK/BAG public APIs by entering a Dutch address or postcode.
- 📍 **Spatial Proximity Search**: Leverage **PostGIS** geospatial indexing to identify recently sold comparable properties (*referentiewoningen*) within a customizable radius (e.g., 500m / 1km).
- 🧮 **Algorithmic Valuation Engine**: Calculate a baseline market value (`estimatedValue`) and intelligent bidding bounds (`recommendedBid`) based on weighted historical sales, floor area, age, and neighborhood price trends.
- 📊 **Historical Transaction Ingestion**: Batch import or store actual historic transaction records for algorithmic training and backtesting.
- 🔮 **Future-Ready ML Pipeline**: Architected to seamlessly plug in statistical regression or machine learning models (e.g., XGBoost / Random Forest via Python microservice or Java ONNX runtime) for advanced valuation.

---

## 🛠️ Tech Stack

* **Core Backend**: Java 17+, Spring Boot 3.x, Spring Data JPA
* **Database & Geospatial**: PostgreSQL with PostGIS extension
* **External APIs**:
   
* **Build Tool**: Apache Maven
* **Documentation & Testing**: Swagger / Open API 3, JUnit 5, Testcontainers

---

## 📂 Project Structure

```text
housing-bid-nl/
├── src/
│   ├── main/
│   │   ├── java/com/housingbid/
│   │   │   ├── config/             # Spring & PostGIS/WebClient configurations
│   │   │   ├── controller/         # REST API Controllers (Address, Property, Valuation)
│   │   │   ├── dto/                # Request & Response Data Transfer Objects
│   │   │   ├── model/              # JPA Entities (Property, Transaction, ValuationResult)
│   │   │   ├── repository/         # Spring Data JPA Repositories (with Spatial Queries)
│   │   │   ├── service/            # Core business logic
│   │   │   │   ├── pdok/           # PDOK / BAG API Integration Client
│   │   │   │   ├── valuation/      # Rule-based & Statistical Valuation Engines
│   │   │   │   └── PropertyService.java
│   │   │   └── HousingBidApplication.java
│   │   └── resources/
│   │       ├── application.yml     # Application configuration & API keys
│   │       └── db/migration/       # Flyway database migration scripts
│   └── test/                       # Unit and Integration tests
├── pom.xml                         # Maven dependencies
├── .gitignore                      # Git ignore rules
└── README.md                       # Project documentation
```

---

## 🗺️ System Architecture & Workflow
```text
[ User Input ] (Postcode + House Number)
       │
       ▼
 [ Spring Boot Backend ]
       │
       ├───► Call PDOK / BAG API ──────► Retrieve Coordinates, Area (m²), Bouwjaar
       │
       ├───► Save/Update PostgreSQL (PostGIS)
       │
       ├───► Execute Spatial Query ────► Find Comparable Sales in X-km Radius
       │
       └───► Valuation Engine ─────────► Compute Estimated Value & Recommended Bid
       │
       ▼
 [ JSON Response / Frontend ] ────► { estimatedValue, recommendedBid, confidenceScore }
```
