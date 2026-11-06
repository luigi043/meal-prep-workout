# Meal Prep Workout

Meal Prep Workout is an offline Android app for following a sample weekly routine. It combines a daily checklist, workout cards, a meal plan, drink recipes, a grocery checklist and space for the latest weight and waist measurements. The plan is fixed and does not personalize recommendations.

## Build and test

Open this folder in Android Studio. Configure Android SDK platform 36 and a Gradle JDK compatible with Android Gradle Plugin 9.1.1. The app targets Java 17 bytecode and runs on Android API 26 or newer. The project specifies Gradle 9.4.1 in the wrapper properties. Until the wrapper scripts and JAR are restored, use an existing Gradle 9.4.1 installation:

```powershell
gradle assembleDebug testDebugUnitTest
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Data and demo

Checklists and measurements are stored on the device with DataStore. Today's tasks are recorded by local calendar date. Measurements show the latest saved values. The app does not have an account or a synchronization service. Reference links open in the device browser.

Try five sample checklists in the [public portfolio demo](https://meal-prep-workout-demo.vercel.app/) or open the [standalone HTML](demo/index.html) locally. It uses synthetic data and keeps checkmarks in memory until reload. The [native screen checklist](docs/verification/SCREEN-INDEX.md) describes the Android verification steps.
