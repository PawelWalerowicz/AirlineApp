package controllers.aerial;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static utilities.InputValidator.checkForQuit;
import static utilities.InputValidator.isDateValid;

public class DateController {
    private boolean proceed;
    SimpleDateFormat sdfWithTime = new SimpleDateFormat("E, dd-MM-yyyy, HH:mm");
    SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("E, dd-MM-yyyy");
    Calendar earliestDate;
    Calendar latestDate;

    public Calendar askForEarliestDate() {
        Calendar earliestDate=null;
        if(proceed) {
            System.out.print("Please enter earliest possible date for departure (date format DD-MM-YYYY),\"Q\" to quit, or enter to choose today: "); //todo:enter option to input tomorrow after enter
            earliestDate= askForDate();
        }
        this.earliestDate=earliestDate;
        return earliestDate;
    }

    public Calendar askForLatestDate(Calendar earliestDate) {
        Calendar latestDate = null;

        if(proceed) {
            System.out.print("Please enter latest possible date for departure (date format DD-MM-YYYY), or \"Q\" to quit, or enter to choose a week after earliest date: ");
            do {
                latestDate = askForDate();
                latestDate.set(Calendar.HOUR,23);
                latestDate.set(Calendar.MINUTE,59);
                if (proceed && latestDate.before(earliestDate)) {
                    System.out.println("Latest date cannot be set before earliest date (" + sdfWithTime.format(earliestDate.getTime()) + "). Please try again.");
                }
            } while (proceed && latestDate.after(earliestDate));
        }
        return latestDate;
    }

    private Calendar askForDate() {
        Scanner scanner = new Scanner(System.in);
        Calendar chosenDate = new GregorianCalendar();
        String date;
        boolean dateWithoutMistakes=true;
        if(proceed) {
            do {
                date = scanner.nextLine();
                proceed = checkForQuit(date);
                if(proceed) {
                    if(date.isEmpty()) {
                        return chooseDefaultDate();
                    } else {
                        dateWithoutMistakes= correctDateFormat(date);
                    }
                }
            } while (proceed && !dateWithoutMistakes);

            if (proceed) {
                chosenDate = getDateFromString(date);
            }
        }
        return chosenDate;

    }

    private Calendar chooseDefaultDate() {
        if(earliestDateExist()) {
            Calendar weekAfterEarliestDate = earliestDate;
            weekAfterEarliestDate.add(Calendar.DAY_OF_YEAR,7);
            System.out.println("Date set for week after earliest date: " + sdfWithoutTime.format(weekAfterEarliestDate.getTime()));
            return weekAfterEarliestDate;
        } else {
            Calendar today = new GregorianCalendar();
            System.out.println("Date set for today: " + sdfWithoutTime.format(today.getTime()));
            return today;
        }
    }

    private boolean earliestDateExist() {
        boolean earliestDateExist;
        try {
            earliestDate.toString();
            earliestDateExist=true;
        } catch (NullPointerException exc) {
            earliestDateExist=false;
        }
        return earliestDateExist;
    }

    private boolean correctDateFormat(String date) {
        if (!isDateValid(date)) {
            System.out.print("Please enter correct date (date format DD-MM-YYYY): ");
            return false;
        } else if(!isDateWithinAYear(date)) {
            System.out.print("Cannot search flights over one year ahead. Please try again: ");
            return false;
        } else if(!isDateAfterNow(date)) {
            System.out.print("Chosen date cannot be current day or before. Please try again: ");
            return false;
        } else return true;
    }

    private boolean isDateWithinAYear(String date) {
        if (isDateValid(date)) {
            Calendar yearFromNow = new GregorianCalendar();
            int nextYear = yearFromNow.get(Calendar.YEAR) + 1;
            yearFromNow.set(Calendar.YEAR,nextYear);
            Calendar chosenDate = getDateFromString(date);
            return chosenDate.before(yearFromNow);
        } else return false;

    }

    private boolean isDateAfterNow(String date) {
        if (isDateValid(date)) {
            Calendar departureDate = getDateFromString(date);
            Calendar currentDate = new GregorianCalendar();
            return departureDate.after(currentDate);
        } else return false;

    }

    private Calendar getDateFromString(String date) {
        String[] dayMonthYearAsString = date.split("[-\\.]");
        Calendar calendar = new GregorianCalendar();
        int[] dayMonthYearAsInteger = new int[3];
        for (int i = 0; i < dayMonthYearAsString.length; i++) {
            dayMonthYearAsInteger[i] = Integer.parseInt(dayMonthYearAsString[i]);
        }
        calendar.set(dayMonthYearAsInteger[2], dayMonthYearAsInteger[1] - 1, dayMonthYearAsInteger[0], 0, 0, 0);
        return calendar;
    }

    public boolean getProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }
}
