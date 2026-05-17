package com.luizfit.plan3meses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.luizfit.plan3meses.ui.Plano3MesesApp
import com.luizfit.plan3meses.ui.theme.PlanoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanoTheme {
                Plano3MesesApp()
            }
        }
    }
}
