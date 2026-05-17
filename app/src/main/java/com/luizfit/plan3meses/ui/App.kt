package com.luizfit.plan3meses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.luizfit.plan3meses.data.ChecklistTask
import com.luizfit.plan3meses.data.MealDay
import com.luizfit.plan3meses.data.PlanContent
import com.luizfit.plan3meses.data.ProgressState
import com.luizfit.plan3meses.data.Recipe
import com.luizfit.plan3meses.data.Workout
import com.luizfit.plan3meses.state.PlanRepository
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

private data class AppDestination(
    val route: String,
    val label: String,
    val short: String
)

private val destinations = listOf(
    AppDestination("dashboard", "Inicio", "I"),
    AppDestination("week", "Semana", "S"),
    AppDestination("meals", "Refeicoes", "R"),
    AppDestination("drinks", "Bebidas", "B"),
    AppDestination("workout", "Treino", "T"),
    AppDestination("shopping", "Compras", "C"),
    AppDestination("progress", "Progresso", "P")
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
    val currentRoute = backStackEntry?.destination?.route ?: destinations.first().route

    val onToggle: (String, Boolean) -> Unit = { id, checked ->
        scope.launch { repository.setCompleted(id, checked) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Plano 3 Meses", fontWeight = FontWeight.Bold)
                        Text(
                            "Luiz - treino, refeicoes, bebidas e progresso",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                destinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentRoute == destination.route,
                        onClick = {
                            if (currentRoute != destination.route) {
                                navController.navigate(destination.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(destinations.first().route) { saveState = true }
                                }
                            }
                        },
                        icon = { Text(destination.short, fontWeight = FontWeight.Bold) },
                        label = {
                            Text(
                                destination.label,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = destinations.first().route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("dashboard") {
                DashboardScreen(progress = progress, onToggle = onToggle)
            }
            composable("week") {
                WeekScreen()
            }
            composable("meals") {
                MealsScreen()
            }
            composable("drinks") {
                DrinksScreen(progress = progress, onToggle = onToggle)
            }
            composable("workout") {
                WorkoutScreen(progress = progress, onToggle = onToggle)
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

@Composable
private fun DashboardScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    val today = LocalDate.now().dayOfWeek
    val workout = PlanContent.workoutFor(today)
    val drink = PlanContent.drinkFor(today)
    val completedCount = progress.completedIds.size

    ScreenColumn {
        item {
            SectionHeader("Dashboard", "Hoje: ${today.portugueseName()}")
        }
        item {
            PlanCard("Resumo de hoje", workout?.focus ?: "Sem ginasio planeado") {
                InfoLine("Treino", workout?.title ?: "Descanso ativo, caminhada leve ou prep.")
                InfoLine("Bebida", drink.title)
                InfoLine("Sono", "Cama 22:00, dormir 22:10, acordar 5:55.")
                InfoLine("Checklist", "$completedCount itens marcados no plano.")
            }
        }
        item {
            MetricGrid()
        }
        item {
            PlanCard("Acoes do dia", "Marca o que cumpriste") {
                PlanContent.dashboardTasks.forEach { task ->
                    CheckTaskRow(task, progress.completedIds.contains(task.id), onToggle)
                }
            }
        }
        item {
            SupplementCard()
        }
    }
}

@Composable
private fun WeekScreen() {
    ScreenColumn {
        item {
            SectionHeader("Semana", "Trabalho 8:00-17:00, ginasio terca a sabado")
        }
        item {
            PlanCard("Rotina da manha", "Bike + caminhada sem destruir a recuperacao") {
                PlanContent.weeklySchedule.forEach { InfoLine("Dia", it) }
            }
        }
        item {
            PlanCard("Sono", "Prioridade para perder gordura e recuperar") {
                PlanContent.sleepPlan.forEach { InfoLine("Horario", it) }
            }
        }
        item {
            PlanCard("Regras de ajuste", "Mantem simples") {
                InfoLine("Peso parado", "Corta 150-200 kcal/dia, geralmente massa/azeite/frutos secos.")
                InfoLine("Energia baixa", "Adiciona banana pre-treino e confirma sono.")
                InfoLine("Semana pesada", "Corta cardio final antes de cortar musculacao.")
            }
        }
    }
}

@Composable
private fun MealsScreen() {
    val uriHandler = LocalUriHandler.current

    ScreenColumn {
        item {
            SectionHeader("Refeicoes", "Pasta salads, frango, pizza controlada e meal prep")
        }
        item {
            PlanCard("Budget Bytes adaptado", "Links para inspiracao, com porcoes do teu plano") {
                LinkButton("Budget Bytes", "https://www.budgetbytes.com/", uriHandler::openUri)
                LinkButton("Greek chicken pasta salad", "https://www.budgetbytes.com/greek-chicken-pasta-salad/", uriHandler::openUri)
                LinkButton("Pizza chicken", "https://www.budgetbytes.com/baked-pizza-chicken/", uriHandler::openUri)
                InfoLine("Porcao de massa", "70-90 g seca por refeicao principal, ou 180-240 g ja cozida.")
                InfoLine("Pizza", "1x/semana planeada: pizza chicken, tortilla pizza ou 2 fatias pan pizza + salada.")
            }
        }
        items(PlanContent.meals) { mealDay ->
            MealDayCard(mealDay)
        }
    }
}

@Composable
private fun DrinksScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    ScreenColumn {
        item {
            SectionHeader("Bebidas", "Hidratacao com variedade sem calorias liquidas")
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

@Composable
private fun WorkoutScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    ScreenColumn {
        item {
            SectionHeader("Treino", "Gym Workout - terca a sabado")
        }
        item {
            PlanCard("Links uteis", "Tecnica primeiro, carga depois") {
                LinkButton("MuscleWiki", "https://musclewiki.com/", uriHandler::openUri)
                LinkButton("Workout.lol", "https://workout.lol/", uriHandler::openUri)
            }
        }
        items(PlanContent.workouts) { workout ->
            WorkoutCard(
                workout = workout,
                checked = progress.completedIds.contains(workout.id),
                onToggle = onToggle
            )
        }
    }
}

@Composable
private fun ShoppingScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit
) {
    ScreenColumn {
        item {
            SectionHeader("Compras", "Toca para marcar comprado")
        }
        items(PlanContent.shopping) { category ->
            PlanCard(category.title) {
                category.items.forEach { item ->
                    CheckTaskRow(item, progress.completedIds.contains(item.id), onToggle)
                }
            }
        }
    }
}

@Composable
private fun ProgressScreen(
    progress: ProgressState,
    onToggle: (String, Boolean) -> Unit,
    onWeightChange: (String) -> Unit,
    onAbdomenChange: (String) -> Unit
) {
    ScreenColumn {
        item {
            SectionHeader("Progresso", "Peso, abdomen e check semanal")
        }
        item {
            PlanCard("Medidas semanais", "Regista 1x por semana, de manha") {
                OutlinedTextField(
                    value = progress.weeklyWeight,
                    onValueChange = onWeightChange,
                    label = { Text("Peso atual / semanal") },
                    suffix = { Text("kg") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = progress.weeklyAbdomen,
                    onValueChange = onAbdomenChange,
                    label = { Text("Abdomen semanal") },
                    suffix = { Text("cm") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            PlanCard("Checklist semanal", "Marca quando estiver feito") {
                val weeklyTasks = listOf(
                    ChecklistTask("week_weight", "Peso registado", "1x por semana, mesma hora."),
                    ChecklistTask("week_abdomen", "Abdomen medido", "Fita no mesmo ponto."),
                    ChecklistTask("week_meal_prep", "Meal prep feito", "Frango, massa, legumes e bebidas."),
                    ChecklistTask("week_shopping", "Compras feitas", "Lista completa para a semana.")
                )
                weeklyTasks.forEach { task ->
                    CheckTaskRow(task, progress.completedIds.contains(task.id), onToggle)
                }
            }
        }
        item {
            PlanCard("Alvo realista", "3 meses") {
                InfoLine("Peso", "-6 a -10 kg se a consistencia estiver boa.")
                InfoLine("Abdomen", "-6 a -10 cm e roupa mais solta.")
                InfoLine("Abs", "Definicao forte pode precisar de mais tempo, mas a base comeca agora.")
            }
        }
    }
}

@Composable
private fun MetricGrid() {
    PlanCard("Perfil atual", "Consulta e metas corrigidas") {
        PlanContent.metrics.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { metric ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(metric.label, style = MaterialTheme.typography.labelMedium)
                            Text(
                                metric.value,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(metric.note, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
                if (row.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun MealDayCard(mealDay: MealDay) {
    PlanCard("${mealDay.day} - ${mealDay.title}") {
        mealDay.meals.forEach { meal ->
            InfoLine(meal.time, "${meal.title}: ${meal.detail}")
            if (meal.macros.isNotBlank()) {
                Text(
                    meal.macros,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
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
    PlanCard(recipe.title, recipe.timing) {
        CheckTaskRow(
            task = ChecklistTask(recipe.id, "Fiz / preparei esta bebida", recipe.note),
            checked = checked,
            onToggle = onToggle
        )
        InfoLine("Ingredientes", recipe.ingredients)
        recipe.steps.forEachIndexed { index, step ->
            InfoLine("${index + 1}", step)
        }
        InfoLine("Nota", recipe.note)
    }
}

@Composable
private fun WorkoutCard(
    workout: Workout,
    checked: Boolean,
    onToggle: (String, Boolean) -> Unit
) {
    PlanCard(workout.title, "${workout.focus} - ${workout.totalTime}") {
        CheckTaskRow(
            task = ChecklistTask(workout.id, "Treino concluido", workout.focus),
            checked = checked,
            onToggle = onToggle
        )
        workout.sections.forEach { section ->
            InfoLine(section.label, "${section.title}: ${section.detail}")
        }
    }
}

@Composable
private fun SupplementCard() {
    PlanCard("Creatina e whey", "Simples e consistente") {
        InfoLine("Creatina", "5 g todos os dias. Melhor: pequeno-almoco. Nos dias de ginasio tambem pode ser pos-treino.")
        InfoLine("Whey", "1 scoop quando faltar proteina. Melhor: 16:00 ou depois do ginasio se o jantar atrasar.")
        InfoLine("Nota", "Creatina pode subir peso por agua no musculo; isso nao e gordura.")
    }
}

@Composable
private fun ScreenColumn(content: androidx.compose.foundation.lazy.LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = content
    )
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PlanCard(
    title: String,
    subtitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (subtitle != null) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            content()
        }
    }
}

@Composable
private fun InfoLine(label: String, value: String) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun CheckTaskRow(
    task: ChecklistTask,
    checked: Boolean,
    onToggle: (String, Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onToggle(task.id, it) }
        )
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(task.title, fontWeight = FontWeight.SemiBold)
            Text(
                task.detail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LinkButton(
    label: String,
    url: String,
    openUri: (String) -> Unit
) {
    Button(
        onClick = { openUri(url) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
    }
}

private fun DayOfWeek.portugueseName(): String = when (this) {
    DayOfWeek.MONDAY -> "Segunda"
    DayOfWeek.TUESDAY -> "Terca"
    DayOfWeek.WEDNESDAY -> "Quarta"
    DayOfWeek.THURSDAY -> "Quinta"
    DayOfWeek.FRIDAY -> "Sexta"
    DayOfWeek.SATURDAY -> "Sabado"
    DayOfWeek.SUNDAY -> "Domingo"
}
