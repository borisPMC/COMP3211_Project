import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PirTest {

    @Test
    void testGetPirType() {
        assertEquals("N", PIR.getPirType("Test Note"));
    }
}
