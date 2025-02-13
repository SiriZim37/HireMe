# HireMe - Android App

**HireMe** is an Android application that connects users with service providers for various services such as cleaning, cooking, babysitting, and more. It allows users to register, search for services, and book providers in a simple and user-friendly interface.

---

## Core Features

- **User Registration & Authentication**
    - Register/login with Email
    - Profile setup with skills, availability, and pricing (for service providers)

- **Service Listings & Hiring** (Working on)
    - Users can browse services
    - Search & filter based on location, price, or ratings
    - Book & schedule services

- **In-App Chat & Notifications** (Future Expansion)
    - Chat between customers and service providers
    - Push notifications for updates

- **Payments & Reviews** (Future Expansion)
    - Users can rate & review service providers

- **Admin Dashboard** (Future Expansion)
    - Manage user registrations, services, and complaints

---

## Tech Stack

- **Programming Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database
- **Dependency Injection**: Hilt
- **Coroutines** for asynchronous operations
- **Jetpack Compose** for UI development
- **ViewBinding** for efficient view handling
- **Unit Testing**: Included for critical components

---


## Key Technologies & Libraries

### **Kotlin**
The app is primarily written in Kotlin, taking advantage of its modern language features, such as:
- **Null safety**: Prevents null pointer exceptions by enforcing nullability constraints.
- **Extension functions**: Extends existing classes with new functionality without modifying them.
- **Concise syntax**: Makes the code more readable and maintainable.

### **Jetpack Compose**
The app uses **Jetpack Compose** for building the user interface. Compose is a declarative UI toolkit that simplifies UI design and maintenance. It makes creating complex UIs more straightforward and reduces boilerplate code. 
- **View Binding** and **Data Binding** are also used in combination with Jetpack Compose for efficient UI handling.

### **Room Database**
**Room** is used for local data storage, providing an abstraction layer over SQLite to store user and service data efficiently:
- Data is persisted locally, making it accessible offline.
- The app uses **Dao (Data Access Object)** interfaces to define methods for interacting with the database.
- **Entities** represent the app's data model.

### **Hilt**
**Hilt** simplifies **Dependency Injection (DI)** in the app. It automatically provides the necessary dependencies (such as **ServiceRepository**, **ServiceDao**, and **WorkingDaysDao**) into the **ViewModels** and other components. This reduces boilerplate code and improves code maintainability.

### **Coroutines**
**Coroutines** are used for handling **asynchronous operations** in the app, such as network requests and database queries:
- Coroutines run in the background thread, preventing UI freezes.
- The app uses **viewModelScope** and **lifecycleScope** for managing background tasks tied to the lifecycle of **ViewModels** and **Activities/Fragments**.

### **LiveData & StateFlow**
- **LiveData** is used to observe data changes in **ViewModels** and automatically update the UI when data changes.
- **StateFlow**, a part of **Kotlin Flow**, is used for advanced state management and reactive programming, offering more control over the flow of data.

### **Firebase Authentication (Future Implementation)**
**Firebase Authentication** will be integrated to handle user authentication. Users will be able to log in or sign up with their email and password securely. This will enable secure access to the app for users and service providers.

### **Jetpack Navigation**
The app uses **Jetpack Navigation Component** to manage in-app navigation. It simplifies the implementation of **fragment-based navigation** and ensures proper back stack management and smooth navigation transitions.

### **Retrofit (Future Implementation)**
**Retrofit** will be used for networking, enabling the app to make **API calls** to the backend server. This will allow users to access service listings, make bookings, process payments, and more.

## Hilt Dependency Injection

In this project, we use **Hilt** for **dependency injection** to simplify managing the application's components and their dependencies. Below is the setup and usage of **Hilt** in the app:

### 1. **Set Up Hilt in Your Project**

First, add the necessary Hilt dependencies in the **`build.gradle`** files:

```gradle
// In the project-level build.gradle
buildscript {
    dependencies {
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.40.5' // Hilt plugin
    }
}
// In the app-level build.gradle
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'  // Hilt plugin
}

dependencies {
    implementation "com.google.dagger:hilt-android:2.40.5"  // Hilt Dependency Injection
    kapt "com.google.dagger:hilt-android-compiler:2.40.5"   // Hilt compiler
}
### 2. **Create Application Class and Initialize Hilt
@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
<application
    android:name=".MyApplication"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/Theme.HireMe">
    ...
</application>

### 3. **Inject Dependencies into ViewModel
@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val serviceDao: UserServiceDao,
    private val workingDaysDao: WorkingDaysDao
) : ViewModel() {...}

###4. ***Inject ViewModel into Fragment/Activity
@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val serviceViewModel: ServiceViewModel by viewModels()
...
}

```

## Requirements

- **Android Studio** with Kotlin support
- **Minimum SDK Version**: 21 (Lollipop)