package APIJodatimeAndAPIJavaTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Java8DateTime {
	// DateTimeFormat fmt = DateTimeFormat.forPattern("dd MMMM
	// yyyy").withLocale(Locale.FRENCH);

	public static void main(String[] args) {
		DateTime dateTime = new DateTime("2012-12-25");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("EEEE dd MMMM yyyy HH:mm:ss");
		DateTimeFormatter frenchFmt = formatter.withLocale(Locale.FRENCH);
		System.out.println(frenchFmt.print(dateTime));
		DateTimeFormatter englishFmt = formatter.withLocale(Locale.ENGLISH);
		System.out.println(englishFmt.print(dateTime));

		LocalDateTime datelocaldatetime = LocalDateTime.now();

		String localdatetime = datelocaldatetime.toString(frenchFmt);
		System.out.println(" Local date time joda to string: " + localdatetime);

		String localdatetimetext = "11/15/2013 08:00:00";
		DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss").withLocale(Locale.FRENCH);// Format
																											// for
																											// input
		DateTime jodatime = dtf.parseDateTime(localdatetimetext);// Parsing raw
																	// text date
																	// inut in
																	// the date
																	// time joda
		DateTimeFormatter patternformat = DateTimeFormat.forPattern("EEEE, d MMMM yyyy").withLocale(Locale.FRENCH);
		System.out.println(" Date text " + localdatetimetext + " to date time joda " + patternformat.print(jodatime));
		Date datenow = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("EEEE d MMM yyyy");
		System.out.println(formater.format(datenow));

		LocalDate localdate = LocalDate.now();
		DateTime datetime = localdate.toDateTimeAtStartOfDay();
		System.out.println(" Date locale" + localdate + " to date time: " + datetime);
	}
}
