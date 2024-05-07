import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;

public class JUnitCycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }
}
