# Forecast Chart

A forecast chart builder using MPAndroidChart library with reactive view and writed in Kotlin.

## Add as dependency

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.oxeanbits.forecast-chart:3.1.3'
}
```

## Example

![forecast-chart](https://user-images.githubusercontent.com/13650290/69456531-a037b300-0d49-11ea-8333-dbdd7e76cc41.gif)

## Basic usage

```kotlin
expectedDataArray = listOf(ChartEntry(1f, 1f), ChartEntry(2f, 2f), ChartEntry(3f, 3f)
actualDataArray = listOf(ChartEntry(1f, 1f), ChartEntry(2f, 2f))
forecastedDataArray = listOf(ChartEntry(2f, 2f), ChartEntry(3f, 3f))

forecastChartComponent{
    size(MATCH, 350)
    expectedLine(expectedDataArray, "Expected", Color.BLUE)
    actualLine(actualDataArray, "Actual", Color.GREEN)
    forecastedLine(forecastedDataArray, "Forecasted", Color.GRAY)
    unit("m³")
}
```

Initialize the timezone information in your `Application.onCreate()` method, for handle with date-time:
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidThreeTen.init(this)
}
```

## References

- 100% Kotlin based
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- [Anvil](https://github.com/anvil-ui/anvil)
- [ThreeTen Android Backport](https://github.com/JakeWharton/ThreeTenABP)

## MIT License

Copyright (c) 2019 Oxeanbits

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
