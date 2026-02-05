# Android Compose Base - Corporate Starter Template

A production-ready Android starter project with MVVM architecture, Jetpack Compose, and Material 3.

## 🚀 Quick Start

```bash
git clone <repository-url>
# Open in Android Studio → Run
```

## 📁 Project Structure

```
app/
├── core/                   # Core utilities
│   ├── base/               # BaseViewModel, BaseUiState/Event/Effect
│   ├── extension/          # Flow, Coroutine, Modifier extensions
│   ├── network/            # Retrofit, OkHttp, ApiResult
│   └── util/               # Resource wrapper
│
├── data/                   # Data layer
│   ├── datasource/         # Data sources (API, local)
│   ├── repository/         # Repository implementations
│   └── model/              # DTOs
│
├── domain/                 # Business logic
│   ├── model/              # Domain models
│   ├── repository/         # Repository interfaces
│   └── usecase/            # Use cases
│
├── feature/                # Feature modules
│   └── sample/             # Sample feature (reference implementation)
│
├── designsystem/           # Design System
│   ├── theme/              # Colors, Typography, Dimensions, Shapes
│   ├── button/             # Button components
│   ├── input/              # Input components
│   ├── card/               # Card components
│   └── ...                 # 20+ components
│
├── navigation/             # Navigation 3 setup
│   ├── Screen.kt           # Navigation keys
│   └── NavGraph.kt         # Navigation graph
│
└── di/                     # Hilt modules
```

---

## 🆕 How to Add a New Feature

### 1. Create Feature Package
```
feature/myfeature/
├── MyState.kt
├── MyEvent.kt
├── MyEffect.kt
├── MyViewModel.kt
└── MyScreen.kt
```

### 2. State, Event, Effect
```kotlin
// MyState.kt
data class MyState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<Item> = emptyList()
) : BaseUiState

// MyEvent.kt
sealed interface MyEvent : BaseUiEvent {
    data object LoadData : MyEvent
    data class ItemClicked(val id: String) : MyEvent
}

// MyEffect.kt
sealed interface MyEffect : BaseUiEffect {
    data class Navigate(val route: String) : MyEffect
    data class ShowMessage(val message: String) : MyEffect
}
```

### 3. ViewModel
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val useCase: MyUseCase
) : BaseViewModel<MyState, MyEvent, MyEffect>(MyState()) {
    
    override fun onEvent(event: MyEvent) {
        when (event) {
            is MyEvent.LoadData -> loadData()
            is MyEvent.ItemClicked -> sendEffect(MyEffect.Navigate(event.id))
        }
    }
    
    private fun loadData() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            when (val result = useCase()) {
                is Resource.Success -> updateState { copy(data = result.data, isLoading = false) }
                is Resource.Error -> updateState { copy(error = result.message, isLoading = false) }
                is Resource.Loading -> {}
            }
        }
    }
}
```

### 4. Screen
```kotlin
@Composable
fun MyScreen(viewModel: MyViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Handle effects
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MyEffect.Navigate -> { /* navigate */ }
                is MyEffect.ShowMessage -> { /* show snackbar */ }
            }
        }
    }
    
    // UI based on state
    StateContainer(
        state = Resource.Success(state),
        onRetry = { viewModel.onEvent(MyEvent.LoadData) }
    ) {
        // Your content
    }
}
```

### 5. Add Navigation
```kotlin
// Screen.kt
@Serializable
data object MyKey

// NavGraph.kt
is MyKey -> NavEntry(key) {
    MyScreen(onNavigateToDetail = { onNavigate(MyDetailKey(it)) })
}
```

---

## 🌐 How to Add an API

### 1. Create API Interface
```kotlin
interface MyApi {
    @GET("items")
    suspend fun getItems(): List<ItemDto>
}
```

### 2. Provide in NetworkModule
```kotlin
@Provides
@Singleton
fun provideMyApi(retrofit: Retrofit): MyApi {
    return retrofit.create(MyApi::class.java)
}
```

### 3. Create DataSource
```kotlin
class MyRemoteDataSource @Inject constructor(
    private val api: MyApi
) : MyDataSource {
    override suspend fun getItems() = safeApiCall { api.getItems() }
}
```

---

## 🎨 Design System Usage

```kotlin
// Direct theme access
Text(
    text = "Hello",
    style = AppTheme.typography.headlineMedium,
    color = AppTheme.colors.textPrimary
)

// Components
PrimaryButton(text = "Save", onClick = { })
AppTextField(value = email, onValueChange = { }, label = "Email")
AppCard { /* content */ }
```

---

## 📦 Tech Stack

| Category | Library |
|----------|---------|
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM + Clean Architecture |
| Navigation | Navigation 3 |
| DI | Hilt |
| Network | Retrofit + OkHttp + Moshi |
| Async | Coroutines + StateFlow |
| Images | Coil |
