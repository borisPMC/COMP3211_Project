import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class PirTest {

    @Test
    void testIsContainString() {
        assertTrue(PIR.isContainString("Hello World", "World"));
        assertFalse(PIR.isContainString("Hello World", "Universe"));
    }

    @Test
    void testIsDateSatisfy() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("01/01/2022");

        assertTrue(PIR.isDateSatisfy(date, '<', "02/01/2022"));
        assertFalse(PIR.isDateSatisfy(date, '>', "02/01/2022"));
        assertTrue(PIR.isDateSatisfy(date, '=', "01/01/2022"));

        // Additional conditions
        assertFalse(PIR.isDateSatisfy(date, '<', "01/01/2022"));
        assertTrue(PIR.isDateSatisfy(date, '>', "31/12/2021"));
        assertFalse(PIR.isDateSatisfy(date, '=', "02/01/2022"));
    }
}