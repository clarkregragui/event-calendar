package booking.batch;


import static org.junit.Assert.*;

import org.junit.Test;


public class BatchBookingProcessorTest {

	
	@Test
	public void processBatchTest(){
		String batchFile = "0900 1730\n\n"
				+"2011-03-17 10:17:06 EMP001\n\n"
				+"2011-03-21 09:00 2\n\n"
				+"2011-03-16 12:34:56 EMP002\n\n"
				+"2011-03-21 09:00 2\n\n"
				+"2011-03-16 09:28:23 EMP003\n\n"
				+"2011-03-22 14:00 2\n\n"
				+"2011-03-17 11:23:45 EMP004\n\n"
				+"2011-03-22 16:00 1\n\n"
				+"2011-03-15 17:29:12 EMP005\n\n"
				+"2011-03-21 16:00 3";
		
		
		String expectedOutput = "2011-03-21\n\n"
						+"09:00 11:00 EMP002\n\n"
						+"2011-03-22\n\n"
						+"14:00 16:00 EMP003\n\n"
						+"16:00 17:00 EMP004\n\n";

		
		BatchBookingProcessor processor = new BatchBookingProcessor();
		
		assertEquals("output should be as expected", expectedOutput, processor.processBatch(batchFile));

	}
	
	@Test
	public void processBatchWithBadData(){
		
		String badBatch = "0900 1730\n\n"
				+"2011-03-17 10:17:06 EMP001\n\n"
				+"2011-03-21 09:00 2\n\n"
				+"2011-03-16 12:34:56 EMP002\n\n"
				+"2011-03-21 09:00 2\n\n"
				+"2011-03-16 09:28 EMP003\n\n" //seconds missing
				+"2011-03-22 14:00 2\n\n"
				+"2011-03-17 11:23:45 EMP004\n\n"
				+"2011-03-22 16:00 1\n\n"
				+"2011-03-15 17:29:12 EMP005\n\n"
				+"2011-03-21 16:00 3";
		
		String expectedOutput = "2011-03-21\n\n"
				+"09:00 11:00 EMP002\n\n"
				+"2011-03-22\n\n"
				+"16:00 17:00 EMP004\n\n";


		BatchBookingProcessor processor = new BatchBookingProcessor();

		assertEquals("output should be as expected", expectedOutput, processor.processBatch(badBatch));
	}
}
