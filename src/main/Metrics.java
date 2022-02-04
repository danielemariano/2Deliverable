package main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class Metrics {

	private Metrics(){

	}

	public static int numberOfBugFixedForRelease(Release release, Class c) {
		int counter = 0;
		if(c.getTicket()!= null) {
			for(Ticket t : c.getTicket()) {
				if(t.getFV().equals(release.getInt())) {
					counter = counter + 1;
				}
			}
		}
		return counter;
	}

	public static long classAge(Class c) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
	    LocalDate localDate = LocalDate.now();
	    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		return ((date.getTime() - c.getDate().getTime())/ (1000 * 60 * 60 * 24 * 7));
	}

	public static int getAVGChg(Class c) {
		if(c.getRecurrence()==0) {
			c.setRecurrence(1);
		}
		return c.getSumChg()/c.getRecurrence();
	}
}
