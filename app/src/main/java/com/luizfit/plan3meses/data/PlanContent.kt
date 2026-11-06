package com.luizfit.plan3meses.data

import java.time.DayOfWeek

object PlanContent {
    val metrics = listOf(
        Metric("Current weight", "80 kg", "Example measurement"),
        Metric("Waist", "85 cm", "Example measurement")
    )

    val dashboardTasks = listOf(
        ChecklistTask("daily_water", "Daily water", "Bring a 1 L bottle and sip throughout the day."),
        ChecklistTask("daily_workout", "Workout or walk", "Complete today's planned activity."),
        ChecklistTask("daily_sleep", "Bedtime at 22:10", "Get ready for bed at 22:00 and turn off the lights at 22:10."),
        ChecklistTask("daily_protein", "Protein", "Sample plan target: 165–180 g daily from food and optional whey.")
    )

    val weeklySchedule = listOf(
        "Monday: Cycle for 15 minutes and walk for 20 minutes. Use the rest day to prepare for the week.",
        "Tuesday: 10 minutes of easy cycling and a 10–15 minute walk. Push and abs at 18:15.",
        "Wednesday: 10 minutes of easy cycling and a 10–15 minute walk. Pull and cycling at 18:15.",
        "Thursday: 10 minutes of easy cycling and a 10–15 minute walk. Legs and core at 18:15.",
        "Friday: 10 minutes of easy cycling and a 10–15 minute walk. Upper body and arms at 18:15.",
        "Saturday: 10 minutes of easy cycling and a 10-minute walk. Full body at 18:15.",
        "Sunday: Active recovery, meal prep, shopping, drinks and an optional easy walk."
    )

    val sleepPlan = listOf(
        "21:30: Shower and prepare tomorrow's food and gym bag.",
        "21:45: Avoid bright screens and caffeine.",
        "22:00: Get into bed.",
        "22:10: Lights out.",
        "05:55: Wake up."
    )

    val meals = listOf(
        MealDay(
            day = "Monday",
            title = "Greek chicken pasta salad",
            meals = listOf(
                MealItem("7:10", "Chilled high-protein oats", "60 g oats, 200 g Greek yogurt, apple and cinnamon. The sample schedule pairs this with creatine.", "40–55 g protein"),
                MealItem("13:00", "Greek chicken pasta salad", "80 g pasta (dry weight, then cooked), 170 g chicken breast, cucumber, tomato, spinach, mint and lemon.", "~620 kcal"),
                MealItem("19:15", "Turkey in tomato sauce with a small serving of pasta", "180–200 g turkey, homemade tomato sauce, vegetables and 60 g pasta (dry weight, then cooked).")
            )
        ),
        MealDay(
            day = "Tuesday",
            title = "Tuna and chickpeas",
            meals = listOf(
                MealItem("7:10", "Eggs, bread and tomato", "3 eggs, 2 slices of bread, and tomato or spinach."),
                MealItem("13:00", "Tuna and chickpea pasta salad", "75 g pasta (dry weight, then cooked), tuna, 100 g chickpeas, cucumber, red onion, lemon and parsley or mint.", "About 45 g protein"),
                MealItem("20:00", "Tomato and paprika chicken", "200 g prepped chicken, vegetables and 60–70 g pasta (dry weight, then cooked).")
            )
        ),
        MealDay(
            day = "Wednesday",
            title = "Mediterranean chicken",
            meals = listOf(
                MealItem("7:10", "Green smoothie and eggs", "Green smoothie, 2 boiled eggs and 1 slice of bread."),
                MealItem("13:00", "Mediterranean chicken salad", "80 g pasta (dry weight, then cooked), 170 g lemon and garlic chicken, tomato, cucumber and leafy greens."),
                MealItem("20:00", "Lentil soup and sardines", "Thick lentil soup, 1 drained can of sardines and a salad.")
            )
        ),
        MealDay(
            day = "Thursday",
            title = "Turkey and tomato",
            meals = listOf(
                MealItem("7:10", "Yogurt and oats", "250 g Greek yogurt, 50 g oats, a small banana and cinnamon."),
                MealItem("13:00", "Turkey pasta salad", "75 g pasta (dry weight, then cooked), 180 g turkey, tomato sauce, cucumber and spinach."),
                MealItem("20:00", "Soy and ginger chicken", "200 g chicken, broccoli, peppers or zucchini, and 60–70 g pasta (dry weight, then cooked).")
            )
        ),
        MealDay(
            day = "Friday",
            title = "Pizza chicken",
            meals = listOf(
                MealItem("7:10", "Green smoothie and high-protein oats", "Green smoothie and 50 g oats with yogurt or whey."),
                MealItem("13:00", "Sardine pasta salad", "70 g pasta (dry weight, then cooked), drained sardines, tomato, cucumber, onion and lemon."),
                MealItem("20:00", "Pizza chicken and salad", "Chicken breast with tomato or pizza sauce, a measured portion of cheese and oregano. Serve with a large salad.")
            )
        ),
        MealDay(
            day = "Saturday",
            title = "Chicken and chickpeas",
            meals = listOf(
                MealItem("7:10", "Eggs, bread and fruit", "3 eggs, 1–2 slices of bread and a piece of fruit."),
                MealItem("13:00", "Chicken and chickpea pasta salad", "80 g pasta (dry weight, then cooked), 150 g chicken, 80 g chickpeas, cucumber, tomato and mint."),
                MealItem("20:00", "Lemon and garlic chicken", "200 g chicken, vegetables, and 60–70 g pasta (dry weight, then cooked) or potatoes.")
            )
        ),
        MealDay(
            day = "Sunday",
            title = "Weekly reset and prep",
            meals = listOf(
                MealItem("Morning", "Green smoothie and a protein side", "Green smoothie with eggs, yogurt or chicken on the side."),
                MealItem("Lunch", "Planned pizza or a salad plate", "Tortilla pizza or two slices of pan pizza, with salad and chicken or tuna."),
                MealItem("Afternoon", "Meal prep", "Cook the chicken, pasta and vegetables. Pack the meals and prepare the drinks.")
            )
        )
    )

