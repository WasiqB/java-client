package io.appium.java_client.appium.element.generation.ios;

import io.appium.java_client.appium.element.generation.BaseElementGenerationTest;
import io.appium.java_client.ios.BaseIOSTest;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.appium.java_client.MobileBy.AccessibilityId;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.partialLinkText;

public class IOSElementGenerationTest extends BaseElementGenerationTest {
    private static final File testApp = new File(new File("src/test/java/io/appium/java_client"),
        "TestApp.app.zip");
    private static final File webViewApp = new File(new File("src/test/java/io/appium/java_client"),
        "vodqa.zip");
    private final Supplier<DesiredCapabilities> serverBrowserCapabilitiesSupplier = () -> {
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, BaseIOSTest.PLATFORM_VERSION);
        serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, BaseIOSTest.DEVICE_NAME);
        //sometimes environment has performance problems
        serverCapabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        return serverCapabilities;
    };
    private final Supplier<Capabilities> clientBrowserCapabilitiesSupplier = () -> {
        DesiredCapabilities clientCapabilities = new DesiredCapabilities();
        clientCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        return clientCapabilities;
    };
    private final Supplier<DesiredCapabilities> commonAppCapabilitiesSupplier = () -> {
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, BaseIOSTest.DEVICE_NAME);
        serverCapabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT,
            500000); //some environment is too slow
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, BaseIOSTest.PLATFORM_VERSION);
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        return serverCapabilities;
    };
    private final Function<File, Supplier<Capabilities>> appFileSupplierFunction = file -> {
        final DesiredCapabilities clientCapabilities = new DesiredCapabilities();
        return () -> {
            clientCapabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
            return clientCapabilities;
        };
    };

    @Test
    public void whenIOSNativeAppIsLaunched() {
        assertTrue(check(() -> {
            Capabilities caps = this.commonAppCapabilitiesSupplier.get();
            return caps.merge(this.appFileSupplierFunction.apply(testApp).get());
        }, this.commonPredicate,
            AccessibilityId("Answer"),
            IOSElement.class));
    }

    @Ignore
    @Test
    public void whenIOSHybridAppIsLaunched() {
        assertTrue(check(() -> {
            Capabilities caps = this.commonAppCapabilitiesSupplier.get();
            return caps.merge(this.appFileSupplierFunction.apply(webViewApp).get());
        }, (by, aClass) -> {
                new WebDriverWait(this.driver, ofSeconds(30))
                    .until(ExpectedConditions.presenceOfElementLocated(id("login")))
                    .click();
                this.driver.findElementByAccessibilityId("webView").click();
                new WebDriverWait(this.driver, ofSeconds(30))
                    .until(ExpectedConditions
                        .presenceOfElementLocated(AccessibilityId("Webview")));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.driver.getContextHandles().forEach((handle) -> {
                    if (handle.contains("WEBVIEW")) {
                        this.driver.context(handle);
                    }
                });
                return this.commonPredicate.test(by, aClass);
            }, partialLinkText("login"), IOSElement.class));
    }

    @Test
    public void whenIOSBrowserIsLaunched() {
        assertTrue(check(() -> {
            Capabilities caps = this.serverBrowserCapabilitiesSupplier.get();
            return caps.merge(this.clientBrowserCapabilitiesSupplier.get());
        }, (by, aClass) -> {
                this.driver.get("https://www.google.com");
                return this.commonPredicate.test(by, aClass);
            }, name("q"), IOSElement.class));
    }

    @Test
    public void whenIOSNativeAppIsLaunched2() {
        assertTrue(check(() -> {
            DesiredCapabilities serverCapabilities = this.commonAppCapabilitiesSupplier.get();
            serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, BaseIOSTest.PLATFORM_VERSION);
            serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            return serverCapabilities.merge(this.appFileSupplierFunction.apply(testApp).get());
        }, this.commonPredicate, id("IntegerA"), IOSElement.class));
    }

    @Test
    public void whenIOSBrowserIsLaunched2() {
        assertTrue(check(() -> {
            DesiredCapabilities serverCapabilities = this.serverBrowserCapabilitiesSupplier.get();
            serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, BaseIOSTest.PLATFORM_VERSION);
            serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            return serverCapabilities.merge(this.clientBrowserCapabilitiesSupplier.get());
        }, (by, aClass) -> {
                this.driver.get("https://www.google.com");
                return this.commonPredicate.test(by, aClass);
            }, name("q"), IOSElement.class));
    }
}
