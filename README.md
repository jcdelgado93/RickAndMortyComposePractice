# Rick & Morty Showcase App 🧪

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)

Native Android application developed as a **Proof of Concept (PoC)** to demonstrate modern, scalable, and reactive architectural patterns. The project strictly follows **Clean Architecture** principles and the **Offline-First** paradigm.

## 🏗️ Architecture & Tech Stack

The project is modularized by layers following SOLID principles:

* **Language:** Kotlin (100%).
* **UI:** Jetpack Compose (Modern Declarative UI).
* **Architecture:** Clean Architecture (Presentation, Domain, Data) + MVVM.
* **Dependency Injection:** Dagger Hilt.
* **Network:** Retrofit2 + Gson.
* **Local Persistence (Offline-First):** Room Database + TypeConverters.
* **Concurrency:** Kotlin Coroutines + Flows.
* **Images:** Coil.

## 🚀 Key Features (Showcase)

### 1. Offline-First (Single Source of Truth)
The application implements a strict **"Single Source of Truth"** pattern. The UI never consumes data directly from the API.
1.  Data is fetched from the API (Retrofit).
2.  Immediately persisted into the local database (Room).
3.  The UI reactively observes the database via `Flows`.
*This guarantees the app functions seamlessly without an internet connection once data has been cached.*

### 2. Granular State Management
Simplified **MVI (Model-View-Intent)** implementation using typed `UiStates`.
* **Separation of Concerns:** Specialized ViewModels (`ListViewModel` vs `DetailViewModel`).
* **State Handling:** Type-safe handling of Loading, Success, and Error states.

### 3. Complex Data Persistence
Advanced usage of **Room TypeConverters** to serialize and persist complex nested objects (such as `Origin` and `Location`) and lists, overcoming SQLite's relational limitations without compromising the domain model integrity.

### 4. Modern UI/UX
* **Hero Animations:** Immersive design pattern in the Character Detail screen.
* **Navigation:** Jetpack Navigation Compose.
* **Reusable Components:** Atomic UI component architecture (Shimmer Effects, Data Rows, Cards).

## 🛠️ Project Structure

```text
com.example.rickandmorty
├── data                # Data Layer (Repositories, API, Room DTOs)
│   ├── local           # Database, DAO, Entities, Converters
│   ├── remote          # API Interface, Network DTOs
│   └── repository      # Repository Implementation (Single Source of Truth Logic)
├── domain              # Domain Layer (Pure Business Rules)
│   ├── model           # Agnostic Data Models
│   ├── repository      # Repository Interfaces
│   └── usecase         # Use Cases (Interactors)
├── di                  # Hilt Modules (AppModule, DataModule, etc.)
└── presentation        # UI Layer (Compose)
    ├── components      # Reusable Composables
    ├── navigation      # Navigation Graph
    ├── screens         # Screens (List, Detail)
    ├── state           # UiStates (View State Data Classes)
    └── viewmodel       # ViewModels (ListViewModel, DetailViewModel)

🚧 Roadmap / Next Steps
[x] Base Implementation (Clean Arch + MVVM)
[x] Offline Persistence (Room)
[x] Detail UI with Complex Data
[ ] Advanced Pagination (RemoteMediator + Paging3)
[ ] Unit Testing (JUnit + Mockk)
[ ] UI Tests (Compose Test)

Developed by [Julio Delgado] - 2026
