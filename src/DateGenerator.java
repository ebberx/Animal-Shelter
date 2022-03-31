import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateGenerator {

    public DateGenerator() {

    }

    public String getDate(int week, int weekdayAsInt, int year) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, weekdayAsInt);
        cal.set(Calendar.YEAR, year);

        return sdf.format(cal.getTime());
    }

}
