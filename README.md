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

## Requirements

- **Android Studio** with Kotlin support
- **Minimum SDK Version**: 21 (Lollipop)


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