package booking.calendar;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import booking.factory.BookingFactory;




public class BookingCalendarTest {
	
	private BookingCalendar bookingCalendar;
	
	@Before
	public void setup(){
		bookingCalendar = new BookingCalendar("0900", "1730");
	}

	@Test
	public void addBookingTest() throws ParseException{
		
		assertEquals("new empty Calendar", 0, bookingCalendar.size());
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		assertTrue(inserted);
		assertEquals("add 1 item", 1, bookingCalendar.size());
	}
	
	
	@Test
	public void clashBookingNotAddedTest() throws ParseException{
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		assertTrue("should be inserted", inserted);
		assertEquals("add 1 item", 1, bookingCalendar.size());
		
		inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		assertFalse("should not be inserted", inserted);
		assertEquals("Calendar should still contain 1 item", 1, bookingCalendar.size());
	}
	
	@Test
	public void multipleBookingsAddedTest() throws ParseException{
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		assertTrue("should be inserted", inserted);
		assertEquals("add 1 item", 1, bookingCalendar.size());
		
		inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 11:00", "2"));
		assertTrue("should be inserted", inserted);
		assertEquals("Calendar should contain 2 items", 2, bookingCalendar.size());
	}
	
	@Test
	public void noBookingsBeforeOpeningTimeTest() throws ParseException{
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 08:59", "2"));
		assertFalse("should not be inserted", inserted);
		assertEquals("add 1 item", 0, bookingCalendar.size());
	}
	
	@Test
	public void noBookingsStartingOnOrAfterClosingTimeTest() throws ParseException{
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 17:30", "2"));
		assertFalse("should not be inserted", inserted);
		assertEquals("add 1 item", 0, bookingCalendar.size());
	}
	
	@Test
	public void noBookingsFinishingAfterClosingTimeTest() throws ParseException{
		
		boolean inserted = bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 15:31", "2"));
		assertFalse("should not be inserted", inserted);
		assertEquals("add 1 item", 0, bookingCalendar.size());
	}
	
	@Test
	public void calendarToStringTest() throws ParseException{
		
		bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP002", "2011-03-21 13:00", "2"));
		bookingCalendar.addBooking(BookingFactory.createBooking("2011-03-17 10:17:06", "EMP003", "2011-03-22 13:00", "2"));
		
		assertEquals("toString should be as stated", 
				"2011-03-21\n\n09:00 11:00 EMP001\n\n13:00 15:00 EMP002\n\n2011-03-22\n\n13:00 15:00 EMP003\n\n", bookingCalendar.toString());
		
	}
	
}
