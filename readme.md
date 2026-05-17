# Plano 3 Meses - Android App

Native Android app and reference plan for Luiz Carlos Morais Fehlberg's 3-month fat-loss, meal prep, hydration, supplement, sleep, and gym routine.

## Objetivo do projeto

Build a practical offline mobile app that helps Luiz follow the plan every day:

- See the current stats, daily workout, drink rotation, sleep target, and checklist.
- Follow the Monday-Saturday work schedule and Tuesday-Saturday gym plan.
- Use meal prep with pasta salads, chicken breast, controlled pizza options, and Budget Bytes-style low-cost meals.
- Track checklists, shopping items, weekly weight, and weekly abdomen measurement.
- Keep all plan content available offline, without a backend and without a WebView.

## Perfil e metas

- Name: Luiz Carlos Morais Fehlberg
- Birth date: 1998-09-20
- Height: 187 cm
- Current weight: 112.4 kg
- Body fat: 29.3%
- Abdomen: 107 cm
- BMR: 2188 kcal/day
- Work: Monday to Saturday, 8:00-17:00
- Gym: Tuesday to Saturday, around 18:15
- Sleep target: bed 22:00, lights off 22:10, wake 5:55
- Main 12-week target: lose 6-10 kg, reduce waist/abdomen, keep muscle, build a stronger V-shape base

## Best build choice

Use **Native Android with Kotlin + Jetpack Compose + Material 3**.

Why this is the best option:

- Better than WebView for daily use: faster, more native, easier to save checklist/progress state.
- Works offline because all plan content is typed Kotlin data.
- Easy to grow later with graphs, reminders, progress photos, meal filters, and exports.
- Android Studio supports this stack directly.

## Current stack

- Android Studio project
- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- DataStore Preferences
- Minimum SDK: 26
- Package: `com.luizfit.plan3meses`

Version choices are based on current official Android documentation:

- Android Gradle Plugin: `9.2.0`
- Kotlin: `2.2.20`
- Compose BOM: `2026.04.01`
- Navigation Compose: `2.9.7`
- DataStore Preferences: `1.2.1`

## Project structure

