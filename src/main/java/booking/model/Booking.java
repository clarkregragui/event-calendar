package booking.model;

import java.util.Date;


public class Booking implements Comparable<Booking>{
	Date created, start, end;
	String employee;
	
	private Booking(Date created, String employee, Date start, Date end){
		this.created = created;
		this.employee = employee;
		this.start = start;
		this.end = end;
	}
	
	public static Booking getInstance(Date startDate, String employee, Date bookingStart, Date bookingEnd) {
		return new Booking(startDate, employee, bookingStart, bookingEnd);
	}
	
	
	public Date getCreated() {
		return created;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public String getEmployee() {
		return employee;
	}
	
	

	public int compareTo(Booking booking) {
			
		//starts during
		if(booking.getStart().equals(this.start) || (booking.getStart().after(this.start) && booking.getStart().before(this.end)))
			return 0;
		//finishes during
		if(booking.getEnd().after(this.start) && (booking.getEnd().before(this.end) || booking.getEnd().equals(this.end)))
			return 0;
		//starts before and ends after
		if(booking.getStart().before(this.start) && (booking.getEnd().after(this.end) || booking.getEnd().equals(this.end)))
			return 0;
		
		//before no overlap
		if(booking.getStart().before(this.start) && (booking.getEnd().before(this.start) || booking.getEnd().equals(this.start)))
			return -1;
		//after
		//implied if(booking.getStart().after(this.end) || booking.getStart().equals(this.end))
		return 1;
		
	}
	
}