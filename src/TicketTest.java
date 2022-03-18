import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TicketTest {

    @Test
    void checkInsufficientSeats() throws TicketException {
        TheaterSeatAllocation theaterSeatAllocation = new TheaterSeatAllocation(10,20);
        String inputPathString = "C:\\Users\\Karan Uppin\\IdeaProjects\\MovieTheaterSeatingChallenge\\Input\\Input too big";
        boolean isException = false;
        try {
            theaterSeatAllocation.readInputFile(inputPathString);
        } catch (TicketException e) {
            assertTrue(true);
            isException = true;
        } catch (Exception e) {
            assertTrue(false);
            isException = true;
        } finally {
            assertTrue(isException);
        }
    }

    @Test
    void checkZeroSeatsFill() throws TicketException {

        TheaterSeatAllocation theaterSeatAllocation = new TheaterSeatAllocation(10,20);
        String inputPathString = "C:\\Users\\Karan Uppin\\IdeaProjects\\MovieTheaterSeatingChallenge\\Input\\Zero seats";
        boolean isException = false;
        try {
            theaterSeatAllocation.readInputFile(inputPathString);
        } catch (TicketException e) {
            assertTrue(true);
            isException = true;
        } catch (Exception e) {
            assertTrue(false);
            isException = true;
        } finally {
            assertTrue(isException);
        }
    }
}
