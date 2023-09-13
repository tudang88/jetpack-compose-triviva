# Triviva Compose

## 1. Introduction
This is the simple quiz game developed by compose.
The main target when I created this app was practice how to build multi-screen app with compose.
There are some new concepts will be covered by making this app as following.
* How to make screen without using Fragment
* How to navigate between screen
* How to use ViewModel

## 2. Target UIs

## 3. Importance Note
* Dependencies for Navigation
  Just a little caveat is some versions would not be compatible with the Gradle version, which could lead to build errors.
```groovy
    implementation "androidx.navigation:navigation-compose:2.4.0-alpha04"
```