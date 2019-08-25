package io.appium.java_client.touch.offset;

import io.appium.java_client.touch.ActionOptions;
import org.openqa.selenium.Point;

import java.util.Map;

import static java.util.Optional.ofNullable;

@SuppressWarnings("unchecked")
public class PointOption<T extends PointOption<T>> extends ActionOptions<T> {

    protected Point coordinates;

    /**
     * It creates a built instance of {@link PointOption} which takes x and y coordinates.
     * This is offset from the upper left corner of the screen.
     *
     * @param offset is an offset value.
     * @return a built option
     */
    public static PointOption point(Point offset) {
        return new PointOption().withCoordinates(offset);
    }

    /**
     * It creates a built instance of {@link PointOption} which takes x and y coordinates.
     * This is offset from the upper left corner of the screen.
     *
     * @param xOffset is x value.
     * @param yOffset is y value.
     * @return a built option
     */
    public static PointOption point(int xOffset, int yOffset) {
        return new PointOption().withCoordinates(xOffset, yOffset);
    }

    @Override
    public Map<String, Object> build() {
        final Map<String, Object> result = super.build();
        result.put("x", this.coordinates.x);
        result.put("y", this.coordinates.y);
        return result;
    }

    @Override
    protected void verify() {
        ofNullable(this.coordinates).orElseThrow(() -> new IllegalArgumentException(
                "Coordinate values must be defined"));
    }

    /**
     * It defines x and y coordinates.
     * This is offset from the upper left corner of the screen.
     *
     * @param offset is an offset value.
     * @return self-reference
     */
    public T withCoordinates(Point offset) {
        return withCoordinates(offset.x, offset.y);
    }

    /**
     * It defines x and y coordinates.
     * This is offset from the upper left corner of the screen.
     *
     * @param xOffset is x value.
     * @param yOffset is y value.
     * @return self-reference
     */
    public T withCoordinates(int xOffset, int yOffset) {
        this.coordinates = new Point(xOffset, yOffset);
        //noinspection unchecked
        return (T) this;
    }
}