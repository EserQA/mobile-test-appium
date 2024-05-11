package com.app;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumServerTest {
    static AppiumDriverLocalService server;
    static AndroidDriver driver;

    static void setInstance() {
        String nodeJsPath = System.getProperty("user.home") + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        String nodeExePath = "C:\\Program Files\\nodejs\\node.exe";

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File(nodeJsPath))
                .usingDriverExecutable(new File(nodeExePath))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withIPAddress("127.0.0.1");
        server = AppiumDriverLocalService.buildService(builder);
    }

    static AppiumDriverLocalService getInstance() {
        if (server == null) {
            setInstance();
        }
        return server;
    }

    public static void startAppiumServer() {
        getInstance().start();
        System.out.println("----Starting Appium Server----");
        System.out.println("URL" + server.getUrl());
        System.out.println("Server status is " + server.isRunning());
    }

    public static void stopAppiumServer() {
        if (server != null) {
            getInstance().stop();
            System.out.println("----Stopping Appium Server----");
            System.out.println("Server status is " + server.isRunning());
        }
    }

    @Test
    public void test1() throws MalformedURLException {
        startAppiumServer();
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getSauceLabOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(AppiumBy.accessibilityId("menu item log out")).click();
        driver.findElement(By.id("android:id/button1")).click();
        String actualMessage = driver.findElement(By.id("android:id/alertTitle")).getText();
        String expectedMessage = "You are successfully logged out.";
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.findElement(By.id("android:id/button1")).click();
        driver.quit();
        stopAppiumServer();
    }

    @Test
    public void test2() throws MalformedURLException {
        startAppiumServer();
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getApiDemosOptions());
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement dogNames = driver.findElement(By.xpath("//android.widget.TextView[@text='Dog Names']"));
        longPress(dogNames);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Sample action']")).click();
        waitForSeconds(1);
        driver.quit();
        stopAppiumServer();
    }

    @Test
    public void test3() throws MalformedURLException {
        startAppiumServer();
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getWebDriverIoOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.accessibilityId("Swipe"))));
        driver.findElement(AppiumBy.accessibilityId("Swipe")).click();
        waitForSeconds(3);
        scroll("RIGHT", 0.5);
        waitForSeconds(5);
        scroll("LEFT", 0.5);
        waitForSeconds(3);
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"WebView\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.accessibilityId("Get Started"))));
        scroll("DOWN", 0.5);
        waitForSeconds(2);
        scroll("UP", 0.5);
        driver.quit();
        stopAppiumServer();
    }

    @Test
    public void test4() throws MalformedURLException {
        startAppiumServer();
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getApiDemosOptions());
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement dogNames = driver.findElement(By.xpath("//android.widget.TextView[@text='Dog Names']"));
        longPress(dogNames);
        waitForSeconds(1);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Sample action']")).click();
        waitForSeconds(1);
        driver.quit();
        stopAppiumServer();
    }
    
    public static void longPress(WebElement element) {
        Point location = element.getLocation();
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(input, 0);
        sequence.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y));
        sequence.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), location.x, location.y));
        sequence.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(sequence));
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scroll(String direction, double scrollRatio) {
        final Duration SCROLL_DURATION = Duration.ofMillis(300);
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new IllegalArgumentException("Scroll ratio must be between 0 and 1");
        }
        Dimension windowSize = driver.manage().window().getSize();
        Point midPoint = new Point((int) (windowSize.width * 0.5), (int) (windowSize.height * 0.5));
        int bottom = midPoint.y + (int) (scrollRatio * midPoint.y);
        int top = midPoint.y - (int) (scrollRatio * midPoint.y);
        int right = midPoint.x + (int) (scrollRatio * midPoint.x);
        int left = midPoint.x - (int) (scrollRatio * midPoint.x);
        if (direction.equalsIgnoreCase("UP")) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DURATION);
        } else if (direction.equals("DOWN")) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DURATION);
        } else if (direction.equals("RIGHT")) {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DURATION);
        } else if (direction.equals("LEFT")) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DURATION);
        }
    }

    public static void swipe(Point startPoint, Point endPoint, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.x, startPoint.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), endPoint.x, endPoint.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    public static void switchContext(int contextIndex) {
        String determinedContext = driver.getContextHandles().toArray()[contextIndex].toString();
        driver.context(determinedContext);
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        Point sourceElementCenter = getCenterOfElement(source);
        Point targetElementCenter = getCenterOfElement(target);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 0);
        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), targetElementCenter));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(dragAndDrop));
    }

    public static void doubleTap(WebElement element) {
        Point sourceElementCenter = getCenterOfElement(element);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence doubleTap = new Sequence(finger, 0);
        doubleTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger,Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger,Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger,Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(doubleTap));
    }

    public static Point getCenterOfElement(WebElement element) {
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        int centerX = elementLocation.getX() + (elementSize.width / 2);
        int centerY = elementLocation.getY() + (elementSize.height / 2);
        return new Point(centerX, centerY);
    }
}
