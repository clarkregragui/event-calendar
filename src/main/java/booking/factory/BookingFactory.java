package booking.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import booking.model.Booking;


public class BookingFactory {

	private BookingFactory(){}
	
	public static Booking createBooking(String createdTime, String employee, String startTime, String duration) throws ParseException{
		
		SimpleDateFormat createdDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat bookingDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date startDate = createdDateFormat.parse(createdTime);
		Date bookingStart = bookingDateFormat.parse(startTime);
		
		GregorianCalendar bookingEndCal = new GregorianCalendar();
		bookingEndCal.setTime(bookingStart);
		bookingEndCal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(duration));
		
		Date bookingEnd = bookingEndCal.getTime();
		return Booking.getInstance(startDate, employee, bookingStart, bookingEnd);
	}
}
