package com.luizfit.plan3meses.data

import java.time.DayOfWeek

object PlanContent {
    val metrics = listOf(
        Metric("Peso atual", "112.4 kg", "Antes: 115 kg"),
        Metric("Altura", "187 cm", "Corrigido"),
        Metric("Gordura", "29.3%", "Massa gorda aprox. 32.9 kg"),
        Metric("Abdomen", "107 cm", "Medir 1x/semana"),
        Metric("BMR", "2188", "kcal/dia em repouso"),
        Metric("Meta 12 semanas", "-6 a -10 kg", "Foco em consistencia")
    )

    val dashboardTasks = listOf(
        ChecklistTask("daily_water", "Agua do dia", "Levar 1 L para o trabalho e beber ao longo do dia."),
        ChecklistTask("daily_workout", "Treino ou caminhada", "Cumprir o movimento planeado para hoje."),
        ChecklistTask("daily_sleep", "Sono 22:10", "Entrar na cama as 22:00, luzes apagadas as 22:10."),
        ChecklistTask("daily_protein", "Proteina", "Bater 165-180 g/dia usando comida e whey se precisar.")
    )

    val weeklySchedule = listOf(
        "Segunda: 15 min bicicleta + 20 min caminhada. Sem ginasio. Preparar a semana.",
        "Terca: 10 min bicicleta leve + 10-15 min caminhada. Ginasio Push + Abs as 18:15.",
        "Quarta: 10 min bicicleta leve + 10-15 min caminhada. Ginasio Pull + Bike as 18:15.",
        "Quinta: 10 min bicicleta leve + 10-15 min caminhada. Ginasio Legs + Core as 18:15.",
        "Sexta: 10 min bicicleta leve + 10-15 min caminhada. Ginasio Upper + Arms as 18:15.",
        "Sabado: 10 min bicicleta leve + 10 min caminhada. Ginasio Full Body Today as 18:15.",
        "Domingo: descanso ativo, prep, compras, bebidas e caminhada leve opcional."
    )

    val sleepPlan = listOf(
        "21:30 - banho, comida de amanha e mochila do ginasio.",
        "21:45 - sem ecra forte e sem cafeina.",
        "22:00 - cama.",
        "22:10 - dormir.",
        "05:55 - acordar."
    )

    val meals = listOf(
        MealDay(
            day = "Segunda",
            title = "Greek chicken pasta salad",
            meals = listOf(
                MealItem("7:10", "Aveia proteica fria", "60 g aveia + 200 g iogurte grego + maca + canela. Creatina aqui.", "40-55 g proteina"),
                MealItem("13:00", "Salada de massa tipo Greek chicken", "80 g massa seca cozida + 170 g peito de frango + pepino + tomate + espinafres + menta + limao.", "~620 kcal"),
                MealItem("19:15", "Peru em tomate + massa pequena", "180-200 g peru + molho tomate caseiro + legumes + 60 g massa seca cozida.")
            )
        ),
        MealDay(
            day = "Terca",
            title = "Atum e grao",
            meals = listOf(
                MealItem("7:10", "Ovos + pao + tomate", "3 ovos + 2 fatias pao + tomate/espinafres."),
                MealItem("13:00", "Salada de massa, atum e grao", "75 g massa seca cozida + atum + 100 g grao + pepino + cebola roxa + limao + salsa/menta.", "~45 g proteina"),
                MealItem("20:00", "Frango tomate-paprika", "200 g frango freezer + legumes + 60-70 g massa seca cozida.")
            )
        ),
        MealDay(
            day = "Quarta",
            title = "Frango mediterranico",
            meals = listOf(
                MealItem("7:10", "Batido verde + ovos", "Batido detox verde + 2 ovos cozidos + 1 fatia pao."),
                MealItem("13:00", "Salada mediterranica com frango", "80 g massa seca cozida + 170 g frango limao-alho + tomate + pepino + folhas verdes."),
                MealItem("20:00", "Sopa de lentilhas + sardinhas", "Sopa grossa + 1 lata sardinhas escorrida + salada.")
            )
        ),
        MealDay(
            day = "Quinta",
            title = "Peru e tomate",
            meals = listOf(
                MealItem("7:10", "Iogurte + aveia", "250 g iogurte grego + 50 g aveia + banana pequena + canela."),
                MealItem("13:00", "Salada de massa com peru", "75 g massa seca cozida + 180 g peru + molho tomate + pepino + espinafres."),
                MealItem("20:00", "Frango soy-gengibre", "200 g frango + brocolo/pimentos/courgette + 60-70 g massa seca cozida.")
            )
        ),
        MealDay(
            day = "Sexta",
            title = "Pizza chicken controlada",
            meals = listOf(
                MealItem("7:10", "Batido verde + aveia proteica", "Batido verde + 50 g aveia com iogurte ou whey."),
                MealItem("13:00", "Salada de massa com sardinha", "70 g massa seca cozida + sardinhas escorridas + tomate + pepino + cebola + limao."),
                MealItem("20:00", "Pizza chicken + salada", "Peito de frango com molho tomate/pizza, queijo controlado e oregano. Salada grande ao lado.")
            )
        ),
        MealDay(
            day = "Sabado",
            title = "Frango e grao",
            meals = listOf(
                MealItem("7:10", "Ovos + pao + fruta", "3 ovos + 1-2 fatias pao + 1 fruta."),
                MealItem("13:00", "Salada de massa com frango e grao", "80 g massa seca cozida + 150 g frango + 80 g grao + pepino + tomate + menta."),
                MealItem("20:00", "Frango limao-alho", "200 g frango + legumes + 60-70 g massa seca cozida ou batata.")
            )
        ),
        MealDay(
            day = "Domingo",
            title = "Reset e prep",
            meals = listOf(
                MealItem("Manha", "Batido verde + proteina", "Batido detox verde e depois ovos, iogurte ou frango."),
                MealItem("Almoco", "Pizza planeada ou prato T", "Tortilla pizza ou 2 fatias pan pizza + salada + frango/atum."),
                MealItem("Tarde", "Prep", "Cozinhar frango, massa, legumes, caixas e bebidas.")
            )
        )
    )

