package booking.batch;

import java.text.ParseException;

import booking.calendar.BookingCalendar;
import booking.factory.BookingFactory;
import booking.model.Booking;


public class BatchBookingProcessor {

	
	public String processBatch(String batch){
		
		String[] splitBatch = batch.split("\\s+");
		BookingCalendar bookingCalendar = new BookingCalendar(splitBatch[0], splitBatch[1]);
		
		for(int i = 2; i < splitBatch.length; i+=6){
			try{
				Booking booking = BookingFactory.createBooking(splitBatch[i] + " " + splitBatch[i+1] , splitBatch[i+2], splitBatch[i+3] +" "+ splitBatch[i+4], splitBatch[i+5]);
				bookingCalendar.addBooking(booking);
				
			}catch(ParseException e){
				e.printStackTrace();
			}
		}
		
		return bookingCalendar.toString();
	}
	

}
