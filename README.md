# RolandTone

An **Android** project built with Gradle.  
The app was created with the help of AI, and is a personal AI training project.
The purpose of the application is to quickly change the sound tones on the Roland Go:Keys3 when connecting the phone via USB

## Project layout
- Root module: `:app`
- Gradle wrapper (`gradlew`, `gradlew.bat`)
- Android SDK required: **API 26–35** (min 26, target 35)
- Java 17 is used.

## Build & run
```bash
# Assemble a debug APK
./gradlew :app:assembleDebug

# Install on a connected device or emulator
./gradlew :app:installDebug
```
The APK is produced at `app/build/outputs/apk/debug/app-debug.apk`.

If you prefer to install manually:
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

After installation, launch the main activity:
```bash
adb shell am start -n com.elikcandy.rolandtone/.MainActivity
```
View logs:
```bash
adb logcat -s RolandTone
```

## Dependencies
- androidx.appcompat
- com.google.android.material
- androidx.constraintlayout
- androidx.lifecycle (runtime‑ktx, viewmodel‑ktx)
- androidx.navigation (fragment‑ktx, ui‑ktx)
- androidx.compose.foundation (included for MIDI support)

## Lint
```bash
./gradlew lintDebug
```

## Tests
There are no unit or instrumentation tests in this repo.

## Notes
- Use the Gradle wrapper (`./gradlew`).
- Ensure you have a JDK 17+ installed.
- `local.properties` must contain `sdk.dir` pointing to the Android SDK.
