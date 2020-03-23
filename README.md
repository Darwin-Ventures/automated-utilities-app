# Automated Utilities App

Automated Utilities is an Android application that... 

## APP Features.


## Screenshots
<img src="https://github.com/Darwin-Ventures/automated-utilities-app/blob/develop/screenshots/start_view.png" width="300" > |
<img src="https://github.com/Darwin-Ventures/automated-utilities-app/blob/develop/screenshots/register_view.png" width="300" > |
<img src="https://github.com/Darwin-Ventures/automated-utilities-app/blob/develop/screenshots/login_view.png" width="300" >|

## Getting Started and Installation

1. Clone this repository onto your local machine.
`git clone https://github.com/Darwin-Ventures/automated-utilities-app`

2. Locate the project on your machine. 

3. In Android Studio, under the file menu select open, then select an existing project.

4. Build the project.
`./gradlew build`

5. Select an emulator and run the application.

6. In addition, you can run the application using an Android device.

### Prerequisites

1. [Set up Android Studio](https://developer.android.com/studio/install) 

2. [Enable Kotlin in Android Studio](https://medium.com/@elye.project/setup-kotlin-for-android-studio-1bffdf1362e8)

3. [Run application on emulator](https://developer.android.com/studio/run/emulator)

4. [Run application on android device](https://developer.android.com/studio/run/device)


## Running the tests

1. In order to run tests from the terminal, run the following commands
`./gradlew test`

2. In order to be able to run tests using Android Studio itself, navigate to either androidTest or test folder and select a file then right click the file and select run test

NB: In order to be able to run instrumentation tests, you must have either a device or an emulator running.

### Break down into the end to end tests

androidTest folder holds instrumentation tests and these test com.example.automatedutilitiesapp.di.components/com.example.automatedutilitiesapp.views specific to Android (UI)

test folder holds the unit tests that test the logic and algorithms being used within the application. (Backend)

### Coding Styles/ Conventions
- Google Java Style
- Kotlin Style
- XML Naming Conventions

## Architecture
* MVVM

## Built With/ Powered by
* [Kotlin](https://kotlinlang.org/) - Programming language
* [Android](https://www.android.com/) - Operating System
* [Firebase](https://firebase.google.com/) - Real time Storage/Authentication
* [Glide](https://github.com/bumptech/glide) - Image processing
* [Koin](https://github.com/InsertKoinIO/koin) - Dependency Injection.

## Versioning
1.0 

## Authors
[@Karol](https://github.com/Inkafoo)

[@Ajay](https://github.com/Darwin-Ventures)

[@Akshitverma4](https://github.com/akshitverma4)
