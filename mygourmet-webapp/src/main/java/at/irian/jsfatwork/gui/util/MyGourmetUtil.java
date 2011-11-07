package at.irian.jsfatwork.gui.util;

import java.util.Date;
import java.util.Calendar;

public class MyGourmetUtil {

    public static int getAge(Date birthday) {
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthday);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

}