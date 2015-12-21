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
public class DeptRelationTest {
    private final DeptRelation deptRelation;
    private final String expectedFrom;
    private final String expectedTo;
    private final String expectedToString;
    private final int expectedAmount;

    public DeptRelationTest(String expectedFrom, String expectedTo, int expectedAmount, String expectedToString) {
        deptRelation = new DeptRelation(expectedFrom, expectedTo, expectedAmount);
        this.expectedFrom = expectedFrom;
        this.expectedTo = expectedTo;
        this.expectedAmount = expectedAmount;
        this.expectedToString = expectedToString;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"서류", "과제", 10000, "DeptRelation{from='서류', to='과제', amount=" + 10000 + '}'}
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
        assertEquals(expectedFrom, deptRelation.getFrom());
    }

    @Test
    public void getToTest() {
        assertEquals(expectedTo, deptRelation.getTo());
    }

    @Test
    public void getAmountTest() {
        assertEquals(expectedAmount, deptRelation.getAmount());
    }

    @Test
    public void toStringTest() {
        assertEquals(expectedToString, deptRelation.toString());
    }
}
