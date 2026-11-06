# Meal Prep Workout screen verification

Use a fresh emulator install of `com.luizfit.plan3meses`. Use sample measurements such as 80 kg and 85 cm. Keep the native captures in this folder.

| Capture | How to reach the screen | Checks |
| --- | --- | --- |
| 01-welcome.png | Launch the app | The plan opens from **Open plan**. |
| 02-today.png | Open the plan or select **Today** | Toggle a daily task and verify that the completion count updates. |
| 03-workout.png | Select **Workout** | Change the workout day, mark it complete, switch days and return. |
| 04-meals.png | Select **Meals** | Scroll through all seven days. |
| 05-drinks.png | Select **Meals**, then **Drinks**, or open the drink shortcut from Today | Mark a recipe prepared and switch tabs. |
| 06-shopping.png | Select **Shopping** | Expand a category and verify that the category and overall counts reflect a checked item. |
| 07-progress.png | Select **Progress** | Enter sample measurements and mark a weekly task. Relaunch and confirm persistence. |

The app has no account, camera, Bluetooth or location workflow. Workout and recipe reference links open an external browser. Those sites are separate from the offline app.

The native captures are pending. The initial build stopped before compilation because the installed SDK did not contain platform 36. The standalone browser demo is a simulation with synthetic data.
