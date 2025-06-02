# Klivvr Android Assignment

This project is a solution to the Klivvr Android Assignment, designed to demonstrate problem-solving abilities, UX sensibilities, code quality, and architectural decisions in building a performant and responsive city search application.

---

## DEMO

https://github.com/user-attachments/assets/d8356d9c-9d2e-4899-b6f3-06c1a036eace

## 📱 Features

* Loads a large JSON file (\~200k city entries) on startup
* Fast and case-insensitive prefix search optimized beyond linear time complexity
* Search results update live as user types
* Scrollable, alphabetically ordered list with sticky group headers and animated UI states
* Responsive UI with Jetpack Compose and proper state handling
* Detailed city view opens Google Maps with location
* Smooth search bar animations between states (focused/unfocused)
* Clean architecture with proper domain-driven design (DDD)

---

## 🔧 Tech Stack

* **Language**: Kotlin
* **UI**: Jetpack Compose
* **Architecture**: MVVM (Single State)
* **Dependency Injection**: Hilt
* **Concurrency**: Kotlin Coroutines
* **JSON Parsing**: Gson
* **Testing**: Unit Testing (ViewModel, UseCases)
* **Tools in Progress**: ProGuard, Baseline Profiler

---

📦 Modularization Strategy

This project uses modularization by component, inspired by The Real Clean Architecture in Android - Modularization.

📦 Modularization Strategy
This project uses modularization by component, inspired by The Real Clean Architecture in Android - Modularization.
| Type                       | Description                                                                                                                          | Pros                                                                               | Cons                                                             |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| **Package by Layer**       | Organizes code by technical role (e.g., `ui`, `domain`, `data`) inside one module                                                    | Simple to start; common in tutorials                                               | Becomes messy as app grows; tight coupling across layers         |
| **Package by Feature**     | Each feature is a module containing its own `ui`, `domain`, `data` layers                                                            | Feature isolation; great scalability for large teams                               | Duplication of cross-cutting concerns; overkill for smaller apps |
| **Package by Component** ✅ | Modules are organized by *logical components* (e.g., `citysearch-ui`, `citysearch-domain`, `core-ui`) rather than features or layers | Clear separation of responsibilities; better reuse; easier testing and maintenance | Requires thoughtful architecture up front                        |

In this project:

feature:choose handles only UI logic and interacts with state from the domain

component:city-domain encapsulates use cases and business rules

component:city-data manages JSON parsing and repository implementations

Shared UI or core utilities can go into reusable modules like core-ui


## ⚙️ Architecture Overview

### Clean Architecture (Domain-Driven Design)

* Adopted **Domain-Driven Design (DDD)** instead of Google's recommended architecture.
* **Domain layer** is **independent** and doesn’t depend on data or UI.
* Clear separation between:

  * **UI** (Compose, state management)
  * **Domain** (UseCases, Models)
  * **Data** (Repositories, JSON parsing)

### Presentation Design Patterns:

* **MVVM**: with a single `UiState` class for reactive state management
* **Repository Pattern**: abstracts data source from domain logic

### Structure
<img width="187" alt="Screenshot 2025-06-02 at 8 39 35 PM" src="https://github.com/user-attachments/assets/266259e3-5e1d-4043-a33f-8cf33497b20c" />

---
🔍 Search Algorithm
To meet the requirement of filtering cities by prefix in better than linear time, several approaches were evaluated and benchmarked.

⚖️ Comparison of Search Strategies
| Approach                          | Time Complexity | Memory Usage | Maintains Order | Supports Prefix Search | Notes                                                    |
| --------------------------------- | --------------- | ------------ | --------------- | ---------------------- | -------------------------------------------------------- |
| **Binary Search (Sorted List)** ✅ | `O(log n)`      | Low          | ✅               | ✅                      | Chosen approach; most balanced                           |
| Unsorted `HashMap`                | `O(1)` (lookup) | Medium       | ❌               | ❌ (not prefix-capable) | Fast lookup but not suitable for prefix filtering        |
| Tree-backed `SortedMap`           | `O(log n)`      | High         | ✅               | ✅                      | Valid but uses more memory and unnecessary for this case |

✅ Chosen Approach: Binary Search on Sorted List
Cities are pre-sorted alphabetically at load time.

A binary search is used to locate the start index of the matching prefix.

The search then collects matching results in the range.

Optimized for:

Speed (O(log n))

Memory usage

Correct ordering (alphabetical display)

🔍 This algorithm satisfies the constraint of sub-linear filtering time without introducing unnecessary memory overhead.
---

## 🧪 Testing

* ViewModel and UseCase unit tests included
* Uses coroutine test utilities for predictable execution

---

## 🎨 UI / UX Design

* Based on the [provided prototype](https://mockitt.com/proto/xQ2D8Qi3suszki68EKdfSS/sharing?view_mode=read_)
* **City cell includes**:

  * Country flag
  * City and country code
  * Coordinates
* **Sticky headers**:

  * Display first letter group
  * Animate with scroll and maintain visual line
* **Search Bar**:

  * Animated transitions on focus/unfocus
  * Smooth UI state changes (loading, empty, results)
* **Performance**:

  * `LazyColumn` used for memory-efficient list rendering
  * `key` parameter optimizes recomposition using city ID

---

## 📈 Optimization Techniques

* **Kotlin Collections**: Efficient filtering & transformation
* **Memory Optimization**:

  * Lazy loading with `LazyColumn`
  * No in-memory duplication
* **Performance Optimizations**:

  * State hoisting and minimal recompositions
  * Efficient keying of composables
* **Work in Progress**:

  * ProGuard configuration
  * Baseline profiler integration

---

## 📱 Compatibility

* Minimum SDK: Android 5.0 (API 21+)
* Uses only permitted 3rd party libraries:

  * JSON: Gson
  * DI: Hilt
  * Jetpack libraries only

---

## 🚀 Getting Started

### Clone the project

```bash
git clone https://github.com/Ahmedshafie161/Klivvr.git
```

### Open in Android Studio

* Use latest stable version
* Make sure Gradle sync completes

---

## 🗂 Version Control

* Git history retained with `.git` folder
* Meaningful commits to show progress evolution

---

## ✅ Assessment Checklist

| Requirement                             | Status |
| --------------------------------------- | ------ |
| Fast prefix filtering                   | ✅      |
| Case-insensitive search                 | ✅      |
| Not linear complexity                   | ✅      |
| Alphabetically sorted                   | ✅      |
| Scrollable list with sticky headers     | ✅      |
| Google Maps integration                 | ✅      |
| Search bar animations                   | ✅      |
| State animations (loading, empty, etc.) | ✅      |
| Kotlin + Jetpack Compose                | ✅      |
| MVVM + Clean Architecture               | ✅      |
| Unit Testing                            | ✅      |
| Git history present                     | ✅      |
| Android 5+ support                      | ✅      |

---

## 🙌 Author Notes

This project was designed with maintainability, scalability, and user experience in mind. Feel free to explore the code, review comments, and check benchmarks in the search logic implementation.

For any questions or clarifications, feel free to reach out!
