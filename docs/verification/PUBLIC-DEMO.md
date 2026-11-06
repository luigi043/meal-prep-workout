# Public portfolio demo

Open the [Meal Prep Workout demo](https://meal-prep-workout-demo.vercel.app/). The deployed HTML matches `demo/index.html` byte for byte and is available without signing in. The deployment contains only that file. The Android repository is private.

The browser demo presents five synthetic checklists in a phone-width layout. Checks stay in memory until the page reloads. The demo has no Android storage, personal measurements, accounts, network integrations, or APK.

A headless Edge session loaded the public URL at a 390 px viewport and verified all five navigation buttons, the selected state, checklist retention when switching views, unchecking, and the absence of horizontal overflow and JavaScript exceptions. A separate Node harness exercised the same checklist logic.

Vercel deployment ID: `dpl_2XfxycaRQMikMy9rbhqiqyxeYWmg`.
