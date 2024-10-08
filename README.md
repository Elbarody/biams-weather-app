# biams-weather-app

## Overview

is a modern weather forecasting application built using the latest Android technologies. It provides daily and hourly weather forecasts by integrating remote data from an API

## Features

- **Daily & Hourly Forecasts:** Displays weather conditions, temperature, and forecasts for the next hours.
- **API Integration with Retrofit:** Fetches weather data from the OpenWeatherMap API.
- **Modern UI with Jetpack Compose:** Utilizes a declarative UI approach for a more dynamic and responsive design.
- **Dependency Injection with Hilt:** Provides a scalable and testable dependency injection solution.
- **Asynchronous Programming with Kotlin Coroutines:** Handles background tasks for API calls and database operations efficiently.
- **Clean Architecture:** Separates the codebase into different layers (Presentation, Domain, Data) for better maintainability.

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Networking:** Retrofit, OkHttp
- **Dependency Injection:** Hilt
- **Coroutines & Flow:** For asynchronous operations
- **Testing:** JUnit, Mockk for unit testing


## Architecture

The application follows the Model-View-ViewModel (MVVM) architecture pattern to separate concerns and ensure a clean codebase. The primary components are:

- **Models**: Data classes representing the nutritional data and responses.
- **ViewModels**: Handle the logic and interact with repositories to fetch data.
- **Repositories**: Abstract the data source and provide data to ViewModels.
- **Views**: Android activities and fragments that display the UI.

## Configuration
Add your API key to the `Constants` file or to your environment variables:
   ```
       API_KEY="your_api_key"
   ```

## Screenshots
| <img src="screenshots/1.jpg" width=200/> | <img src="screenshots/2.jpg" width=200/> | <img src="screenshots/3.jpg" width=200/> | <img src="screenshots/4.jpg" width=200/> |

