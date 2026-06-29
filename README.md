# SkillForge

SkillForge is an Android learning application built with Kotlin and
Jetpack Compose. It fetches course data from a REST API using Retrofit
and displays categories, courses, lessons, and a video player interface.

## Features

-   Jetpack Compose UI
-   MVVM Architecture
-   Retrofit + Coroutines
-   Coil for image loading
-   Loading & Error states
-   Navigation between screens
-   Unit Tests

## Tech Stack

-   Kotlin
-   Jetpack Compose
-   Retrofit
-   Coroutines
-   Koin
-   Coil

## AI Usage

### AI Tools Used

-   ChatGPT
-   Gemini

### Example Prompts
what casue the api call always returning unknow
what best whay to use nested lazy row and column



### What AI Got Right

AI helped generate reusable Compose UI components, suggested
architecture improvements, and explained coroutine usage, which sped up
development.

### What AI Got Wrong
nothing

I corrected the implementation by ensuring all ExoPlayer operations were
executed on the Main dispatcher while keeping database and network work
on the IO dispatcher.

## Project Structure

-   ui/
-   data/
-   domain/
-   navigation/
-   di/

## How to Run

1.  Clone the repository.
2.  Open in Android Studio.
3.  Sync Gradle.
4.  Run the app or build using:

``` bash
./gradlew assembleDebug
```
