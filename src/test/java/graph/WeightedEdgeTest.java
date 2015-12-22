package graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class WeightedEdgeTest {
    private final WeightedEdge<String> deptRelation;
    private final String expectedSource;
    private final String expectedDest;
    private final String expectedToString;
    private final int expectedWeight;

    public WeightedEdgeTest(String expectedSource, String expectedDest, int expectedWeight, String expectedToString) {
        deptRelation = new WeightedEdge<>(expectedSource, expectedDest, expectedWeight);
        this.expectedSource = expectedSource;
        this.expectedDest = expectedDest;
        this.expectedWeight = expectedWeight;
        this.expectedToString = expectedToString;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"서류", "과제", 10000, "Edge{source=서류, destination=과제, weight=10000}"}
                // TODO 여기다가 테스트 좀 더 추가해줘요. 기찮ㄷ...
        });
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getFormTest() {
        assertEquals(expectedSource, deptRelation.getSource());
    }

    @Test
    public void getToTest() {
        assertEquals(expectedDest, deptRelation.getDestination());
    }

    @Test
    public void getAmountTest() {
        assertEquals(expectedWeight, (long)deptRelation.getWeight());
    }

    @Test
    public void toStringTest() {
        assertEquals(expectedToString, deptRelation.toString());
    }
}