```text
.
|-- app/
|   |-- build.gradle.kts
|   `-- src/main/
|       |-- AndroidManifest.xml
|       |-- java/com/luizfit/plan3meses/
|       |   |-- MainActivity.kt
|       |   |-- data/
|       |   |   |-- Models.kt
|       |   |   `-- PlanContent.kt
|       |   |-- state/
|       |   |   `-- PlanRepository.kt
|       |   `-- ui/
|       |       |-- App.kt
|       |       `-- theme/Theme.kt
|       `-- res/
|-- plano_3meses.html
|-- readme.md
|-- settings.gradle.kts
|-- build.gradle.kts
`-- gradle.properties
```

## App screens

1. **Inicio**
   - Current stats
   - Today's workout
   - Today's drink rotation
   - Sleep target
   - Daily checklist
   - Creatine and whey guidance

2. **Semana**
   - Work schedule
   - Morning bike/walk routine
   - Gym Tuesday-Saturday
   - Sleep schedule
   - Adjustment rules

3. **Refeicoes**
   - Budget Bytes-style pasta salads
   - Chicken breast meal prep
   - Controlled pizza options
   - Portion guidance for pasta

4. **Bebidas**
   - Agua com pepino e menta
   - Agua lima + menta
   - Agua morango + lima + menta
   - Batido detox verde
   - ACV shot with safety note

5. **Treino**
   - Gym Workout - Terca
   - Gym Workout - Quarta
   - Gym Workout - Quinta
   - Gym Workout - Sexta
   - Gym Workout - Sabado

6. **Compras**
   - Proteins
   - Pasta and carbs
   - Vegetables and salads
   - Fruits and drinks
   - Sauces and supplements
   - Meal prep tools

7. **Progresso**
   - Weekly weight
   - Weekly abdomen
   - Weekly checklist

## How to build in Android Studio

1. Open Android Studio.
2. Select **Open**.
3. Choose this folder:

   ```text
   C:\Users\cluiz\OneDrive - Instituto Politecnico de Beja\Documents\meal prep & work out
   ```

4. Let Android Studio sync Gradle.
5. If Android Studio asks for setup details:
   - JDK: use Android Studio bundled JDK.
   - Gradle: use version `9.4.1` for Android Gradle Plugin `9.2.0`.
   - Android SDK: install SDK `36` and Build Tools `36.0.0`.
6. Create or start an emulator, or connect an Android phone with USB debugging enabled.
7. Press **Run**.
8. To build an APK:
   - `Build > Build Bundle(s) / APK(s) > Build APK(s)`

Command-line builds require Java/Gradle on PATH or a generated Gradle wrapper. This workspace currently has Android Studio installed, but Java/Gradle are not on the shell PATH.

## What is already implemented

- Native Android project scaffold.
- Kotlin data models.
- Seeded plan content in `PlanContent.kt`.
- Persistent checklist/progress state with DataStore.
- Compose UI with bottom navigation.
- Dashboard, week, meals, drinks, workout, shopping, and progress screens.
- Three flavored waters:
  - cucumber + mint
  - lime + mint
  - strawberry + lime + mint
- `plano_3meses.html` updated with the strawberry drink.

## Test checklist

Use this after opening the project in Android Studio:

- App builds successfully.
- App opens offline.
- Bottom navigation reaches every screen.
- Checklist items toggle.
- Checklist state persists after closing and reopening the app.
- Weekly weight and abdomen fields persist.
- Tuesday-Saturday workouts appear in the Gym Workout format.
- Drinks include cucumber mint, lime mint, and strawberry lime mint.
- Shopping list includes whey, creatine, pasta, chicken, limes, mint, and strawberries.

## Roadmap

### v1

- Offline plan app.
- Checklists and progress fields.
- Static plan content.
- Native Compose UI.

### v2

- Weekly progress charts.
- Reminder notifications for sleep, water, gym, and creatine.
- Progress photos.
- Meal filter by ingredient.
- Export progress as CSV.
- Better tablet layout.

## Sources

- Budget Bytes: https://www.budgetbytes.com/
- MuscleWiki: https://musclewiki.com/
- Workout.lol: https://workout.lol/
- CDC sleep: https://www.cdc.gov/sleep/about/index.html
- ISSN creatine: https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0173-z
- ISSN protein: https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0177-8
- Android Gradle Plugin: https://developer.android.com/build/releases/gradle-plugin
- Compose BOM: https://developer.android.com/develop/ui/compose/bom
- DataStore: https://developer.android.com/jetpack/androidx/releases/datastore
- Navigation: https://developer.android.com/jetpack/androidx/releases/navigation

## Full pro-level prompt/spec

Copy and reuse this prompt to rebuild or extend the app:

```text
Build a native Android app called "Plano 3 Meses" for Luiz Carlos Morais Fehlberg.

Context:
Luiz was born on 1998-09-20, is 187 cm tall, current weight is 112.4 kg, body fat is 29.3%, abdomen is 107 cm, BMR is 2188 kcal, and he works Monday to Saturday from 8:00 to 17:00. The goal is a 3-month fat-loss and fitness plan with high-protein meals, pasta salads, chicken meal prep, controlled pizza options, hydration routines, sleep guidance, supplements, and gym workouts from Tuesday to Saturday.

Build approach:
Use Android Studio with Kotlin, Jetpack Compose, Material 3, Navigation Compose, and DataStore Preferences. The app must work offline. Do not use a WebView. Store static plan content as typed Kotlin data models. Use DataStore only for checklist/progress state.

Core objectives:
1. Help Luiz follow the plan daily.
2. Show meals, drinks, workouts, shopping list, supplements, sleep, and weekly progress.
3. Make the app practical for daily use before and after work.
4. Keep the UI clean, mobile-first, fast, and easy to scan.

