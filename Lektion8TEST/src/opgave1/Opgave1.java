package opgave1;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Opgave1 {

	public static void main(String[] args) {
		System.out.println(calculateFine(LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 20), true));
	}
	
	/*
	 Pre: calculatedDate and actualDate contains valid dates and
     calculatedDate < actualDate
     (calculatedDate is the expected return-date and
      actualDate is the day you actually return the book;
      adult is true if the borrower is an adult, false else)
      */
	
	public static int calculateFine(LocalDate calculatedDate, LocalDate actualDate, boolean adult) {
		
		
		int excess = (int) ChronoUnit.DAYS.between(calculatedDate, actualDate);
		
		if (excess >= 1 && excess <=7 && adult == false) {
			return 10;
		}
		
		if (excess >= 8 && excess <= 14 && adult == false) {
			return 30;
		}
		
		if (excess >= 15 && adult == false) {
			return 45;
		}
		
		if (excess >= 1 && excess <=7 && adult == true) {
			return 20;
		}
		
		if (excess >= 8 && excess <= 14 && adult == true) {
			return 60;
		}
		
		if (excess >= 15 && adult == true) {
			return 90;
		}
		
		return 0;
	}
}