    val drinks = listOf(
        Recipe(
            id = "drink_cucumber_mint",
            title = "Agua com pepino e menta",
            timing = "O dia todo - prepara na noite anterior - 0 kcal",
            ingredients = "1/2 pepino fatiado fino - 10-12 folhas menta - 1 L agua fria - 1/2 limao opcional",
            steps = listOf(
                "Corta o pepino fino. Nao e preciso descascar.",
                "Esmaga as folhas de menta nas palmas das maos.",
                "Coloca pepino e menta numa garrafa de 1 L.",
                "Enche de agua fria e fecha.",
                "Frigorifico durante a noite, minimo 4 horas."
            ),
            note = "Substitui sumos e facilita beber agua."
        ),
        Recipe(
            id = "drink_lime_mint",
            title = "Agua lima + menta",
            timing = "Alternar nos dias de ginasio - 0 kcal",
            ingredients = "1/2 lima fatiada - 10 folhas menta - 1 L agua fria - gelo opcional",
            steps = listOf(
                "Fatia a lima fina.",
                "Esmaga a menta nas palmas das maos.",
                "Coloca tudo numa garrafa de 1 L com agua fria.",
                "Frigorifico por 2-4 horas ou durante a noite."
            ),
            note = "Boa para terca, quinta e sabado. Usa menos lima se a acidez incomodar."
        ),
        Recipe(
            id = "drink_strawberry_lime_mint",
            title = "Agua morango + lima + menta",
            timing = "Opcao mais doce sem acucar - 0 kcal",
            ingredients = "5-6 morangos fatiados - 1/2 lima fatiada - 8-10 folhas menta - 1 L agua fria",
            steps = listOf(
                "Lava e fatia os morangos.",
                "Fatia a lima fina.",
                "Esmaga a menta nas palmas das maos.",
                "Junta tudo numa garrafa de 1 L.",
                "Frigorifico 2-4 horas antes de beber."
            ),
            note = "Usa ao domingo ou quando quiseres variedade com sabor mais fresco/doce."
        ),
        Recipe(
            id = "drink_green_detox",
            title = "Batido detox verde",
            timing = "Quarta, sexta e domingo - cerca de 80 kcal",
            ingredients = "Espinafres - 1/2 pepino - maca verde - sumo 1/2 limao - gengibre - 250 ml agua",
            steps = listOf(
                "Coloca primeiro a agua no liquidificador.",
                "Junta espinafres, pepino, maca e gengibre.",
                "Bate 60-90 segundos.",
                "Adiciona limao e bate mais 10 segundos."
            ),
            note = "Nao substitui proteina no pequeno-almoco."
        ),
        Recipe(
            id = "drink_acv",
            title = "Shot ACV diluido",
            timing = "15-30 min antes do almoco",
            ingredients = "1 col sopa vinagre de maca - 200 ml agua - canela opcional",
            steps = listOf(
                "Coloca primeiro a agua no copo.",
                "Adiciona o vinagre de maca.",
                "Mexe com canela se quiseres.",
                "Bebe com palhinha e passa a boca com agua."
            ),
            note = "Nunca bebas vinagre puro."
        )
    )