    val drinks = listOf(
        Recipe(
            id = "drink_cucumber_mint",
            title = "Cucumber and mint water",
            timing = "Prepare the night before and sip throughout the day",
            ingredients = "Half a cucumber, thinly sliced, 10–12 mint leaves, 1 L cold water and half a lemon (optional)",
            steps = listOf(
                "Thinly slice the cucumber. Peeling is optional.",
                "Crush the mint leaves gently between your palms.",
                "Add the cucumber and mint to a 1 L bottle.",
                "Fill with cold water and close the bottle.",
                "Refrigerate overnight, or for at least four hours."
            ),
            note = "Serve chilled as an alternative to juice."
        ),
        Recipe(
            id = "drink_lime_mint",
            title = "Lime and mint water",
            timing = "An option for workout days",
            ingredients = "Half a sliced lime, 10 mint leaves, 1 L cold water and ice (optional)",
            steps = listOf(
                "Thinly slice the lime.",
                "Crush the mint gently between your palms.",
                "Put the ingredients into a 1 L bottle and fill with cold water.",
                "Refrigerate for two to four hours or overnight."
            ),
            note = "The sample plan uses this on Tuesday, Thursday and Saturday. Use less lime for a milder flavor."
        ),
        Recipe(
            id = "drink_strawberry_lime_mint",
            title = "Strawberry, lime and mint water",
            timing = "Fruit-infused water with no added sugar",
            ingredients = "5–6 sliced strawberries, half a sliced lime, 8–10 mint leaves and 1 L cold water",
            steps = listOf(
                "Wash and slice the strawberries.",
                "Thinly slice the lime.",
                "Crush the mint gently between your palms.",
                "Combine the ingredients in a 1 L bottle.",
                "Refrigerate for two to four hours before serving."
            ),
            note = "Serve on Sunday or whenever you want a fruitier drink."
        ),
        Recipe(
            id = "drink_green_detox",
            title = "Green smoothie",
            timing = "Wednesday, Friday and Sunday (about 80 kcal)",
            ingredients = "Spinach, half a cucumber, a green apple, juice of half a lemon, ginger and 250 ml water",
            steps = listOf(
                "Pour the water into the blender first.",
                "Add the spinach, cucumber, apple and ginger.",
                "Blend for 60–90 seconds.",
                "Add lemon juice and blend for another 10 seconds."
            ),
            note = "Pair with the breakfast in the sample meal plan."
        ),
        Recipe(
            id = "drink_acv",
            title = "Diluted apple cider vinegar",
            timing = "Before lunch in the sample plan",
            ingredients = "1 tablespoon apple cider vinegar, 200 ml water and cinnamon (optional)",
            steps = listOf(
                "Pour the water into a glass first.",
                "Add the apple cider vinegar.",
                "Stir in cinnamon if desired.",
                "Use a straw and rinse your mouth with water afterward."
            ),
            note = "Always dilute the vinegar."
        )
    )

