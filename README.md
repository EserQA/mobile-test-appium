***To start appium***	 
appium

***To get listed emulator devices***	 
adb devices

***To get appPackage and appActivity***
adb shell dumpsys window windows | grep mFocusedApp

***To get build version***	 
adb shell dumpsys window windows | grep mFocusedApp

***To verify the system***
appium-doctor --android