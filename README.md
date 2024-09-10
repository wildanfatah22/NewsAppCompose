
# News App

This news app is an Android project built using Kotlin, Jetpack Compose, and follows Clean Architecture with the MVVM pattern. This app consumes news data from the News API to display the latest news to users.


## Features

- Modern User Interface: Uses Jetpack Compose to provide a modern and responsive look.
- Clean Architecture: Separates app concerns into domain, data, and presentation for improved maintainability and testability.
- MVVM: Uses the MVVM architectural pattern to separate View (Compose), ViewModel, and Model (data).
- News API: Fetches news data from News API to display the latest news from various categories and countries.
- Search Feature: Allows users to search for news by keyword.
- Save News: Adds a feature to save favorite news.


## Tech Stack

- Programming Language: Kotlin
- Framework: Jetpack Compose
- Architecture: Clean Architecture, MVVM
- Library:
      - Retrofit: For making HTTP requests to the News API
      - Coroutines: For performing asynchronous operations
      - Room: For storing news data locally (optional)
      - Hilt: For dependency injection
Tools: Android Studio


## Screenshots
<img src="https://github.com/user-attachments/assets/25d770c3-9750-4233-89df-37563f6a157a" width="200">
<img src="https://github.com/user-attachments/assets/3bfc820c-91b4-46f6-bd28-57033d8b442b" width="200">
<img src="https://github.com/user-attachments/assets/36000493-38bd-4769-852b-1ae52ccac049" width="200">
<img src="https://github.com/user-attachments/assets/5e08426a-a8cf-4dca-ac22-fc7438c208f6" width="200">
<img src="https://github.com/user-attachments/assets/837cb1b3-df4c-4b98-ad68-6ca32461505e" width="200">


## Installation

1. Clone the repository

```bash
  git clone https://github.com/wildanfatah22/NewsAppCompose.git
```
2. Open the project in Android Studio: Open the project you just cloned in Android Studio.
3. Sync Gradle: Wait for Android Studio to finish syncing Gradle.
4. Configure News API:
    - Create an account on the News API (https://newsapi.org/) and get an API key.
    - Replace the API key in the build.gradle file (app module) with your API key.


## How to Get a News API Key
1. Visit the News API website (https://newsapi.org/).
2. Sign up or log in to your account.
3. Create a new project.
4. You will be given a unique API key. Save it carefully.


