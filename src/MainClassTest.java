import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int number = 14;
        if (getLocalNumber() != number)
            Assert.fail("Метод не возвращает число 14" );
    }
}
