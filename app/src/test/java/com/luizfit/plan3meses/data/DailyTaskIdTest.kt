package com.luizfit.plan3meses.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import java.time.LocalDate

class DailyTaskIdTest {
    @Test
    fun completionAppliesOnlyToTheRecordedDayAndTask() {
        val day = LocalDate.of(2026, 12, 31)
        val previousCompletions = setOf("daily_water", dailyTaskId("daily_water", day))

        assertEquals(true, dailyTaskId("daily_water", day) in previousCompletions)
        assertFalse(dailyTaskId("daily_water", day.plusDays(1)) in previousCompletions)
        assertFalse(dailyTaskId("daily_workout", day) in previousCompletions)
    }
}
