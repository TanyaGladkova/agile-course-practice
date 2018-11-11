package ru.unn.agile.mathstatistics.model;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class MathStatisticsTests {

    @Test
    public void canCalculateExpectedValueWithOneNumbers() {
        Integer[] values = {10};
        Double[]  probabilities = {1.0};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(10), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueWithOneNegativeInteger() {
        Integer[] values = {-23};
        Double[]  probabilities = {1.0};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(-23), expectedValue);
    }

    @Test
    public void canCalculateExpectedValuePositiveIntegers() {
        Integer[] values = {10, 5, 25, 30};
        Double[]  probabilities = {0.25, 0.25, 0.25, 0.25};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(17.5), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueWithDouble() {
        Double[] values = {1.2, 3.3, 0.2, 8.1, 0.9};
        Double[] probability = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probability);

        assertEquals(new Double(2.74), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueWithIntegersAndDouble() {
        Number[] values = {1.2, 3.3, 11, 2, 8.9, 2, 36, 7.3};
        Double[] probabilities = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(8.9625), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueWithNegativeNumbers() {
        Number[] values = {-3, 8.3, 86.2, -3.5};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(22.0), expectedValue);
    }

    @Test
    public void canCalculateNegativeExpectedValue() {
        Double[] values = {-10.2, 4.3, 0.0, -31.9, 3.0, 0.33};
        Double[] probabilities = {0.1667, 0.166666, 0.166666, 0.166666, 0.166666, 0.16666};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertTrue(Math.abs(-5.745 - expectedValue) <= MathStatistics.EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithBigSizeOfDataArray() {
        Integer[] values = getMonotonicArray(0, 100);
        Double[]  probabilities = new Double[100];
        Arrays.fill(probabilities, 0.01);

        Double expected = new Double((0 + 99) * 100 / 2) * 0.01;

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(expected, expectedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueWithNullElement() {
        Number[] values = {1.2, 2, -5, null, 7};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueWithNullData() {
        Double[] values = null;
        Double[] probabilities = {1.0};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueWithEmptyData() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test
    public void canCalculateDispersionWithOneNumber() {
        Integer[] values = {8};
        Double[]  probabilities = {1.0};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0), dispersion);
    }

    @Test
    public void canCalculateDispersionWithOneNegativeNumber() {
        Integer[] values = {-23};
        Double[]  probabilities = {1.0};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0), dispersion);
    }

    @Test
    public void canCalculateDispersionWithNegativeNumbers() {
        Number[] values = {9, 3, -1, 5, -8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(33.44), dispersion);
    }

    @Test
    public void canCalculateDispersionWithArrayOfZeros() {
        Number[] values = {0, 0, 0, 0};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0.0), dispersion);
    }

    @Test
    public void canCalculateDispersionWithDouble() {
        Number[] values = {7.2, 64.3, -13.8, 0.6, 23.1};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(717.1096), dispersion);
    }

    @Test
    public void canCalculateDispersionWithBigSizeOfDataArray() {
        Integer[] values = getMonotonicArray(0, 100);
        Double[]  probabilities = new Double[100];
        Arrays.fill(probabilities, 0.01);

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertTrue(Math.abs(833.2499 - dispersion) <= MathStatistics.EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionWithNullElement() {
        Number[] values = {2, -3, 5, null, 8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionWithNullData() {
        Double[] values = null;
        Double[] probabilities = {1.0};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionWithEmptyData() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test
    public void canCalculateMomentWithOneNumber() {
        Integer[] values = {3};
        Double[]  probabilities = {1.0};

        Double moment = MathStatistics.calculateMoment(values, probabilities, 3, 46);

        assertEquals(new Double(-79507.0), moment);
    }

    @Test
    public void canCalculateMomentWithOneNegativeNumber() {
        Integer[] values = {-11};
        Double[]  probabilities = {1.0};

        Double moment = MathStatistics.calculateMoment(values, probabilities, 1, 44);

        assertEquals(new Double(-55), moment);
    }

    @Test
    public void canCalculateMomentWithNegativeNumbers() {
        Number[] values = {9, 3, -1, 9, -8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double moment = MathStatistics.calculateMoment(values, probabilities, 4, 9);

        assertEquals(new Double(18963.4), moment);
    }

    @Test
    public void canCalculateMomentWithArrayOfZeros() {
        Number[] values = {0, 0, 0, 0};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double moment = MathStatistics.calculateMoment(values, probabilities, 5, 2);

        assertEquals(new Double(-32.0), moment);
    }

    @Test
    public void canCalculateMomentWithDouble() {
        Number[] values = {7.1, 6.3, 11.8, 0.8, 23.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double moment = MathStatistics.calculateMoment(values, probabilities, 3, 3);

        assertTrue(Math.abs(1803.618 - moment) <= MathStatistics.EPSILON);
    }

    @Test
    public void canCalculateMomentWithBigSizeOfDataArray() {
        Integer[] values = getMonotonicArray(-400, 400);
        Double[]  probabilities = new Double[800];
        Arrays.fill(probabilities, 0.00125);

        Double moment = MathStatistics.calculateMoment(values, probabilities, 3, 1);

        assertTrue(Math.abs(-240003.0 - moment) <= MathStatistics.EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateMomentWithNullElement() {
        Number[] values = {1, null, 7, 2, 2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateMoment(values, probabilities, 3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateMomentWithNullData() {
        Double[] values = null;
        Double[] probabilities = {1.0};

        MathStatistics.calculateMoment(values, probabilities, 1, 2.8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateMomentWithEmptyData() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateMoment(values, probabilities, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateMomentWithZeroOrder() {
        Number[] values = {0.1, 9.3, 1.8, 0.8, 2.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateMoment(values, probabilities, 0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateMomentWithNegativeOrder() {
        Number[] values = {0.1, 0.3, 6.8, -6.8, 2.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateMoment(values, probabilities, -5, 3);
    }

    @Test
    public void canCalculateExpectedValue() {
        Number[] values = {0, -3, 0.1, 0, 1.9};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);
        Double moment = MathStatistics.calculateMoment(
                values, probabilities, MathStatistics.EXPECTED_VALUE_ORDER, 0);

        assertEquals(expectedValue, moment);
    }

    @Test
    public void canCalculateCentralMoment() {
        Number[] values = {2.1, 1.2, -3, 0, 4.9};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);
        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);
        Double moment = MathStatistics.calculateMoment(
                values, probabilities, MathStatistics.DISPERSION_ORDER, expectedValue);

        assertEquals(dispersion, moment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilityVectorWithNullElement() {
        Number[] values = {1, 3, 7, 4, 2};
        Double[] probabilities = {0.2, null, 0.2, 0.2, 0.2};

        MathStatistics.calculateMoment(values, probabilities, 3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilityVectorWithNullData() {
        Double[] values = {4.0, 5.0, 6.0, 7.0};
        Double[] probabilities = null;

        MathStatistics.calculateMoment(values, probabilities, 1, 2.8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilityVectorWithEmptyData() {
        Number[] values = {0, -3, 0.1, 0, 1.9};
        Double[] probabilities = {};

        MathStatistics.calculateMoment(values, probabilities, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenDataLengthAndProbabilityVectorLengthAreNotEqual() {
        Number[] values = {7.3, -5.1, 9.3, 5.5};
        Double[] probability = {0.1, 0.2, 0.7};

        MathStatistics.calculateDispersion(values, probability);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenTotalProbabilityIsNotEqual1() {
        Number[] values = {1, 3, 7, 4, 2};
        Double[] probabilities = {0.2, 0.2, 0.13, 0.2, 0.2};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilityVectorElementIsNegative() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {0.1, -0.2, 0.7};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilityVectorElementIsMoreThan1() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {1.2, 0.5, 0.7};

        MathStatistics.calculateDispersion(values, probabilities);
    }


    private Integer[] getMonotonicArray(final int start, final int end) {
        Integer[] data = new Integer[end - start];
        for (int i = 0; i < data.length; ++i) {
            data[i] = start + i;
        }
        return data;
    }

}
