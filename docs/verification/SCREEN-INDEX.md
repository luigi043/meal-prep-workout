# Meal Prep Workout Android verification

The debug APK built with Gradle 9.4.1, AGP 9.1.1 and Android SDK platform 36. The Gradle unit test passed with zero failures. A standalone Kotlin/JUnit run also passed the same test, which verifies that completion applies only to the recorded calendar date and task.

The APK installed on an Android API 35 emulator (1080 × 2340). The screenshots below are direct emulator captures. The app has no account, camera, Bluetooth or location workflow. Workout and recipe reference links open an external browser.

| Native capture | How to reach it | Observed result |
| --- | --- | --- |
| [01 Welcome](01-welcome.png) | Launch the app | Welcome rendered and **Open plan** opened Today on the warmed retry. |
| [02 Today](02-today.png) | Open the plan or select **Today** | Daily checklist and workout displayed. Measurements were blank on the fresh install. |
| [03 Workout](03-workout.png) | Select **Workout** | The Friday card and day picker displayed. |
| [04 Meals](04-meals.png) | Select **Meals** | The meal schedule and recipe references displayed. |
| [05 Drinks](05-drinks.png) | Select **Meals**, then **Drinks** | A recipe, preparation steps and the preparation action displayed. |
| [06 Shopping](06-shopping.png) | Select **Shopping** | The grocery list showed 31 items across its categories. |
| [07 Progress](07-progress.png) | Select **Progress** | The measurement inputs and weekly checklist displayed. |
| [08 Initial timeout](08-startup-anr.png) | Android displayed this dialog on the initial launch | Android logged a five-second input-dispatch timeout while delivering focus. Earlier emulator logs also show system app timeouts. The cause is unconfirmed. |
| [09 Daily task complete](09-daily-task-complete.png) | Check **Daily water**, navigate away and return | The check remained selected and the ring reported 25%. |
| [10 Workout complete](10-workout-complete.png) | Select **Mark complete** for Friday | The selected card changed to **Workout complete**. |
| [11 Shopping expanded](11-shopping-expanded.png) | Expand **Protein** | Six item checkboxes appeared. |
| [12 Grocery item checked](12-shopping-item-checked.png) | Check the chicken item | The total changed to 1 of 31 and Protein changed to 1 of 6. |
| [13 Drink prepared](13-drink-prepared.png) | Select **Mark prepared** | The recipe changed to **Prepared**. The shortcut from Today also opened Drinks with the preparation state retained. |
| [14 Measurement input before the fix](14-measurements-before-fix.png) | Type the synthetic values 80.5 and 85 quickly in the earlier build | The fields recorded 0.58 and 58. The subsequent change keeps the field draft synchronous while DataStore persists edits. |
| [15 Timeout after rebuild](15-timeout-after-rebuild.png) | Launch the APK containing the input fix | The APK installed and rendered Welcome. The recurring timeout blocked the rapid input and relaunch retest. |

On the retry after compiling only this app's installed bytecode for speed, **Open plan** reached Today once the welcome screen had finished drawing. The first-launch timeout remains an open verification issue. The on-device clock was kept unchanged. Daily rollover was covered by the JVM test.

The standalone browser demo is a separate simulation with synthetic data. See [public demo verification](PUBLIC-DEMO.md).

The rebuilt APK from commit `4a08df6` passed `gradlew.bat --offline assembleDebug testDebugUnitTest` and installed successfully. The seven-view walkthrough used the preceding build. Repeat rapid entry of 80.5 and 85 on a responsive Android environment and confirm the values on Today and after relaunch.

The local copy is `meal-prep-workout-debug.apk` in this folder. Its SHA-256 is `8a89efcf813677725f93b4336ada78f5b4b17d25414008bc26b686fe95fe1392`.