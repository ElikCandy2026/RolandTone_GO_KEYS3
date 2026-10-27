# AGENTS.md

This repository is a minimal **Android Gradle** project.  The key module is `:app` and the Gradle wrapper is provided.

## Running the App

After installing the debug APK, launch the main activity via ADB:

```bash
adb shell am start -n com.elikcandy.rolandtone/.MainActivity
```

You can also view logs:

```bash
adb logcat -s RolandTone
```

## Quick Build & Run

```bash
# Build the debug APK
./gradlew :app:assembleDebug

# Install on a connected device or emulator
./gradlew :app:installDebug

# Clean the build outputs
./gradlew clean
```

* The APK is located at `app/build/outputs/apk/debug/app-debug.apk`.
* If you prefer to install manually:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Android Studio / IDE

* Open the project root in Android Studio (the wrapper scripts are present).  The project will automatically sync.
* The main entry point is `com.elikcandy.rolandtone.MainActivity`.

## Lint & Static Checks

```bash
# Lint the debug build
./gradlew lintDebug
```

## Testing

There are **no unit or instrumentation tests** in this repo, so `./gradlew test` or `./gradlew connectedDebugAndroidTest` will not run anything.

## Project Configuration

| Item | Value |
|------|-------|
| Gradle Wrapper | `gradle-8.2-bin.zip` (see `gradle.properties`) |
| Android Gradle Plugin | `9.4.0` (root `build.gradle`) |
| Compile SDK | 35 |
| Target SDK | 35 |
| Min SDK | 26 |
| Java Version | 17 |
| Dependencies | androidx.appcompat, material, constraintlayout, lifecycle, navigation, foundation (for MIDI support) |

## `local.properties`

If you encounter errors about the Android SDK location, ensure `local.properties` contains the correct `sdk.dir`.  The file may be empty in this repo; you can generate it automatically with Android Studio or set it manually:

```
sdk.dir=/path/to/your/sdk
```

## Common Gotchas

* The project uses **Java 17**; ensure your JDK is 17 or newer.
* The Gradle wrapper (`./gradlew`) must be used to avoid Gradle version mismatch.
* No tests or lint rules are defined beyond the defaults.

## Useful Commands

| Command | Purpose |
|---------|---------|
| `./gradlew tasks` | List all Gradle tasks for the project |
| `./gradlew dependencies` | View dependency graph |
| `./gradlew app:dependencies` | View module‑specific dependencies |
| `./gradlew app:clean` | Clean app build outputs |

## Summary

- Build: `./gradlew :app:assembleDebug`
- Install: `./gradlew :app:installDebug`
- Lint: `./gradlew lintDebug`
- No tests present.

Happy coding!