    val workouts = listOf(
        Workout(
            id = "workout_tue",
            dayOfWeek = DayOfWeek.TUESDAY,
            title = "Gym Workout - Terca",
            focus = "Push + Abs - peito, ombros, triceps",
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
            title = "Gym Workout - Quarta",
            focus = "Pull + Bike - costas, biceps, posterior",
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
            title = "Gym Workout - Quinta",
            focus = "Legs + Core - pernas, gluteos, posterior",
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
            title = "Gym Workout - Sexta",
            focus = "Upper + Arms - ombros, peito/costas leve, bracos",
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
            title = "Gym Workout - Sabado",
            focus = "Full Body Today - formato completo",
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
        ShoppingCategory("Proteinas", listOf(
            task("shop_chicken", "Peito de frango ou coxas sem pele", "2 kg para congelar"),
            task("shop_turkey", "Peru em cubos ou picado", "1 kg"),
            task("shop_eggs", "Ovos classe L", "2 duzias"),
            task("shop_yogurt", "Iogurte grego natural/0%", "2 potes grandes ou packs"),
            task("shop_tuna", "Atum", "6-8 latas"),
            task("shop_sardines", "Sardinhas", "4 latas")
        )),
        ShoppingCategory("Massa e hidratos", listOf(
            task("shop_pasta", "Massa integral fusilli/penne", "1.5 kg"),
            task("shop_oats", "Aveia em flocos", "1 kg"),
            task("shop_bread", "Pao integral", "Para pequeno-almoco e pre-treino"),
            task("shop_wraps", "Wraps/tortilhas", "Para tortilla pizza"),
            task("shop_chickpeas", "Grao-de-bico", "3 latas")
        )),
        ShoppingCategory("Legumes e saladas", listOf(
            task("shop_cucumber", "Pepino", "5-6 unidades"),
            task("shop_mint", "Menta/hortela fresca", "2 ramos"),
            task("shop_spinach", "Espinafres", "Saco grande"),
            task("shop_tomato", "Tomate", "Cereja ou normal"),
            task("shop_broccoli", "Brocolo", "Para jantar pos-treino")
        )),
        ShoppingCategory("Fruta e bebidas", listOf(
            task("shop_lemons", "Limoes", "12"),
            task("shop_limes", "Limas", "4"),
            task("shop_strawberries", "Morangos", "Para agua morango+lima+menta"),
            task("shop_ginger", "Gengibre fresco", "Raiz grande"),
            task("shop_apples", "Macas", "Verdes/vermelhas"),
            task("shop_bananas", "Bananas", "Pre-treino")
        )),
        ShoppingCategory("Molhos e suplementos", listOf(
            task("shop_acv", "Vinagre de maca cru nao filtrado", "Com a mae"),
            task("shop_pizza_sauce", "Molho tomate/pizza", "Pizza chicken e tortilla pizza"),
            task("shop_mozzarella", "Mozzarella/queijo ralado", "Porcao controlada"),
            task("shop_whey", "Whey", "Neutra ou baunilha"),
            task("shop_creatine", "Creatina monohidratada", "5 g por dia")
        )),
        ShoppingCategory("Meal prep tools", listOf(
            task("shop_bottles", "Garrafas 1 L", "Pepino/menta e lima/menta"),
            task("shop_boxes", "Caixas meal prep", "Para pasta salads"),
            task("shop_freezer_bags", "Sacos/caixas de congelacao", "Para frango"),
            task("shop_straws", "Palhinhas", "Para ACV")
        ))
    )

    val usefulLinks = listOf(
        "Budget Bytes" to "https://www.budgetbytes.com/",
        "MuscleWiki" to "https://musclewiki.com/",
        "Workout.lol" to "https://workout.lol/",
        "CDC sono" to "https://www.cdc.gov/sleep/about/index.html",
        "ISSN creatina" to "https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0173-z",
        "ISSN proteina" to "https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0177-8"
    )

    fun workoutFor(dayOfWeek: DayOfWeek): Workout? = workouts.firstOrNull { it.dayOfWeek == dayOfWeek }

    fun drinkFor(dayOfWeek: DayOfWeek): Recipe = when (dayOfWeek) {
        DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY -> drinks.first { it.id == "drink_lime_mint" }
        DayOfWeek.SUNDAY -> drinks.first { it.id == "drink_strawberry_lime_mint" }
        else -> drinks.first { it.id == "drink_cucumber_mint" }
    }

    private fun task(id: String, title: String, detail: String) = ChecklistTask(id, title, detail)
}
