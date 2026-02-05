package com.example.androidcomposebase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with [@HiltAndroidApp] to enable Hilt dependency injection.
 * 
 * This class serves as the entry point for Hilt and triggers the code generation
 * for the base classes needed for dependency injection.
 * 
 * Make sure to register this class in AndroidManifest.xml:
 * ```xml
 * <application
 *     android:name=".App"
 *     ... />
 * ```
 */
@HiltAndroidApp
class App : Application()
