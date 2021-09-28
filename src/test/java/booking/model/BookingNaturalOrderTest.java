package booking.model;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import booking.factory.BookingFactory;


public class BookingNaturalOrderTest {

	
	private Booking existing;
	
	@Before
	public void createExistingBooking() throws ParseException{
		existing = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2");
	}
	
	
	@Test
	public void FinishDuringOverlapClash() throws ParseException{
		
		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 08:00", "2");
		
		assertEquals(0, existing.compareTo(overlap));
	}
	
	@Test
	public void startBeforeAndFinishAfterOverlapClash() throws ParseException{
		
		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 08:00", "3");
		
		assertEquals(0, existing.compareTo(overlap));
	}
	
	@Test
	public void startDuringOverlapClash() throws ParseException{

		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 10:00", "2");
		
		assertEquals(0, existing.compareTo(overlap));
	}
	
	@Test
	public void identicalBookingTimeClash() throws ParseException{

		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2");
		
		assertEquals(0, existing.compareTo(overlap));
	}
	
	@Test
	public void startAtEndBoundary() throws ParseException{

		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 11:00", "2");
		
		assertEquals(1, existing.compareTo(overlap));
	}
	
	@Test
	public void endAtStartBoundary() throws ParseException{

		Booking overlap = BookingFactory.createBooking("2011-03-17 10:17:06", "EMP001", "2011-03-21 07:00", "2");
		
		assertEquals(-1, existing.compareTo(overlap));
	}
	
}
