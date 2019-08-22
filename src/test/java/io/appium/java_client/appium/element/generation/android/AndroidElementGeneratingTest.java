package io.appium.java_client.appium.element.generation.android;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.appium.element.generation.BaseElementGenerationTest;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.function.Supplier;

import static io.appium.java_client.MobileBy.AndroidUIAutomator;
import static io.appium.java_client.MobileBy.name;
import static io.appium.java_client.MobileBy.tagName;
import static org.junit.Assert.assertTrue;

public class AndroidElementGeneratingTest extends BaseElementGenerationTest {
    private final File app = new File(new File("src/test/java/io/appium/java_client"),
        "ApiDemos-debug.apk");
    private final Supplier<DesiredCapabilities> commonCapabilitiesSupplier = () -> {
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        serverCapabilities.setCapability(MobileCapabilityType.APP, this.app.getAbsolutePath());
        return serverCapabilities;
    };

    @Test
    public void whenAndroidNativeAppIsLaunched() {
        assertTrue(check(() -> {
            DesiredCapabilities clientCapabilities = this.commonCapabilitiesSupplier.get();
            clientCapabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
            clientCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            return clientCapabilities;
        }, this.commonPredicate, AndroidUIAutomator("new UiSelector().clickable(true)"),
            AndroidElement.class));
    }

    @Test
    public void whenAndroidHybridAppIsLaunched() {
        assertTrue(check(() -> {
            DesiredCapabilities clientCapabilities = this.commonCapabilitiesSupplier.get();
            clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
            clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".view.WebView1");
            return clientCapabilities;
        }, (by, aClass) -> {
                this.driver.context("WEBVIEW_io.appium.android.apis");
                return this.commonPredicate.test(by, aClass);
            }, tagName("a"), AndroidElement.class));
    }

    @Test
    public void whenAndroidBrowserIsLaunched() {
        assertTrue(check(() -> {
            DesiredCapabilities clientCapabilities = this.commonCapabilitiesSupplier.get();
            clientCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            clientCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.BROWSER);
            clientCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            return clientCapabilities;
        }, (by, aClass) -> {
                this.driver.get("https://www.google.com");
                return this.commonPredicate.test(by, aClass);
            }, name("q"), AndroidElement.class));
    }
}