package booking.factory;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import booking.model.Booking;




public class BookingFactoryTest {

	@Test
	public void createBookingTest() throws ParseException{

		assertNotNull("booking object shold be returned", BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		
	}
	
	@Test
	public void bookingContentsCorrectTest() throws ParseException{
		
		SimpleDateFormat createdDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat bookingDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Booking booking = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2");
		assertEquals("created date should be as input","2011-03-17 10:17:06", createdDateFormat.format(booking.getCreated()) );
		assertEquals("start time should be as input", "2011-03-21 09:00", bookingDateFormat.format(booking.getStart()));
		assertEquals("end time should be as input", "2011-03-21 11:00", bookingDateFormat.format(booking.getEnd()));
	}
	
}
