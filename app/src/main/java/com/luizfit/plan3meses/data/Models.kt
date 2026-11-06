package com.luizfit.plan3meses.data

import java.time.DayOfWeek

data class Metric(
    val label: String,
    val value: String,
    val note: String
)

data class ChecklistTask(
    val id: String,
    val title: String,
    val detail: String
)

data class MealDay(
    val day: String,
    val title: String,
    val meals: List<MealItem>
)

data class MealItem(
    val time: String,
    val title: String,
    val detail: String,
    val macros: String = ""
)

data class Recipe(
    val id: String,
    val title: String,
    val timing: String,
    val ingredients: String,
    val steps: List<String>,
    val note: String
)

data class Workout(
    val id: String,
    val dayOfWeek: DayOfWeek,
    val title: String,
    val focus: String,
    val totalTime: String,
    val sections: List<WorkoutSection>
)

data class WorkoutSection(
    val label: String,
    val title: String,
    val detail: String
)

data class ShoppingCategory(
    val title: String,
    val items: List<ChecklistTask>
)

data class ProgressState(
    val completedIds: Set<String> = emptySet(),
    val weeklyWeight: String = "",
    val weeklyAbdomen: String = ""
)

internal fun dailyTaskId(id: String, date: java.time.LocalDate): String = "$id@$date"
