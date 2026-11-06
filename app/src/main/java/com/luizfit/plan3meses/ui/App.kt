package com.luizfit.plan3meses.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.luizfit.plan3meses.R
import com.luizfit.plan3meses.data.ChecklistTask
import com.luizfit.plan3meses.data.MealDay
import com.luizfit.plan3meses.data.PlanContent
import com.luizfit.plan3meses.data.ProgressState
import com.luizfit.plan3meses.data.Recipe
import com.luizfit.plan3meses.data.dailyTaskId
import com.luizfit.plan3meses.data.Workout
import com.luizfit.plan3meses.state.PlanRepository
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

// ----------------------------------------------------------------------------
// Navigation
// ----------------------------------------------------------------------------

private data class AppDestination(
    val route: String,
    val label: String,
    val outlined: ImageVector,
    val filled: ImageVector
)

private val destinations = listOf(
    AppDestination("dashboard", "Today", Icons.Outlined.Home, Icons.Rounded.Home),
    AppDestination("workout", "Workout", Icons.Outlined.FitnessCenter, Icons.Rounded.FitnessCenter),
    AppDestination("meals", "Meals", Icons.Outlined.Restaurant, Icons.Rounded.Restaurant),
    AppDestination("shopping", "Shopping", Icons.Outlined.ShoppingCart, Icons.Rounded.ShoppingCart),
    AppDestination("progress", "Progress", Icons.Outlined.TrendingUp, Icons.Rounded.TrendingUp)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Plano3MesesApp() {
    val context = LocalContext.current
    val repository = remember { PlanRepository(context.applicationContext) }
    val progress by repository.progress.collectAsStateWithLifecycle(initialValue = ProgressState())
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "welcome"
    val showChrome = currentRoute != "welcome"
    val goTo: (String) -> Unit = { route -> navController.goTo(route) }

    val onToggle: (String, Boolean) -> Unit = { id, checked ->
        scope.launch { repository.setCompleted(id, checked) }
    }

    val currentDestination = destinations.firstOrNull { it.route == currentRoute }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (showChrome) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            currentDestination?.label ?: "Meal Prep Workout",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        },
        bottomBar = {
            if (showChrome) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    tonalElevation = 0.dp
                ) {
                    destinations.forEach { destination ->
                        val selected = currentRoute == destination.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = { goTo(destination.route) },
                            icon = {
                                Icon(
                                    imageVector = if (selected) destination.filled else destination.outlined,
                                    contentDescription = destination.label
                                )
                            },
                            label = {
                                Text(
                                    destination.label,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("welcome") {
                WelcomeScreen(progress = progress, goTo = goTo)
            }
            composable("dashboard") {
                DashboardScreen(progress = progress, onToggle = onToggle, goTo = goTo)
            }
            composable("workout") {
                WorkoutScreen(progress = progress, onToggle = onToggle)
            }
            composable("meals") {
                MealsScreen(progress = progress, onToggle = onToggle)
            }
            composable("shopping") {
                ShoppingScreen(progress = progress, onToggle = onToggle)
            }
            composable("progress") {
                ProgressScreen(
                    progress = progress,
                    onToggle = onToggle,
                    onWeightChange = { value -> scope.launch { repository.setWeeklyWeight(value) } },
                    onAbdomenChange = { value -> scope.launch { repository.setWeeklyAbdomen(value) } }
                )
            }
        }
    }
}

// ----------------------------------------------------------------------------
// Welcome
// ----------------------------------------------------------------------------

@Composable
private fun WelcomeScreen(
    progress: ProgressState,
    goTo: (String) -> Unit
) {
    val today = LocalDate.now().dayOfWeek
    val dailyIds = PlanContent.dashboardTasks.map { dailyTaskId(it.id, LocalDate.now()) }
    val dailyCompleted = progress.completionCountFor(dailyIds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logoapp),
                contentDescription = "Meal Prep Workout logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(180.dp)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                "Meal Prep Workout",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "Workouts, meals, drinks and progress in one place.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProgressRing(
                    completed = dailyCompleted,
                    total = dailyIds.size,
                    size = 72.dp,
                    stroke = 8.dp,
                    trackColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.18f),
                    progressColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(today.englishName(), style = MaterialTheme.typography.labelLarge)
                    Text("Today's plan", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "$dailyCompleted of ${dailyIds.size} tasks",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { goTo("dashboard") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Open plan", style = MaterialTheme.typography.titleMedium)
        }
        Text(
            "Build a consistent routine over 12 weeks",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

// ----------------------------------------------------------------------------
// Dashboard ("Today")
// ----------------------------------------------------------------------------

@Composable
private fun DashboardScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit,
    goTo: (String) -> Unit
) {
    val today = LocalDate.now().dayOfWeek
    val workout = PlanContent.workoutFor(today)
    val drink = PlanContent.drinkFor(today)
    val dailyIds = PlanContent.dashboardTasks.map { dailyTaskId(it.id, LocalDate.now()) }
    val dailyCompleted = progress.completionCountFor(dailyIds)

    ScreenColumn {
        item {
            HeroGreeting(
                dayName = today.englishName(),
                completed = dailyCompleted,
                total = dailyIds.size
            )
        }
        item {
            TodayWorkoutCard(
                workout = workout,
                fallbackText = todayWorkoutOrRest(today),
                onOpen = { goTo("workout") }
            )
        }
        item {
            QuickStatsRow(progress)
        }
        item {
            SectionCard(
                title = "Today's checklist",
                subtitle = "Check off each completed task",
                icon = Icons.Outlined.Bolt
            ) {
                PlanContent.dashboardTasks.forEach { template ->
                    val task = template.copy(id = dailyTaskId(template.id, LocalDate.now()))
                    CheckTaskRow(task, progress.completedIds.contains(task.id), onToggle)
                }
            }
        }
        item {
            DrinkSuggestionCard(drink = drink, onOpen = { goTo("meals") })
        }
        item {
            SectionCard(
                title = "Sleep",
                subtitle = "Make time to rest and recover",
                icon = Icons.Outlined.Bedtime
            ) {
                BulletLine("22:00", "Get ready for bed.")
                BulletLine("22:10", "Lights out.")
                BulletLine("05:55", "Wake up.")
            }
        }
        item {
            SectionCard(
                title = "Supplements",
                subtitle = "Notes from the plan",
                icon = Icons.Outlined.Bolt
            ) {
                BulletLine("Creatine", "The sample plan schedules creatine with breakfast or after a workout.")
                BulletLine("Whey", "The sample plan lists whey as an optional snack or after a workout.")
                BulletLine("Note", "Creatine pode subir peso por agua no musculo; isso nao e gordura.")
            }
        }
    }
}

// ----------------------------------------------------------------------------
// Workout
// ----------------------------------------------------------------------------

@Composable
private fun WorkoutScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val today = LocalDate.now().dayOfWeek
    val initialIndex = PlanContent.workouts.indexOfFirst { it.dayOfWeek == today }.coerceAtLeast(0)
    var selectedIndex by rememberSaveable { mutableStateOf(initialIndex) }
    val selected = PlanContent.workouts[selectedIndex]

    ScreenColumn {
        item {
            SectionHeader(
                title = "Workout",
                subtitle = "Tuesday through Saturday at 18:15"
            )
        }
        item {
            DayChipRow(
                selectedIndex = selectedIndex,
                onSelect = { selectedIndex = it }
            )
        }
        item {
            WorkoutDetailCard(
                workout = selected,
                checked = progress.completedIds.contains(selected.id),
                onToggle = onToggle
            )
        }
        item {
            SectionCard(
                title = "Exercise references",
                subtitle = "Practice form before adding weight",
                icon = Icons.Outlined.FitnessCenter
            ) {
                LinkRow("MuscleWiki", "https://musclewiki.com/", uriHandler::openUri)
                LinkRow("Workout.lol", "https://workout.lol/", uriHandler::openUri)
            }
        }
    }
}

@Composable
private fun DayChipRow(
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlanContent.workouts.forEachIndexed { index, workout ->
            FilterChip(
                selected = selectedIndex == index,
                onClick = { onSelect(index) },
                label = {
                    Text(
                        workout.dayOfWeek.shortLabel(),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WorkoutDetailCard(
    workout: Workout,
    checked: Boolean,
    onToggle: (String, Boolean) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        )
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(workout.title, style = MaterialTheme.typography.titleLarge)
                    Text(
                        workout.focus,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        workout.totalTime,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.FitnessCenter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            FilledTonalButton(
                onClick = { onToggle(workout.id, !checked) },
                modifier = Modifier.fillMaxWidth(),
                colors = if (checked) {
                    ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    ButtonDefaults.filledTonalButtonColors()
                }
            ) {
                Text(if (checked) "Workout complete" else "Mark complete")
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            workout.sections.forEach { section ->
                BulletLine(section.label, "${section.title}: ${section.detail}")
            }
        }
    }
}

// ----------------------------------------------------------------------------
// Meals (with Refeicoes / Bebidas tabs)
// ----------------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MealsScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    var tab by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Meals", "Drinks")
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxSize()) {
        PrimaryTabRow(
            selectedTabIndex = tab,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { index, label ->
                Tab(
                    selected = tab == index,
                    onClick = { tab = index },
                    text = { Text(label, style = MaterialTheme.typography.titleSmall) }
                )
            }
        }

        when (tab) {
            0 -> ScreenColumn {
                item {
                    SectionHeader(
                        title = "Meals",
                        subtitle = "Pasta salads, chicken and pizza options"
                    )
                }
                item {
                    SectionCard(
                        title = "Adapted from Budget Bytes",
                        subtitle = "Recipe ideas and portions from the sample plan",
                        icon = Icons.Outlined.Restaurant
                    ) {
                        LinkRow("Budget Bytes", "https://www.budgetbytes.com/", uriHandler::openUri)
                        LinkRow("Greek chicken pasta salad", "https://www.budgetbytes.com/greek-chicken-pasta-salad/", uriHandler::openUri)
                        LinkRow("Pizza chicken", "https://www.budgetbytes.com/baked-pizza-chicken/", uriHandler::openUri)
                        BulletLine("Pasta portion", "70–90 g dry pasta per main meal, or 180–240 g cooked.")
                        BulletLine("Pizza", "Weekly options include pizza chicken, tortilla pizza, or two slices of pan pizza with salad.")
                    }
                }
                items(PlanContent.meals) { mealDay ->
                    MealDayCard(mealDay)
                }
            }
            1 -> ScreenColumn {
                item {
                    SectionHeader(
                        title = "Drinks",
                        subtitle = "Drink ideas for the week"
                    )
                }
                items(PlanContent.drinks) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        checked = progress.completedIds.contains(recipe.id),
                        onToggle = onToggle
                    )
                }
            }
        }
    }
}

@Composable
private fun MealDayCard(mealDay: MealDay) {
    SectionCard(
        title = "${mealDay.day} - ${mealDay.title}",
        icon = Icons.Outlined.Restaurant
    ) {
        mealDay.meals.forEach { meal ->
            BulletLine(meal.time, "${meal.title}: ${meal.detail}")
            if (meal.macros.isNotBlank()) {
                Text(
                    meal.macros,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 15.dp, top = 2.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun RecipeCard(
    recipe: Recipe,
    checked: Boolean,
    onToggle: (String, Boolean) -> Unit
) {
    SectionCard(
        title = recipe.title,
        subtitle = recipe.timing,
        icon = Icons.Outlined.LocalDrink
    ) {
        FilledTonalButton(
            onClick = { onToggle(recipe.id, !checked) },
            modifier = Modifier.fillMaxWidth(),
            colors = if (checked) {
                ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                ButtonDefaults.filledTonalButtonColors()
            }
        ) {
            Text(if (checked) "Prepared" else "Mark prepared")
        }
        BulletLine("Ingredients", recipe.ingredients)
        recipe.steps.forEachIndexed { index, step ->
            BulletLine("${index + 1}", step)
        }
        BulletLine("Note", recipe.note)
    }
}

// ----------------------------------------------------------------------------
// Shopping (expandable categories + global progress)
// ----------------------------------------------------------------------------

@Composable
private fun ShoppingScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    val allItems = remember { PlanContent.shopping.flatMap { it.items } }
    val done = allItems.count { progress.completedIds.contains(it.id) }

    ScreenColumn {
        item {
            SectionHeader(
                title = "Shopping",
                subtitle = "Expand a category and check off purchases"
            )
        }
        item {
            ProgressBarCard(
                title = "$done of ${allItems.size} items",
                subtitle = "Weekly shopping list",
                fraction = if (allItems.isEmpty()) 0f else done.toFloat() / allItems.size
            )
        }
        items(PlanContent.shopping) { category ->
            ExpandableCategoryCard(
                title = category.title,
                items = category.items,
                progress = progress,
                onToggle = onToggle
            )
        }
    }
}

@Composable
private fun ExpandableCategoryCard(
    title: String,
    items: List<ChecklistTask>,
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val done = items.count { progress.completedIds.contains(it.id) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, style = MaterialTheme.typography.titleMedium)
                    Text(
                        "$done / ${items.size}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) "Collapse" else "Expand"
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(top = 8.dp)) {
                    items.forEach { item ->
                        CheckTaskRow(item, progress.completedIds.contains(item.id), onToggle)
                    }
                }
            }
        }
    }
}

// ----------------------------------------------------------------------------
// Progress ("Progress")
// ----------------------------------------------------------------------------

@Composable
private fun ProgressScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit,
    onWeightChange: (String) -> Unit,
    onAbdomenChange: (String) -> Unit
) {
    ScreenColumn {
        item {
            SectionHeader(
                title = "Progress",
                subtitle = "Measurements and weekly checklist"
            )
        }
        item {
            MetricGrid()
        }
        item {
            SectionCard(
                title = "Weekly measurements",
                subtitle = "Record your latest measurements",
                icon = Icons.Outlined.Straighten
            ) {
                OutlinedTextField(
                    value = progress.weeklyWeight,
                    onValueChange = onWeightChange,
                    label = { Text("Current weight") },
                    suffix = { Text("kg") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = progress.weeklyAbdomen,
                    onValueChange = onAbdomenChange,
                    label = { Text("Waist") },
                    suffix = { Text("cm") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            SectionCard(
                title = "Weekly checklist",
                subtitle = "Check off each finished task",
                icon = Icons.Outlined.TrendingUp
            ) {
                val weeklyTasks = listOf(
                    ChecklistTask("week_weight", "Weight recorded", "Once a week at the same time."),
                    ChecklistTask("week_abdomen", "Waist measured", "Place the tape at the same point each time."),
                    ChecklistTask("week_meal_prep", "Meal prep complete", "Chicken, pasta, vegetables and drinks."),
                    ChecklistTask("week_shopping", "Shopping complete", "Finish the weekly shopping list.")
                )
                weeklyTasks.forEach { task ->
                    CheckTaskRow(task, progress.completedIds.contains(task.id), onToggle)
                }
            }
        }
        item {
            SectionCard(
                title = "Weekly routine",
                subtitle = "Sample work and gym schedule",
                icon = Icons.Outlined.TrendingUp
            ) {
                PlanContent.weeklySchedule.forEach { BulletLine("Day", it) }
            }
        }
        item {
            SectionCard(
                title = "12-week review",
                subtitle = "Review your habits and measurements",
                icon = Icons.Outlined.TrendingUp
            ) {
                BulletLine("Weight", "Compare your latest measurements over time.")
                BulletLine("Waist", "Record your waist using the same method.")
                BulletLine("Abs", "Review which habits you can keep up.")
            }
        }
    }
}

// ----------------------------------------------------------------------------
// Shared components
// ----------------------------------------------------------------------------

@Composable
private fun HeroGreeting(dayName: String, completed: Int, total: Int) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ProgressRing(
                completed = completed,
                total = total,
                size = 84.dp,
                stroke = 9.dp,
                trackColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.22f),
                progressColor = MaterialTheme.colorScheme.onPrimary
            )
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Welcome back", style = MaterialTheme.typography.titleMedium)
                Text(dayName, style = MaterialTheme.typography.headlineMedium)
                Text(
                    "$completed/$total tasks today",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                )
            }
        }
    }
}

@Composable
private fun TodayWorkoutCard(
    workout: Workout?,
    fallbackText: String,
    onOpen: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        )
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.FitnessCenter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text("Today's workout", style = MaterialTheme.typography.titleMedium)
            }
            if (workout != null) {
                Text(workout.title, style = MaterialTheme.typography.headlineSmall)
                Text(
                    workout.focus,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    workout.totalTime,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text("Active recovery", style = MaterialTheme.typography.headlineSmall)
                Text(
                    fallbackText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(
                onClick = onOpen,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(if (workout != null) "View workout" else "Browse workouts")
            }
        }
    }
}

@Composable
private fun QuickStatsRow(progress: ProgressState) {
    val stats = listOf(
        Triple(progress.weeklyWeight.ifBlank { "—" }, "kg", "Weight"),
        Triple(progress.weeklyAbdomen.ifBlank { "—" }, "cm", "Waist")
    )
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        stats.forEach { (value, unit, label) ->
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            value,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (unit.isNotEmpty()) {
                            Spacer(Modifier.width(2.dp))
                            Text(
                                unit,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                        }
                    }
                    Text(
                        label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun DrinkSuggestionCard(drink: Recipe, onOpen: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.WaterDrop, contentDescription = null)
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text("Today's drink", style = MaterialTheme.typography.labelLarge)
                Text(drink.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    drink.timing,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onOpen) {
                Icon(Icons.Filled.ArrowOutward, contentDescription = "Open drinks")
            }
        }
    }
}

@Composable
private fun MetricGrid() {
    SectionCard(
        title = "Example measurements",
        subtitle = "Sample values only",
        icon = Icons.Outlined.TrendingUp
    ) {
        PlanContent.metrics.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                row.forEach { metric ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(metric.label, style = MaterialTheme.typography.labelMedium)
                            Text(metric.value, style = MaterialTheme.typography.titleLarge)
                            Text(metric.note, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
                if (row.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ProgressBarCard(title: String, subtitle: String, fraction: Float) {
    val animated by animateFloatAsState(targetValue = fraction.coerceIn(0f, 1f), animationSpec = tween(600), label = "progress")
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.headlineSmall)
            Text(subtitle, style = MaterialTheme.typography.bodySmall)
            LinearProgressIndicator(
                progress = { animated },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                trackColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.18f)
            )
        }
    }
}

@Composable
private fun ProgressRing(
    completed: Int,
    total: Int,
    size: androidx.compose.ui.unit.Dp,
    stroke: androidx.compose.ui.unit.Dp,
    trackColor: Color,
    progressColor: Color
) {
    val fraction = if (total == 0) 0f else completed.toFloat() / total
    val animated by animateFloatAsState(targetValue = fraction.coerceIn(0f, 1f), animationSpec = tween(700), label = "ring")
    val pct = (animated * 100).toInt()

    Box(modifier = Modifier.size(size), contentAlignment = Alignment.Center) {
        androidx.compose.foundation.Canvas(modifier = Modifier.size(size)) {
            val strokePx = stroke.toPx()
            val arcSize = Size(this.size.width - strokePx, this.size.height - strokePx)
            val topLeft = androidx.compose.ui.geometry.Offset(strokePx / 2, strokePx / 2)
            drawArc(
                color = trackColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = 360f * animated,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        Text(
            "$pct%",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = progressColor
        )
    }
}

@Composable
private fun ScreenColumn(content: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        content = content
    )
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.headlineSmall)
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, style = MaterialTheme.typography.titleMedium)
                    if (subtitle != null) {
                        Text(
                            subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            content()
        }
    }
}

@Composable
private fun BulletLine(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .width(3.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.55f))
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CheckTaskRow(
    task: ChecklistTask,
    checked: Boolean,
    onToggle: (String, Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (checked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                else Color.Transparent
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onToggle(task.id, it) },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(Modifier.width(4.dp))
        Column(Modifier.weight(1f)) {
            Text(task.title, style = MaterialTheme.typography.titleSmall)
            Text(
                task.detail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LinkRow(
    label: String,
    url: String,
    openUri: (String) -> Unit
) {
    FilledTonalButton(
        onClick = { openUri(url) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(label, modifier = Modifier.weight(1f))
        Icon(Icons.Filled.ArrowOutward, contentDescription = null, modifier = Modifier.size(18.dp))
    }
}

// ----------------------------------------------------------------------------
// Helpers
// ----------------------------------------------------------------------------

private fun NavHostController.goTo(route: String) {
    if (currentDestination?.route == route) return

    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo("welcome") {
            saveState = true
        }
    }
}

private fun todayWorkoutOrRest(dayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek): String {
    val workout = PlanContent.workoutFor(dayOfWeek)
    if (workout != null) return "${workout.title}: ${workout.focus}"
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Cycle for 15 minutes and walk for 20 minutes. Use the rest day to prepare for the week."
        DayOfWeek.SUNDAY -> "Active recovery, shopping, meal prep and an optional easy walk."
        else -> "Light movement and recovery."
    }
}

private fun ProgressState.completionCountFor(ids: List<String>): Int =
    ids.count { completedIds.contains(it) }

private fun DayOfWeek.englishName(): String = when (this) {
    DayOfWeek.MONDAY -> "Monday"
    DayOfWeek.TUESDAY -> "Tuesday"
    DayOfWeek.WEDNESDAY -> "Wednesday"
    DayOfWeek.THURSDAY -> "Thursday"
    DayOfWeek.FRIDAY -> "Friday"
    DayOfWeek.SATURDAY -> "Saturday"
    DayOfWeek.SUNDAY -> "Sunday"
}

private fun DayOfWeek.shortLabel(): String = when (this) {
    DayOfWeek.MONDAY -> "Mon"
    DayOfWeek.TUESDAY -> "Tue"
    DayOfWeek.WEDNESDAY -> "Wed"
    DayOfWeek.THURSDAY -> "Thu"
    DayOfWeek.FRIDAY -> "Fri"
    DayOfWeek.SATURDAY -> "Sat"
    DayOfWeek.SUNDAY -> "Sun"
}
