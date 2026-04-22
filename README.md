# Crop Disease Identification 🌾🩺

Crop Disease Identification is a JavaFX desktop application for metadata-driven crop disease exploration and guided symptom-based identification.

It combines:

- A layered Java architecture (GUI, domain, infrastructure)
- JSON-based crop metadata
- An interactive UI flow from login to crop search and disease identification

The goal is to keep disease knowledge easy to expand by editing metadata, not core application logic.

## ✨ Highlights

- JavaFX desktop app with FXML-based pages and controllers
- Login and signup flow backed by JSON user persistence
- Metadata-driven crop catalog (index + per-crop files)
- Crop search mode with dynamic result cards
- Disease identification mode with symptom autocomplete suggestions
- Refactored separation of concerns:
    - Metadata index parsing in loader layer
    - Crop catalog search/symptom logic in domain service layer

## 🚀 Current Feature Set

### 🔐 Authentication and Session

- Login and signup screens
- User data persisted in JSON
- Home scene initialized with current user context

### 🏠 Home and Navigation

- Header actions: Home, News, Feeds, Settings
- Collapsible settings panel with account and session actions
- Trending crop shortcuts

### 🔎 Crop Search

- Search text field with validation
- Focused result layout (non-essential side panels hidden)
- Uniform, clickable crop result cards
- Empty state messaging when no match is found

### 🧪 Disease Identification (Current UX Stage)

- Opened from either:
    - Search result card click
    - Trending crop button click
- Focused layout similar to search mode
- Selected crop summary shown at top
- Editable symptom selector with autocomplete suggestions sourced from crop metadata

## 🛠️ Tech Stack

- Java 25
- Maven
- JavaFX 26 (Controls + FXML)
- Gson 2.13.2

## 🧱 Architecture Overview

The project follows a layered structure:

- GUI layer
    - Views and JavaFX controllers
    - Scene/page orchestration and interaction handling
- Domain layer
    - Models (Crop, Disease, User)
    - Services for business logic
- Infrastructure layer
    - Resource loading utilities
    - JSON mapping and persistence adapters

### ♻️ Notable Refactor Components

- Metadata index loader:
    - [src/main/java/com/crop/app/infrastructure/loader/MetadataIndexLoader.java](src/main/java/com/crop/app/infrastructure/loader/MetadataIndexLoader.java)
- Crop catalog service:
    - [src/main/java/com/crop/app/domain/service/CropCatalogService.java](src/main/java/com/crop/app/domain/service/CropCatalogService.java)

## 🗂️ Project Structure

```text
crop_disease_identification/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java                  # Java module config, exports, opens for JavaFX/Gson
│   │   │   └── com/crop/app/
│   │   │       ├── Main.java                     # Application entry point and primary stage bootstrap
│   │   │       ├── common/                       # Shared constants and custom exception types
│   │   │       ├── domain/
│   │   │       │   ├── model/                    # Core entities (Crop, Disease, User)
│   │   │       │   ├── repository/               # Repository interfaces (domain contracts)
│   │   │       │   └── service/                  # Business logic (auth, crop catalog, search)
│   │   │       ├── gui/
│   │   │       │   ├── controller/               # JavaFX controllers (event handling, UI flow)
│   │   │       │   └── view/                     # Scene/page wrapper classes
│   │   │       └── infrastructure/
│   │   │           ├── loader/                   # Classpath/resource loaders (FXML, metadata index, assets)
│   │   │           ├── mapper/                   # JSON-to-domain mappers
│   │   │           └── persistence/              # JSON-backed repository implementations
│   │   └── resources/
│   │       └── com/crop/app/
│   │           ├── db/                           # Stored application/user data files
│   │           ├── metadata/                     # Crop catalog + per-crop disease metadata
│   │           ├── pages/                        # FXML page layouts used by JavaFX
│   │           ├── styles/                       # CSS theme and page styling
│   │           ├── images/                       # Crop and UI image assets
│   │           └── icons/                        # App and UI icon assets
│   └── test/                                     # Tests (unit/integration as project evolves)
├── docs/                                         # Project docs and metadata notes
├── templates/                                    # Header/template support files
├── pom.xml                                       # Maven build configuration
├── README.md                                     # Project documentation
├── CONTRIBUTORS.md                               # Contributor list and roles
├── LICENSE                                       # Apache-2.0 license text
└── NOTICE                                        # Attribution and notice information
```

## 📚 Metadata Strategy

The app uses an index-plus-files pattern:

- [src/main/resources/com/crop/app/metadata/metadata-index.json](src/main/resources/com/crop/app/metadata/metadata-index.json) lists active crops
- Each active crop points to a dedicated metadata file
- Each crop file contains crop details and a diseases array

Benefits:

- Add new crops without changing UI/controller logic
- Keep disease data updates decoupled from code releases
- Easier maintenance and review of data changes

## ✅ Prerequisites

- JDK 25
- Maven 3.9+

Verify your environment:

```bash
java -version
mvn -version
```

## ▶️ Run Locally

From the project root:

```bash
mvn clean javafx:run
```

## 🧪 Build and Validate

Package the application:

```bash
mvn clean package
```

Run test phase:

```bash
mvn test
```

Build artifacts are generated under target.

## 🌱 Extend the Catalog (Add a New Crop)

1. Add a new metadata file under [src/main/resources/com/crop/app/metadata](src/main/resources/com/crop/app/metadata).
2. Add an active entry in [src/main/resources/com/crop/app/metadata/metadata-index.json](src/main/resources/com/crop/app/metadata/metadata-index.json).
3. Ensure the new crop JSON includes:
    - Crop identity fields
    - Description
    - Diseases with symptoms and treatments

No controller rewrite is required for search and symptom suggestion support.

## 🧭 Development Notes

- Keep runtime assets in src/main/resources.
- Load files through classpath loaders, not filesystem absolute paths.
- Prefer keeping controllers focused on UI orchestration only.
- Place parsing/loading concerns in infrastructure loaders and domain logic in services.

## 🤝 Contributors

- Md. Rafi Sarkar ([rafisarkar0128](https://github.com/rafisarkar0128)) - Lead Developer and Maintainer
- Ainul Huque ([ainul10222](https://github.com/ainul10222)) - Lead Developer and Maintainer

See [CONTRIBUTORS.md](CONTRIBUTORS.md) for contributor details.

## 📄 License

Licensed under the Apache License 2.0.

- License text: [LICENSE](LICENSE)
- Attribution and notice: [NOTICE](NOTICE)
