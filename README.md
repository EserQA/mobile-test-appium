## Appium Quick Start Guide
```bash
Start Appium Server
appium
-----------------------------------------------------
List Emulator Devices
adb devices
-----------------------------------------------------
Get App Package and Activity
adb shell dumpsys window windows | grep mFocusedApp
-----------------------------------------------------
Get Build Version
adb shell dumpsys window windows | grep mFocusedApp
-----------------------------------------------------
Verify System Configuration
appium-doctor --android
