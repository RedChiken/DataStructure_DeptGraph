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
public class PairTest<K extends Comparable<K>, V> {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 1000, "Pair{key=0, value=1000}"},
                {2, 500, "Pair{key=2, value=500}"},
                {"서류", 10000, "Pair{key=서류, value=10000}"},
                {"퉤엣", "으엨", "Pair{key=퉤엣, value=으엨}"}
        });
    }

    private final Pair<K, V> pair;
    private final K expectedKey;
    private final V expectedValue;
    private final String expectedToString;

    public PairTest(K expectedKey, V expectedValue, String expectedToString) {
        this.pair = new Pair<K, V>(expectedKey, expectedValue);
        this.expectedKey = expectedKey;
        this.expectedValue = expectedValue;
        this.expectedToString = expectedToString;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void getKeyTest() {
        assertEquals(expectedKey, pair.getKey());
    }

    @Test
    public void getValueTest() {
        assertEquals(expectedValue, pair.getValue());
    }

    @Test
    public void toStringTest() {
        assertEquals(expectedToString, pair.toString());
    }
}
