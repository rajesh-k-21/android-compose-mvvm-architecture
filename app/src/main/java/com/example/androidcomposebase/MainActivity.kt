package com.example.androidcomposebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.androidcomposebase.navigation.AppNavigation
import com.example.androidcomposebase.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for the application.
 * 
 * This activity is annotated with [@AndroidEntryPoint] to enable Hilt
 * dependency injection for this activity and its fragments/composables.
 * 
 * The activity sets up:
 * - Edge-to-edge display
 * - Theme
 * - Navigation 3 with AppNavigation
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        enableEdgeToEdge()
        
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Navigation 3 - Compose-first navigation
                    AppNavigation()
                }
            }
        }
    }
}