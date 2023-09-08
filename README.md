<img align="left" src="https://github.com/Ramotion/fluid-slider-android/blob/master/Fluid_slider.gif" width="480" height="360">

<h1 align="left">FLUID SLIDER [KOTLIN]</h1>

<h4>A slider widget with a popup bubble displaying the precise value selected</h4>

## Requirements

- Android 4.1 Jelly Bean (API lvl 16) or greater

## Basic usage

Place the `FluidSlider` in your layout.

To track the current position of the slider, set the `positionListener`, as shown below:
```
val slider = findViewById<FluidSlider>(R.id.fluidSlider)
slider.positionListener = { p -> Log.d("MainActivity", "current position is: $p" )}
```

You can also track the beginning and completion of the movement of the slider, using the following properties:
`beginTrackingListener` and` endTrackingListener`. Example below:
```
slider.beginTrackingListener = { /* action on slider touched */ }
slider.endTrackingListener = { /* action on slider released */ }
```

Here is simple example, how to change `FluidSlider` range.
```kotlin
// Kotlin
val max = 45
val min = 10
val total = max - min

val slider = findViewById<FluidSlider>(R.id.fluidSlider)
slider.positionListener = { pos -> slider.bubbleText = "${min + (total  * pos).toInt()}" }
slider.position = 0.3f
slider.startText ="$min"
slider.endText = "$max"

// Java
final FluidSlider slider = findViewById(R.id.fluidSlider);
slider.setBeginTrackingListener(new Function0<Unit>() {
    @Override
    public Unit invoke() {
        Log.d("D", "setBeginTrackingListener");
        return Unit.INSTANCE;
    }
});

slider.setEndTrackingListener(new Function0<Unit>() {
    @Override
    public Unit invoke() {
        Log.d("D", "setEndTrackingListener");
        return Unit.INSTANCE;
    }
});

// Or Java 8 lambda
slider.setPositionListener(pos -> {
    final String value = String.valueOf( (int)((1 - pos) * 100) );
    slider.setBubbleText(value);
    return Unit.INSTANCE;
});
```

Here are the attributes you can specify through XML or related setters:
* `bar_color` - Color of slider.
* `bubble_color` - Color of circle "bubble" inside bar.
* `bar_text_color` - Color of `start` and `end` texts of slider.
* `bubble_text_color` - Color of text inside "bubble".
* `start_text` - Start (left) text of slider.
* `end_text` - End (right) text of slider.
* `text_size` - Text size.
* `duration` - Duration of "bubble" rise in milliseconds.
* `initial_position` - Initial positon of "bubble" in range form `0.0` to `1.0`.
* `size` - Height of slider. Can be `small` (40dp) and `normal` (56dp).

## 📄 License

Fluid Slider (FluidSlider.kt) was originally released from Ramotion (https://github.com/Ramotion/fluid-slider-android); reviced by milekey under the MIT license.

See [LICENSE](./LICENSE.txt) for details.