package com.hybridss.androidbaseutils.utilities.utils;

import com.hybridss.androidbaseutils.logger.LGTest;

import java.text.DateFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class UTDateUtilities {
    public static String UTInvalidSmallDate = "1901-01-01";
    public static String UTInvalidLargeDate = "1901-01-01 00:00:00";
    public static String UTDateSmallFormat = "yyyy-MM-dd";
    public static String UTDateSmallFormatDayAndMonth = "dd MMMM";
    public static String UTDateSmallFormatDateAndMonth = "yyyyMM";
    public static String UTDateSmallFormatDDMMMYY = "dd-MMM-yy";
    public static String UTDateSmallFormatDDMMYY = "dd/MM/yy";
    public static String UTDateLargeFormat = "yyyy-MM-dd HH:mm:ss";
    public static String UTDateLargeFormatDDMMMYY = "dd-MM-yyyyHH:mm:ss";
    public static String UTDateLargeFormatWithOutSeg = "yyyy-MM-dd HH:mm";
    public static String UTDateSmallFormatNOC = "dd/MM/yyyy";
    public static String UTDateSmallFormatNOC2 = "dd-MM-yyyy";
    public static String UTDateLargeFormatNOC = "dd/MM/yyyy HH:mm:ss";
    public static String UTDateLargeFormatNOCAlt = "dd-MM-yyyy HH:mm:ss";
    public static String UTDateMediumFormatRuta = "dd/MM/yyyy HH:mm";
    public static String UTDateTimeFormat = "HH:mm:ss";
    public static String UTDateShorTimeFormat = "HH:mm";
    public static String UTDateFormatHour = "hh:mm a";
    public static String UTDateFormatHourAMPM = "hh:mm aa";
    public static String UTDateSmallFormatNOC3 = "MM/dd/yyyy";
    public static String UTDateSmallFormat3MonthCharts = "dd/MMM/yyyy";
    public static String UTDateFormatHourSecond = "hh:mm:ss a";
    public static String UTDateFormatDDMMMYYYY = "dd-MMMM-yyyy";


    public static String AM = "am";
    public static String PM = "pm";

    public static Date invalidSmallDate() {
        return dateFromString(UTInvalidSmallDate);
    }

    public static Date invalidLargeDate() {
        return dateFromString(UTInvalidLargeDate);
    }

    public static String validaCadenaFecha(String dateSource) {
        Date dateSourceNSDate = null;
        if (dateSource.length() == 8 || dateSource.length() == 10) {
            dateSourceNSDate = dateFromString(dateSource);
        } else if (dateSource.length() == 14 || dateSource.length() == 16 || dateSource.length() == 17 || dateSource.length() == 19 || dateSource.length() == 26) { //fix 26 para fecha 1901-01-01 00:00:00.000000
            dateSourceNSDate = dateFromString(dateSource, UTDateLargeFormat);
        }
        return validaFechaNSDate(dateSourceNSDate);
    }

    public static String convert12To24(Date date, String format) {
        SimpleDateFormat isoFormat = new SimpleDateFormat(format, Locale.US);
        isoFormat.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return isoFormat.format(date);
    }

    public static String validaFechaNSDate(Date dateSource) {
        Date invalid = dateFromString(UTInvalidLargeDate);
        if (dateSource.compareTo(invalid) == -1) {
            return UTInvalidLargeDate;
        } else {
            return stringFromDate(dateSource);
        }
    }

    public static ArrayList<Date> getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        while (cal.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
            cal.add(Calendar.DATE, -1);
        }
        ArrayList<Date> fechasEnLaSemana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            fechasEnLaSemana.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return fechasEnLaSemana;
    }

    public static Date dateFromString(String dateInString, String dateFormat) {
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            date = formatter.parse(dateInString);
        } catch (Exception e) {
            date = UTDateUtilities.dateFromString(UTInvalidLargeDate, UTDateLargeFormat);
        }

        return date;
    }

    public static Date dateFromString(String dateInString) {
        return dateFromString(dateInString, UTDateSmallFormat);
    }

    public static String stringFromDateBancaDigital(Date date) {
        return stringFromDate(date, UTDateSmallFormatNOC);
    }

    public static String stringFromString(String date, String formato) {
        return String.format(date, formato);
    }

    public static String stringFromDate(Date date, String formato) {
        try {
            return new SimpleDateFormat(formato,new Locale("es")).format(date).toUpperCase().replace(".","");
        } catch (Exception ex) {
            return UTInvalidLargeDate;
        }
    }

    public static String stringFromDate(Date date) {
        String d = stringFromDate(date, UTDateSmallFormat);
        return d;
    }

    public static String dateHourFromDate(Date date) {
        return stringFromDate(date, UTDateLargeFormatNOC);
    }

    public static Date dateWithDate(Date date) {
        return UTDateUtilities.dateFromString(UTDateUtilities.stringFromDate(date));
    }

    public static Date dateWithDate(Date date, String format) {
        return dateFromString(stringFromDate(date, format), format);
    }

    public static int hourFromToday() {
        return hourFromDate(new Date());
    }

    public static int dayFromToday() {
        return dayFromDate(new Date());
    }

    public static int monthFromToday() {
        return componentFromDate(new Date(), Calendar.MONTH);
    }

    public static int yearFromToday() {
        return componentFromDate(new Date(), Calendar.YEAR);
    }


    public static int hourFromDate(Date date) {
        return componentFromDate(date, Calendar.HOUR_OF_DAY);
    }

    public static int dayFromDate(Date date) {
        return componentFromDate(date, Calendar.DAY_OF_MONTH);
    }

    public static int monthFromDate(Date date) {
        return componentFromDate(date, Calendar.MONTH);
    }

    public static int yearFromDate(Date date) {
        return componentFromDate(date, Calendar.YEAR);
    }

    public static int operativeWeekDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int weekday = c.get(Calendar.DAY_OF_WEEK) + 6;

        if (weekday > 7) {
            return weekday % 7;
        }

        return weekday;
    }

    public static int getDayOfWheek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int componentFromDate(Date date, int components) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(components);
    }

    public static boolean isToday(Date date) {
        return isEqualDate(date, new Date());
    }

    public static boolean isToday(String date) {

        return isEqualDate(dateFromString(date), new Date());
    }

    public static boolean isEqualDate(Date date, Date otherDate) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        Calendar otherCalendar = new GregorianCalendar();
        otherCalendar.setTime(otherDate);

        if (calendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == otherCalendar.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == otherCalendar.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    public static int getMinutesDiff(Date date, Date otherDate) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        Calendar otherCalendar = new GregorianCalendar();
        otherCalendar.setTime(otherDate);

        return Math.abs((calendar.get(Calendar.HOUR) * 60) + calendar.get(Calendar.MINUTE)) - ((otherCalendar.get(Calendar.HOUR) * 60) + otherCalendar.get(Calendar.MINUTE));
    }

    public static long minutesBetween(Date start, Date end) {
        long difference = (end.getTime() - start.getTime());// un dia.
        //double difference = ((double)(start.getTime() - end.getTime())) / 86400000;// un dia.
        //return Math.abs(Math.round(difference));
        return TimeUnit.MILLISECONDS.toMinutes(difference);
    }

    public static long daysBetween(Date start, Date end) {
        long difference = (end.getTime() - start.getTime());// un dia.
        //double difference = ((double)(start.getTime() - end.getTime())) / 86400000;// un dia.
        //return Math.abs(Math.round(difference));
        return TimeUnit.MILLISECONDS.toDays(difference);
    }

    public static Date addDaysToDate(Date dateStart, int daysAdd) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateStart);
        calendar.add(Calendar.DATE, daysAdd);
        return calendar.getTime();
    }

    public static Date addMinutesToDate(Date dateStart, int minutesAdd) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MINUTE, minutesAdd);
        return calendar.getTime();
    }

    public static Date addWeeksToDate(Date startDate, int weeks) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.add(Calendar.WEEK_OF_YEAR, weeks);
        return calendar.getTime();
    }

    public static Date addMonthsToDate(Date startDate, int months) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static Date setDayToDate(Date startDate, int day) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static String stringFromTodayWithFormat(String format) {

        Date date = new Date();

        return stringFromDate(date, format);
    }

    public static String validaFechaString(String dataSource) {

        dataSource = String.format(dataSource, "yyyy-MM-dd HH:mm:ss");

        if (validaCadenaFecha(dataSource).equals(UTInvalidLargeDate)) {
            return stringFromTodayWithFormat(UTDateLargeFormat);
        } else {
            return dataSource;
        }
    }

    public static Date dateFromTodayWithFormat24H() {

        Date date = new Date();

        return UTDateUtilities.dateWithDate(date, UTDateLargeFormat);
    }

    public static ArrayList<Date> weekDatesBetweenDate(Date startDate, Date endDate) {

        ArrayList<Date> fechas = new ArrayList<>();
        Calendar cal = new GregorianCalendar();
        Date temp = startDate;
        fechas.add(startDate);

        while (temp.before(endDate)) {

            cal.setTime(temp);
            cal.add(Calendar.DATE, 7);
            temp = cal.getTime();

            fechas.add(temp);
        }
        return fechas;
    }

    public static Date calcularFechaActualMenosAnios(int anios) {
        Calendar cActual = Calendar.getInstance();
        int years = cActual.get(Calendar.YEAR) - anios;
        cActual.set(Calendar.YEAR, years);
        Date dRetorno = cActual.getTime();
        return dRetorno;
    }

    public static int calcularFechaMayor(String fecha, String fechaActual) {
        return fechaActual.compareTo(fecha);
    }

    public static String antiquityFromDate(Date desde) {
        if (desde != null) {
            Calendar desdeCal = Calendar.getInstance();
            desdeCal.setTime(desde);
            if (desdeCal.get(Calendar.YEAR) != 1901) {
                Calendar actualCal = Calendar.getInstance();
                int years = actualCal.get(Calendar.YEAR) - desdeCal.get(Calendar.YEAR);
                int months = actualCal.get(Calendar.MONTH) - desdeCal.get(Calendar.MONTH);
                int days = actualCal.get(Calendar.DAY_OF_MONTH) - desdeCal.get(Calendar.DAY_OF_MONTH);
                if (months < 0 || (months == 0 && days < 0)) {
                    years--;
                }
                if (years < 1) {
                    return "Menor a un año";
                }
                if (years == 1) {
                    return "De 1 a 2 años";
                }
                if (years > 1) {
                    return "Más de 2 años";
                }
            }
            return "";
        }
        return "";
    }

    public static String getNameWeekDayFromInt(int id) {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "ES"));

        String[] weekdays = dfs.getWeekdays();
        if (id >= 1 && id <= 7) {
            return weekdays[id].toUpperCase();
        } else {
            return String.valueOf(id);
        }
    }

    public static String getDateWithNameMonth(Date fecha, String separators, int digitsMonth, int digitsYear, boolean upperCase) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        NumberFormat numberFormatMonth = NumberFormat.getInstance();
        numberFormatMonth.setMaximumIntegerDigits(digitsMonth);
        numberFormatMonth.setMinimumIntegerDigits(digitsMonth);

        NumberFormat numberFormatYear = NumberFormat.getInstance();
        numberFormatYear.setMaximumIntegerDigits(digitsYear);
        numberFormatYear.setMinimumIntegerDigits(digitsYear);

        String mes = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es", "ES"));
        mes = mes.substring(0, 3).toUpperCase();
        String stFecha = String.format("%s" + separators + "%s" + separators + "%s", numberFormatMonth.format(calendar.get(Calendar.DAY_OF_MONTH)), mes, numberFormatYear.format(calendar.get(Calendar.YEAR)));
        if (digitsYear > 3) {
            stFecha = stFecha.replace(",", "");
        }
        if (upperCase) {
            stFecha = stFecha.toUpperCase();
        } else {
            stFecha = stFecha.toLowerCase();
        }
        return stFecha;
    }

    public static int daysFromDate(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Long timeIntervalSinceDate(Date desde, Date hasta) {
        double difference = (((double) (hasta.getTime() - desde.getTime())) / 86400000)/* un dia.*/ * 24/* horas.*/ * 60/*minutos*/ * 60;//segundos
        return Math.round(difference);
    }

    public static int yearsFromDate(Date startDate, Date endDate) {
        boolean estanInvertidas = false;
        if (startDate.getTime() > endDate.getTime()) {
            estanInvertidas = true;
            Date tmpIntercambio = startDate;
            startDate = endDate;
            endDate = tmpIntercambio;
        }

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate);
        calendarEnd.setTime(endDate);

        int years = calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR);
        int months = calendarEnd.get(Calendar.MONTH) - calendarStart.get(Calendar.MONTH);
        int days = calendarEnd.get(Calendar.DAY_OF_MONTH) - calendarStart.get(Calendar.DAY_OF_MONTH);
        if (months < 0 || (months == 0 && days < 0)) {
            years--;
        }

        if (estanInvertidas) {
            years = -years;
        }

        return years;
    }

    public static int monthsFromDate(Date startDate, Date endDate) {
        Calendar startDateCalendar = Calendar.getInstance();
        Calendar endDateCalendar = Calendar.getInstance();

        startDateCalendar.setTime(startDate);
        endDateCalendar.setTime(endDate);
        int difA = endDateCalendar.get(Calendar.YEAR) - startDateCalendar.get(Calendar.YEAR);
        int difM = difA * 12 + endDateCalendar.get(Calendar.MONTH) - startDateCalendar.get(Calendar.MONTH);
        return difM;
    }

    public static String formater12Hours(String HORA, boolean siSegundos, boolean siAmPm) {
        //HORA ="17:20:10";
        String[] convert = HORA.split(":");
        int hora = Integer.parseInt(convert[0]) % 24;
        int minutos = Integer.parseInt(convert[1]) % 60;
        int segundos = Integer.parseInt(convert[2]) % 60;
        int nuevahora = (hora > 12) ? hora - 12 : hora;
        HORA = "";

        if (nuevahora != 0)
            HORA += nuevahora + ":" + ((minutos == 0) ? "00" : minutos);
        else
            HORA += "00:" + ((minutos == 0) ? "00" : minutos);
        if (siSegundos)
            HORA += ":" + segundos;
        if (siAmPm) {
            if (hora >= 12)
                HORA += " pm";
            else

                HORA += " am";
        }
        return HORA;
    }

    public static String getStringFormattedDate(Date date) {
        String dateTexted = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String day = String.format(Locale.US, "%02d", c.get(Calendar.DAY_OF_MONTH));
        String year = String.format(Locale.US, "%s", UTDateUtilities.stringFromDate(date, "yyyy"));
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es", "ES"));

        if (date == null) {
            return dateTexted;
        }
        return String.format("%s de %s de %s", day, month, year);
    }

    public static String getNameDayOfWeek(String diaEnIngles) {

        switch (diaEnIngles) {
            case "Monday":
                return "Lunes";
            case "Tuesday":
                return "Martes";
            case "Wednesday":
                return "Miercoles";
            case "Thursday":
                return "Jueves";
            case "Friday":
                return "Viernes";
            case "Saturday":
                return "Sabado";
            case "Sunday":
                return "Domingo";
        }
        return "";
    }

    public static String getStringDateinverted(String date) {
        //date = 2-10-2019 to 2019-2-10
        String[] convert = date.split("-");
        return convert[2]+"-"+ convert[1]+"-"+ convert[0];
    }

    public static String getMonthName(Date date){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es", "ES"));
        } catch (Exception e){
            return "Invalid";
        }
    }

    public static String getShortNameDayOfWeek(String dateComplete)
    {
        String[] dataParts = dateComplete.split(" ");
        if (dataParts.length >= 2) {
            String original = dataParts[0];
            String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);
            String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
            try {
                dataParts[0] = cadenaSinAcentos.toLowerCase().substring(0,3);
            } catch (Exception exc) { //Escenario en el cual la primer parte de la cadena pueda llegar con menos de 3 caracteres, retorna lo mismo que viene
                if (LGTest.isDebug())
                    exc.printStackTrace();
                dataParts[0] = cadenaSinAcentos;
            }
            return dataParts[0] + " " + dataParts[1];
        } else {
            return dateComplete;
        }
    }
}
