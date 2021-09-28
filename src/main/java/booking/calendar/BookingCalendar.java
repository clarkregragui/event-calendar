package booking.calendar;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TreeSet;

import booking.model.Booking;


public class BookingCalendar{
	
	private TreeSet<Booking> bookings;
	private int startTime, endTime;
	private SimpleDateFormat timeformat;
	
	
	
	public BookingCalendar(String startTime, String endTime){
		
		bookings = new TreeSet<Booking>();
		this.startTime = Integer.parseInt(startTime);
		this.endTime = Integer.parseInt(endTime);
		timeformat = new SimpleDateFormat("HHmm");
	}
	
	public boolean addBooking(Booking booking){
		
		int bookingStartTime = Integer.parseInt(timeformat.format(booking.getStart()));
		int bookingEndTime = Integer.parseInt(timeformat.format(booking.getEnd()));
		
		if(bookingStartTime < startTime || bookingStartTime > endTime || bookingEndTime > endTime)
			return false;
		
		boolean inserted =  bookings.add(booking); 
		if(!inserted){
			for (Booking reviewedBooking : bookings){
				if(reviewedBooking.getCreated().after(booking.getCreated()) && reviewedBooking.compareTo(booking) == 0){
					bookings.remove(reviewedBooking);
					inserted =  bookings.add(booking);
					break;
				}
			}
		}
		return inserted;
	}
	
	public int size(){
		return bookings.size();
	}
	
	@Override
	public String toString(){
		
		SimpleDateFormat bookingDayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat printableTimeFormat = new SimpleDateFormat("HH:mm");
		String bookingDay = "0000-00-00";
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<Booking> iterator = bookings.descendingIterator();
			
		while(iterator.hasNext()){
			Booking booking = iterator.next();
			String day = bookingDayFormat.format(booking.getStart());
			
			if(day.compareTo(bookingDay) > 0){
				bookingDay = day;
				sb.append(day);
				sb.append("\n\n");
			}
			sb.append(printableTimeFormat.format(booking.getStart()));
			sb.append(" ");
			sb.append(printableTimeFormat.format(booking.getEnd()));
			sb.append(" ");
			sb.append(booking.getEmployee());
			sb.append("\n\n");	
		}
		
		return sb.toString();
	}
}