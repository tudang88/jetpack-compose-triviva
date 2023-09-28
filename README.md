# Triviva Compose

## 1. Introduction
This is the simple quiz game developed by compose.
The main target when I created this app was practice how to build multi-screen app with compose.
There are some new concepts will be covered by making this app as following.
* How to make screen without using Fragment
* How to navigate between screen
* How to use ViewModel
* Using NavigationDrawer
* Using ActionMenu or Overflow menu

## 2. Target UIs

## 3. How to use Navigation
### a. Dependency
  Just a little caveat is some versions would not be compatible with the Gradle version, which could lead to build errors.
```groovy
    implementation "androidx.navigation:navigation-compose:2.4.0-alpha04"
```
### b. Implement NavHost
* Step 1: create a navControler
  ```kotlin
  val navController = rememberNavController()
  // instead of using Navigation Graph as we usually do with xml
  NavHost(navController = navController, startDestination = "just string")
  ```
* Step 2: Define a Route Class same as Router in Flutter
  ```kotlin
  sealed class Screen(val route: String) {
    object MainScreen: Screen("MainScreen")
  }
  ```
* Step 3: Update NavHost which will define all route for each Screen
  ```kotlin
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        // define route to navigate to MainScreen
        composable(route = Screen.MainScreen.route) {

        }
         // define route to navigate to OtherScreen
        composable(route = Screen.OtherScreen.route) {
            // to get navArgs, get it inside this lambda
        }
    }

  ```
* Step 4: Create Screen UI as normal

## 4. How to create a mutable state
Any changes to value schedules recomposition of any composable functions that read value. In the case of ExpandingCard, whenever expanded changes, it causes ExpandingCard to be recomposed.

There are three ways to declare a MutableState object in a composable:
```kotlin
val mutableState = remember { mutableStateOf(default) }
var value by remember { mutableStateOf(default) }
val (value, setValue) = remember { mutableStateOf(default) }
```
These declarations are equivalent, and are provided as syntax sugar for different uses of state. You should pick the one that produces the easiest-to-read code in the composable you're writing.

The by delegate syntax requires the following imports:

## 5. How to Navigation between screen
refer original document
https://developer.android.com/jetpack/compose/navigation
* The implementation sample could be found in this project navigation package
## 6. How to use NavigationDrawer
refer to original document
https://developer.android.com/jetpack/compose/components/drawer
* The implementation sample could be found in this project menu package

## 7. The complete demo
![](https://github.com/tudang88/jetpack-compose-triviva/demo.gif)