Screens:
1. Dashboard
- Show current stats: 112.4 kg, 187 cm, body fat 29.3%, abdomen 107 cm, BMR 2188.
- Show today's gym workout if Tuesday-Saturday.
- Show today's drink rotation.
- Show bedtime target: lights off 22:10, wake 5:55.
- Show checklist summary.

2. Semana
- Work schedule: Monday-Saturday 8:00-17:00.
- Morning routine:
  - Monday: 15 min bike + 20 min walk.
  - Tuesday-Saturday: 10 min light bike + 10-15 min walk.
  - Sunday: light optional walk.
- Gym:
  - Tuesday: Push + Abs.
  - Wednesday: Pull + Bike.
  - Thursday: Legs + Core.
  - Friday: Upper + Arms.
  - Saturday: Full Body Today.
- Sleep:
  - 21:30 prepare food/shower.
  - 21:45 no strong screens.
  - 22:00 bed.
  - 22:10 sleep.
  - Wake 5:55.

3. Refeicoes
- Include pasta salads inspired by Budget Bytes:
  - Greek chicken pasta salad adapted.
  - Tuna and chickpea pasta salad.
  - Chicken cucumber mint pasta salad.
  - Sardine Mediterranean pasta salad.
- Include chicken breast meal prep.
- Include controlled pizza:
  - pizza chicken with salad.
  - tortilla pizza with chicken or tuna.
  - 2 slices pan pizza + big salad as controlled option.
- Use portion guidance: 70-90 g dry pasta per main meal.

4. Bebidas
Create recipe cards for:
- Agua com pepino e menta:
  1/2 cucumber, 10-12 mint leaves, 1 L cold water, optional 1/2 lemon.
- Agua lima + menta:
  1/2 lime, 10 mint leaves, 1 L cold water.
- Agua morango + lima + menta:
  5-6 sliced strawberries, 1/2 lime, 8-10 mint leaves, 1 L cold water.
- Batido detox verde:
  spinach, cucumber, green apple, lemon, ginger, water.
- ACV shot:
  1 tbsp apple cider vinegar diluted in 200 ml water.
Add safety note: never drink vinegar undiluted.

5. Treino
Use this exact structure style for each workout:
- Gym Workout - [Day]
- Warm-Up - 10 min
- Mobility - 5 min
- Strength/Dumbbell Workout
- Abs Circuit
- Cardio Finish
- Optional Finisher when relevant
- Total Workout Time

Workout days:
Tuesday: Push + Abs.
Wednesday: Pull + Bike.
Thursday: Legs + Core.
Friday: Upper + Arms.
Saturday: Full Body Today.

6. Compras
Create grouped checklist:
- Proteins
- Pasta and carbs
- Vegetables and salads
- Fruits
- Drinks
- Sauces and condiments
- Supplements
- Meal prep tools

7. Progresso
Allow Luiz to mark:
- workout completed
- water completed
- meal prep completed
- shopping item bought
- weekly weight
- weekly abdomen measurement

Supplements:
- Creatine monohydrate: 5 g every day, preferably with breakfast or post-workout dinner.
- Whey: 1 scoop when protein is low, best at 16:00 or post-workout if dinner is delayed.

Design:
Use a clean health dashboard style. Use Portuguese labels. Keep cards compact. Avoid marketing landing page style. Prioritize daily usability.

Sources to reference in README:
- Budget Bytes: https://www.budgetbytes.com/
- MuscleWiki: https://musclewiki.com/
- Workout.lol: https://workout.lol/
- CDC sleep: https://www.cdc.gov/sleep/about/index.html
- ISSN creatine: https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0173-z
- ISSN protein: https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0177-8

Acceptance criteria:
- App builds successfully in Android Studio.
- App works offline.
- All main screens are reachable.
- Checklist state persists after app restart.
- Tuesday-Saturday gym plan is visible and readable.
- Drinks include cucumber mint, lime mint, and strawberry lime mint.
- README includes setup/build instructions and this project spec.
```
