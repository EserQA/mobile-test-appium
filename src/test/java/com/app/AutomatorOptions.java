package com.app;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.util.Objects;

public class AutomatorOptions {
    public UiAutomator2Options getApiDemosOptions(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("builds/ApiDemos-debug.apk")).getFile());
        String apkPath = file.getAbsolutePath();
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setPlatformVersion("8.1")
                .setAutomationName("UIAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .setApp(apkPath)
                .setNoReset(false);
        return options;
    }

    public UiAutomator2Options getSauceLabOptions(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("builds/Android-MyDemoAppRN.1.3.0.build-244.apk"))
                    .getFile());
        String apkPath = file.getAbsolutePath();
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setPlatformVersion("8.1")
                .setAutomationName("UIAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity(".MainActivity")
                .setApp(apkPath)
                .setNoReset(false);
        return options;
    }

    public UiAutomator2Options getWebDriverIoOptions(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("builds/Android-NativeDemoApp-0.1.0.apk"))
                .getFile());
        String apkPath = file.getAbsolutePath();
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setPlatformVersion("8.1")
                .setAutomationName("UIAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity(".MainActivity")
                .setApp(apkPath)
                .setNoReset(false);
        return options;
    }

}
