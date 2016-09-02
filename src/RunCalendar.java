import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by yurik on 01.09.16.
 */
public class RunCalendar {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter year: ");
        String year = scanner.nextLine();
        System.out.print("Please enter month: ");
        String month = scanner.nextLine();
        System.out.print("Please enter day: ");
        String day = scanner.nextLine();

        try {
            parseDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        } catch (Exception e){
            e.printStackTrace();
            parseDate(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), LocalDate.now().getDayOfMonth());
        }
    }

    private static void parseDate(int year, int month, int day) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        generateView(monthLength, firstDayInWeek, day);
    }

    private static void generateView(int monthLength, int firstDayInWeek, int day) {
        System.out.println("   П   В   С   Ч   П   " + colorPrintWeekend("С   Н"));
        int counter = 1;
        for (int dayInFirsWeek = 1; dayInFirsWeek <= 7 ; dayInFirsWeek++) {
            if (dayInFirsWeek >= firstDayInWeek)
                printDay(dayInFirsWeek, counter++, day);
            else
                System.out.print("    ");
        }
        System.out.println();
        for (int week = 2; week <= 6; week++) {
            for (int dayInWeek = 1; dayInWeek <= 7; dayInWeek++) {
                if (counter <= monthLength)
                    printDay(dayInWeek, counter++, day);
                else
                    break;
            }
            System.out.println();
        }
    }

    private static void printDay(int dayInWeek, int counter, int day) {
        if (counter == day)
            System.out.printf(ANSI_CYAN + "%4d" + ANSI_RESET, counter);
        else if (dayInWeek >= 6)
            System.out.printf(ANSI_RED + "%4d" + ANSI_RESET, counter);
        else
            System.out.printf("%4d" , counter);
    }

    private static String colorPrintWeekend(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }
}