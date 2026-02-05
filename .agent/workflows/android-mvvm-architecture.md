---
description: Android Jetpack Compose MVVM Architecture with Navigation 3, Hilt DI, and Clean Architecture
---

# Android MVVM Architecture Setup

This workflow describes how to set up a scalable Android app architecture using Jetpack Compose, MVVM, Hilt, Clean Architecture, and Navigation 3.

## Project Structure

```
app/src/main/java/com/example/androidcomposebase/
├── App.kt                           # @HiltAndroidApp
├── MainActivity.kt                  # @AndroidEntryPoint + Navigation 3
├── core/
│   ├── base/
│   │   ├── BaseViewModel.kt         # Generic ViewModel with StateFlow
│   │   ├── BaseUiState.kt           # Base state interface
│   │   ├── BaseUiEvent.kt           # Base event interface
│   │   └── BaseUiEffect.kt          # Base effect interface
│   └── util/
│       └── Resource.kt              # Success/Error/Loading wrapper
├── di/
│   ├── RepositoryModule.kt          # Repository bindings
│   ├── UseCaseModule.kt             # UseCase provisions
│   └── DataSourceModule.kt          # DataSource provisions
├── domain/
│   ├── model/                       # Domain entities
│   ├── repository/                  # Repository interfaces
│   └── usecase/                     # Use cases
├── data/
│   ├── repository/                  # Repository implementations
│   └── source/                      # Data sources
├── feature/
│   └── [feature_name]/
│       ├── [Feature]Contract.kt     # State, Event, Effect
│       ├── [Feature]ViewModel.kt    # Feature ViewModel
│       ├── [Feature]Screen.kt       # Compose screen
│       └── components/              # Reusable components
├── navigation/
│   ├── Screen.kt                    # Navigation keys (@Serializable)
│   └── NavGraph.kt                  # NavDisplay + entryProvider
└── ui/theme/                        # Theme files
```

## Dependencies (libs.versions.toml)

```toml
[versions]
hilt = "2.51.1"
navigation3 = "1.0.0"
coroutines = "1.8.1"
ksp = "2.0.21-1.0.25"
kotlinxSerializationJson = "1.7.3"

[libraries]
# Hilt
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-android-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }

# Navigation 3
navigation3-runtime = { group = "androidx.navigation3", name = "navigation3-runtime", version.ref = "navigation3" }
navigation3-ui = { group = "androidx.navigation3", name = "navigation3-ui", version.ref = "navigation3" }

# Kotlinx Serialization
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }

[plugins]
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
```

## BaseViewModel Pattern

```kotlin
abstract class BaseViewModel<State : BaseUiState, Event : BaseUiEvent, Effect : BaseUiEffect>(
    initialState: State
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()
    
    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    protected val currentState: State get() = _uiState.value

    protected fun updateState(reducer: State.() -> State) {
        _uiState.update { it.reducer() }
    }

    protected fun sendEffect(effectValue: Effect) {
        viewModelScope.launch { _effect.send(effectValue) }
    }

    abstract fun onEvent(event: Event)
}
```

## Navigation 3 Pattern

### Navigation Keys (Screen.kt)
```kotlin
@Serializable
data object UserListKey

@Serializable
data class UserDetailKey(val userId: Int)
```

### NavDisplay Setup (NavGraph.kt)
```kotlin
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Any>(UserListKey) }
    
    val onNavigate: (Any) -> Unit = { key -> backStack.add(key) }
    val onNavigateBack: () -> Unit = { 
        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) 
    }
    
    val saveableStateDecorator = rememberSaveableStateHolderNavEntryDecorator<Any>()
    
    NavDisplay<Any>(
        backStack = backStack.toList(),
        entryDecorators = listOf(saveableStateDecorator),
        onBack = onNavigateBack,
        entryProvider = { key ->
            when (key) {
                is UserListKey -> NavEntry(key) { UserListScreen(...) }
                is UserDetailKey -> NavEntry(key) { UserDetailScreen(key.userId, ...) }
                else -> NavEntry(key) { Text("Unknown") }
            }
        },
        modifier = modifier
    )
}
```

## Compose Screen Pattern

```kotlin
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel(),
    onNavigate: (Int) -> Unit = {}
) {
    // Collect state with lifecycle awareness
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Handle effects
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is FeatureUiEffect.Navigate -> onNavigate(effect.id)
                is FeatureUiEffect.ShowSnackbar -> snackbar.showSnackbar(effect.message)
            }
        }
    }
    
    // Stateless content
    FeatureContent(uiState = uiState, onEvent = viewModel::onEvent)
}
```

## Data Flow

1. **User Action** → Event sent to ViewModel via `onEvent()`
2. **ViewModel** → Calls UseCase
3. **UseCase** → Calls Repository
4. **Repository** → Returns `Flow<Resource<T>>`
5. **ViewModel** → Updates state via `updateState { copy(...) }`
6. **Compose** → Collects `StateFlow` via `collectAsStateWithLifecycle()`
7. **Side Effects** → Sent via `sendEffect()`, collected in `LaunchedEffect`

## Build & Run Commands

```bash
# Build the app
// turbo
./gradlew build

# Install on device/emulator
./gradlew installDebug

# Launch the app
adb shell am start -n com.example.androidcomposebase/.MainActivity
```

## Key Best Practices

- ✅ **Kotlin Only** - No Java code
- ✅ **StateFlow** - All state via StateFlow, no LiveData
- ✅ **Lifecycle-aware** - `collectAsStateWithLifecycle()`
- ✅ **Stateless Composables** - UI receives state, emits events
- ✅ **Clean Architecture** - Clear layer separation
- ✅ **DI with Hilt** - Constructor injection everywhere
- ✅ **MVI-style** - Event/Intent pattern in ViewModel
- ✅ **Navigation 3** - Type-safe, Compose-first navigation
