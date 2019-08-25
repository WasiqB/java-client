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

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.FileDetector;

import java.util.List;

@SuppressWarnings({"unchecked"})
public abstract class MobileElement<T extends MobileElement<T>>
        extends DefaultGenericMobileElement<T> {

    protected FileDetector fileDetector;

    @Override
    public List<T> findElements(By by) {
        return super.findElements(by);
    }

    @Override
    public List<T> findElements(String by, String using) {
        return super.findElements(by, using);
    }

    @Override
    public List<T> findElementsByAccessibilityId(String using) {
        return super.findElementsByAccessibilityId(using);
    }

    @Override
    public List<T> findElementsByClassName(String using) {
        return super.findElementsByClassName(using);
    }

    @Override
    public List<T> findElementsByCssSelector(String using) {
        return super.findElementsByCssSelector(using);
    }

    @Override
    public List<T> findElementsById(String id) {
        return super.findElementsById(id);
    }

    @Override
    public List<T> findElementsByLinkText(String using) {
        return super.findElementsByLinkText(using);
    }

    @Override
    public List<T> findElementsByName(String using) {
        return super.findElementsByName(using);
    }

    @Override
    public List<T> findElementsByPartialLinkText(String using) {
        return super.findElementsByPartialLinkText(using);
    }

    @Override
    public List<T> findElementsByTagName(String using) {
        return super.findElementsByTagName(using);
    }

    @Override
    public List<T> findElementsByXPath(String using) {
        return super.findElementsByXPath(using);
    }

    /**
     * Method returns central coordinates of an element.
     *
     * @return The instance of the {@link org.openqa.selenium.Point}
     */
    public Point getCenter() {
        Point upperLeft = this.getLocation();
        Dimension dimensions = this.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth() / 2,
                upperLeft.getY() + dimensions.getHeight() / 2);
    }

    /**
     * This method sets the new value of the attribute "value".
     *
     * @param value is the new value which should be set
     */
    public void setValue(String value) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        builder.put("id", this.id).put("value", value);
        execute(MobileCommand.SET_VALUE, builder.build());
    }
}
