/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client;

import org.openqa.selenium.By;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;

import java.util.List;

@SuppressWarnings("unchecked")
public interface MobileDriver<T extends WebElement> extends WebDriver, PerformsTouchActions, ContextAware, Rotatable,
        FindsByAccessibilityId<T>, LocationContext, HidesKeyboard, HasDeviceTime,
        InteractsWithFiles, InteractsWithApps, HasAppStrings, FindsByClassName, FindsByCssSelector, FindsById,
        FindsByLinkText, FindsByName, FindsByTagName, FindsByXPath, FindsByFluentSelector<T>, ExecutesMethod,
        HasSessionDetails {

    @Override
    T findElement(By by);

    @Override
    T findElementByClassName(String className);

    @Override
    T findElementByCssSelector(String cssSelector);

    @Override
    T findElementById(String id);

    @Override
    T findElementByLinkText(String linkText);

    @Override
    T findElementByName(String name);

    @Override
    T findElementByPartialLinkText(String partialLinkText);

    @Override
    T findElementByTagName(String tagName);

    @Override
    T findElementByXPath(String xPath);

    @Override
    List<T> findElements(By by);

    @Override
    List<T> findElementsByClassName(String className);

    @Override
    List<T> findElementsByCssSelector(String cssSelector);

    @Override
    List<T> findElementsById(String id);

    @Override
    List<T> findElementsByLinkText(String linkText);

    @Override
    List<T> findElementsByName(String name);

    @Override
    List<T> findElementsByPartialLinkText(String partialLinkText);

    @Override
    List<T> findElementsByTagName(String tagName);

    @Override
    List<T> findElementsByXPath(String xPath);
}