    val workouts = listOf(
        Workout(
            id = "workout_tue",
            dayOfWeek = DayOfWeek.TUESDAY,
            title = "Tuesday gym workout",
            focus = "Push and abs: chest, shoulders and triceps",
            totalTime = "~80-90 min",
            sections = listOf(
                WorkoutSection("Warm-Up - 10 min", "Treadmill", "5 min fast walk. Incline 5-8%. Speed comfortable pace."),
                WorkoutSection("Mobility - 5 min", "Shoulders + hips", "Arm circles x20, torso twists x20, bodyweight squats x15, hip rotations x10 each side."),
                WorkoutSection("1", "Goblet Squat", "4 sets x 12 reps. Rest 60 sec."),
                WorkoutSection("2", "Dumbbell Bench Press", "4 sets x 10-12 reps. Rest 60 sec."),
                WorkoutSection("3", "Incline Dumbbell Press", "3 sets x 10 reps. Rest 60 sec."),
                WorkoutSection("4", "Dumbbell Shoulder Press", "3 sets x 10 reps. Rest 60 sec."),
                WorkoutSection("5", "Dumbbell Lateral Raises", "3 sets x 15 reps. Rest 45 sec."),
                WorkoutSection("6", "Overhead Dumbbell Tricep Extension", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("Abs Circuit - 15 min", "Repeat 3 rounds", "Leg raises 15, Russian twists 20, mountain climbers 30 sec, plank 45 sec. Rest 60 sec."),
                WorkoutSection("Cardio Finish", "Bike + treadmill", "Bike 8-10 min moderate. Treadmill 8-10 min incline 8-12%, speed 5-6 km/h."),
                WorkoutSection("Total Workout Time", "~80-90 min", "Warm-up 10, weights 45, abs 15, cardio 15-20.")
            )
        ),
        Workout(
            id = "workout_wed",
            dayOfWeek = DayOfWeek.WEDNESDAY,
            title = "Wednesday gym workout",
            focus = "Pull and cycling: back, biceps and posterior chain",
            totalTime = "~75-85 min",
            sections = listOf(
                WorkoutSection("Warm-Up - 10 min", "Bike", "5 min easy/moderate + 5 min shoulder/back mobility."),
                WorkoutSection("Mobility - 5 min", "Back + hips", "Band pull-aparts x20, torso twists x20, hip hinges x15, cat-cow x8."),
                WorkoutSection("1", "One-Arm Dumbbell Row", "4 sets x 12 reps each arm. Rest 60 sec."),
                WorkoutSection("2", "Lat Pulldown or Assisted Pull-Up", "4 sets x 10-12 reps. Rest 75 sec."),
                WorkoutSection("3", "Seated Cable Row", "3 sets x 12 reps. Rest 60 sec."),
                WorkoutSection("4", "Dumbbell Romanian Deadlift", "4 sets x 12 reps. Rest 60-90 sec."),
                WorkoutSection("5", "Hammer Curls", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("6", "Dumbbell Bicep Curls", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("Core - 10 min", "Repeat 2 rounds", "Dead bug 12 each side, side plank 30 sec each side, bird dog 12 each side."),
                WorkoutSection("Cardio Finish - 20 min", "Bike", "20 min moderate pace.")
            )
        ),
        Workout(
            id = "workout_thu",
            dayOfWeek = DayOfWeek.THURSDAY,
            title = "Thursday gym workout",
            focus = "Legs and core: legs, glutes and posterior chain",
            totalTime = "~80-90 min",
            sections = listOf(
                WorkoutSection("Warm-Up - 10 min", "Bike", "5 min easy + 5 min lower-body mobility."),
                WorkoutSection("Mobility - 5 min", "Lower body", "Bodyweight squats x15, hip rotations x10 each side, lunges x8 each side, hamstring hinge x12."),
                WorkoutSection("1", "Leg Press", "4 sets x 12 reps. Rest 75-90 sec."),
                WorkoutSection("2", "Goblet Squat", "4 sets x 12 reps. Rest 60 sec."),
                WorkoutSection("3", "Dumbbell Walking Lunges", "3 sets x 10 reps each leg. Rest 75 sec."),
                WorkoutSection("4", "Dumbbell Romanian Deadlift", "4 sets x 10-12 reps. Rest 75-90 sec."),
                WorkoutSection("5", "Hip Thrust or Glute Bridge", "4 sets x 10-12 reps. Rest 60 sec."),
                WorkoutSection("6", "Calf Raises", "4 sets x 15 reps. Rest 45 sec."),
                WorkoutSection("Abs Circuit", "Repeat 3 rounds", "Reverse crunch 15, plank 45 sec, mountain climbers 30 sec."),
                WorkoutSection("Cardio Finish", "Treadmill incline walk", "10-15 min, incline 8-10%, speed 4.8-5.6 km/h.")
            )
        ),
        Workout(
            id = "workout_fri",
            dayOfWeek = DayOfWeek.FRIDAY,
            title = "Friday gym workout",
            focus = "Upper body and arms: shoulders, light chest and back work, and arms",
            totalTime = "~70-80 min",
            sections = listOf(
                WorkoutSection("Warm-Up - 10 min", "Treadmill", "5 min fast walk + 5 min shoulder mobility."),
                WorkoutSection("Mobility - 5 min", "Shoulders + trunk", "Arm circles x20, torso twists x20, light lateral raises x15, scapular squeezes x15."),
                WorkoutSection("1", "Dumbbell Shoulder Press", "4 sets x 10 reps. Rest 60 sec."),
                WorkoutSection("2", "Dumbbell Bench Press", "3 sets x 10-12 reps. Rest 60 sec."),
                WorkoutSection("3", "One-Arm Dumbbell Row", "3 sets x 12 reps each arm. Rest 60 sec."),
                WorkoutSection("4", "Dumbbell Lateral Raises", "4 sets x 15 reps. Rest 45 sec."),
                WorkoutSection("5", "Hammer Curls", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("6", "Overhead Dumbbell Tricep Extension", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("7", "Face Pull or Rear Delt Fly", "3 sets x 15 reps. Rest 45 sec."),
                WorkoutSection("Abs - 10 min", "Repeat 2 rounds", "Russian twists 20, leg raises 12, plank 45 sec."),
                WorkoutSection("Cardio Finish - 10 min", "Bike or incline walk", "Easy/moderate. Save energy for Saturday.")
            )
        ),
        Workout(
            id = "workout_sat",
            dayOfWeek = DayOfWeek.SATURDAY,
            title = "Saturday gym workout",
            focus = "Full-body session",
            totalTime = "~90 min",
            sections = listOf(
                WorkoutSection("Warm-Up - 10 min", "Treadmill", "5 min fast walk. Incline 5-8%. Speed comfortable pace."),
                WorkoutSection("Mobility - 5 min", "Full body", "Arm circles x20, torso twists x20, bodyweight squats x15, hip rotations x10 each side."),
                WorkoutSection("1", "Goblet Squat", "4 sets x 12 reps. Rest 60 sec."),
                WorkoutSection("2", "Dumbbell Bench Press", "4 sets x 10-12 reps. Rest 60 sec."),
                WorkoutSection("3", "One-Arm Dumbbell Row", "4 sets x 12 reps each arm. Rest 60 sec."),
                WorkoutSection("4", "Dumbbell Romanian Deadlift", "4 sets x 12 reps. Rest 60-90 sec."),
                WorkoutSection("5", "Dumbbell Shoulder Press", "3 sets x 10 reps. Rest 60 sec."),
                WorkoutSection("6", "Hammer Curls", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("7", "Overhead Dumbbell Tricep Extension", "3 sets x 12 reps. Rest 45 sec."),
                WorkoutSection("Abs Circuit - 15 min", "Repeat 3 rounds", "Leg raises 15, Russian twists 20, mountain climbers 30 sec, plank 45 sec. Rest 60 sec."),
                WorkoutSection("Cardio Finish - 20 min", "Bike + Treadmill Incline Walk", "Bike 10 min moderate. Treadmill 10 min, incline 8-12%, speed 5-6 km/h."),
                WorkoutSection("Optional Finisher", "Farmer Carry", "3 rounds, 30-40 sec walk with heavy dumbbells.")
            )
        )
    )

    val shopping = listOf(
        ShoppingCategory("Protein", listOf(
            task("shop_chicken", "Chicken breast or skinless thighs", "2 kg for freezing"),
            task("shop_turkey", "Diced or ground turkey", "1 kg"),
            task("shop_eggs", "Large eggs", "2 dozen"),
            task("shop_yogurt", "Plain or nonfat Greek yogurt", "2 large tubs or packs"),
            task("shop_tuna", "Tuna", "6–8 cans"),
            task("shop_sardines", "Sardines", "4 cans")
        )),
        ShoppingCategory("Pasta and grains", listOf(
            task("shop_pasta", "Whole-wheat fusilli or penne", "1.5 kg"),
            task("shop_oats", "Rolled oats", "1 kg"),
            task("shop_bread", "Whole-wheat bread", "For breakfast and pre-workout snacks"),
            task("shop_wraps", "Wraps or tortillas", "For tortilla pizza"),
            task("shop_chickpeas", "Chickpeas", "3 cans")
        )),
        ShoppingCategory("Vegetables and greens", listOf(
            task("shop_cucumber", "Cucumber", "5–6 cucumbers"),
            task("shop_mint", "Fresh mint", "2 bunches"),
            task("shop_spinach", "Spinach", "Large bag"),
            task("shop_tomato", "Tomatoes", "Cherry or regular"),
            task("shop_broccoli", "Broccoli", "For dinner after a workout")
        )),
        ShoppingCategory("Fruit and drinks", listOf(
            task("shop_lemons", "Lemons", "12"),
            task("shop_limes", "Limes", "4"),
            task("shop_strawberries", "Strawberries", "For strawberry, lime and mint water"),
            task("shop_ginger", "Fresh ginger", "Large root"),
            task("shop_apples", "Apples", "Green or red"),
            task("shop_bananas", "Bananas", "Pre-workout snack")
        )),
        ShoppingCategory("Sauces and supplements", listOf(
            task("shop_acv", "Raw unfiltered apple cider vinegar", "With the mother"),
            task("shop_pizza_sauce", "Tomato or pizza sauce", "For pizza chicken and tortilla pizza"),
            task("shop_mozzarella", "Mozzarella or grated cheese", "Use a measured portion"),
            task("shop_whey", "Whey", "Plain or vanilla"),
            task("shop_creatine", "Creatine monohydrate", "Sample plan: 5 g daily")
        )),
        ShoppingCategory("Meal prep tools", listOf(
            task("shop_bottles", "1 L bottles", "For cucumber and mint or lime and mint water"),
            task("shop_boxes", "Meal prep containers", "For pasta salads"),
            task("shop_freezer_bags", "Freezer bags or containers", "For chicken"),
            task("shop_straws", "Straws", "For diluted apple cider vinegar")
        ))
    )

    val usefulLinks = listOf(
        "Budget Bytes" to "https://www.budgetbytes.com/",
        "MuscleWiki" to "https://musclewiki.com/",
        "Workout.lol" to "https://workout.lol/",
        "CDC sleep" to "https://www.cdc.gov/sleep/about/index.html",
        "ISSN creatine" to "https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0173-z",
        "ISSN protein" to "https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0177-8"
    )

    fun workoutFor(dayOfWeek: DayOfWeek): Workout? = workouts.firstOrNull { it.dayOfWeek == dayOfWeek }

    fun drinkFor(dayOfWeek: DayOfWeek): Recipe = when (dayOfWeek) {
        DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY -> drinks.first { it.id == "drink_lime_mint" }
        DayOfWeek.SUNDAY -> drinks.first { it.id == "drink_strawberry_lime_mint" }
        else -> drinks.first { it.id == "drink_cucumber_mint" }
    }

    private fun task(id: String, title: String, detail: String) = ChecklistTask(id, title, detail)
}
