
Open Weather Android app
==================

App useful for checking the weather using open data.

## Technical features

The app is full Kotlin and Jetpack Compose.
Some of the main libraries and features the app uses are:

# Development Environment

App uses the Gradle build system and for so it can be imported directly into Android Studio (last tested version Android Studio Giraffe | 2022.3.1 Beta 1).

# Build

The app contains the usual `debug` and `release` build variants. No flavours have been implemented.

# Architecture

The app follows an `MVVM` architecture and `Version Catalog` system for handling and sharing depencendecies.

# Modularization

The app has been fully modularized and the main modules are:
- `app` which contains the application entry point, the MainActivity;
- `core` which contains all the libraries' modules for delegating reusable business logic, exposing and make the data layer reusable, defining the core navigation, defining the Jetpack Compose theme, defining some Jetpack Compose reuasable components, etc...
- `feature` which contains the main UI entry points (mainly screens).

# UI

The app follows Material Design 3 guidelines and does support light and dark mode theme.

# Testing

Unit tests have been implemented in order to test the main business logic.
