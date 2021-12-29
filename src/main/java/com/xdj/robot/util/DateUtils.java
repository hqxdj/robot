package com.xdj.robot.util;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 谢道吉
 * @date 2021/12/29 18:03
 * @Description
 **/


public final class DateUtils {
    private final static DateUtils dateUtils = new DateUtils();

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        return dateUtils;
    }

    /////////////////////////////////////////////////////////
    // 枚举与常量定义 //
    /////////////////////////////////////////////////////////

    /**
     * 时间粒度枚举
     *
     * @author 满志强
     * @version 1.0
     * @date 2017年4月27日
     */
    public enum EGranularity {
        /**
         * 天
         */
        Day("1"),
        /**
         * 月
         */
        Month("4"),
        /**
         * 季度
         */
        Season("5"),
        /**
         * 半年
         */
        HalfYear("6"),
        /**
         * 年
         */
        Year("7");
        private String granularity;

        EGranularity(String granularity) {
            this.granularity = granularity;
        }

        /**
         * 获取时间粒度
         *
         * @return String
         */
        public String getGranularity() {
            return granularity;
        }
    }

    /**
     * 默认时区
     */
    public static final String TIMEZONE_DEFAULT = "GMT+8";

    /**
     * 17位日期时间格式:yyyyMMdd HH:mm:ss
     */
    public static final String PATTERN_DATETIME_17 = "yyyyMMdd HH:mm:ss";

    /**
     * 14位日期时间格式pattern:yyyyMMddHHmmss
     */
    public static final String PATTERN_DATETIME_14 = "yyyyMMddHHmmss";

    /**
     * 19位日期时间格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_DATETIME_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 16位日期时间格式:yyyy-MM-dd HH:mm
     */
    public static final String PATTERN_DATETIME_16 = "yyyy-MM-dd HH:mm";

    /**
     * 8位日期格式:yyyyMMdd
     */
    public static final String PATTERN_DATE_8 = "yyyyMMdd";

    /**
     * 10位日期格式:yyyy-MM-dd
     */
    public static final String PATTERN_DATE_10 = "yyyy-MM-dd";

    /**
     * 10位日期格式:yyyy-MM-dd
     */
    public static final String PATTERN_DATE_10_MOBILE = "yyyy.MM.dd";

    /**
     * 10位日期格式:yyyy/MM/dd
     */
    public static final String PATTERN_DATE_10_SLASH = "yyyy/MM/dd";

    /**
     * 6位日期格式:yyMMdd
     */
    public static final String PATTERN_DATE_6 = "yyMMdd";

    /**
     * 6位时间格式:HHmmss
     */
    public static final String PATTERN_TIME_6 = "HHmmss";

    /**
     * 8位时间格式:HH:mm:ss
     */
    public static final String PATTERN_TIME_8 = "HH:mm:ss";

    /**
     * 中文日期时间格式:yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String PATTERN_DATETIME_CHS = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 中文日期时间格式:yyyy年MM月dd日，例如2014年04月08日
     */
    public static final String PATTERN_DATE_CHS = "yyyy年MM月dd日";

    /**
     * 17位日期时间毫秒格式:yyyyMMddHHmmssSSS
     */
    public static final String PATTERN_DATETIMEMS_17 = "yyyyMMddHHmmssSSS";

    /**
     * 9位时间毫秒格式:HHmmssSSS
     */
    public static final String PATTERN_TIMEMS_9 = "HHmmssSSS";

    /**
     * 12位日期时间(精确到分)格式:yyyyMMddHHmm
     */
    public static final String PATTERN_DATEMINUTE_12 = "yyyyMMddHHmm";

    /**
     * 2位日期格式,年后两位:yy
     */
    public static final String PATTERN_YEAR_2 = "yy";

    /**
     * 中文日期时间格式:yyyy年M月d日，短格式，例如2014年4月8日
     */
    public static final String PATTERN_DATE_CHS_SHORT = "yyyy年M月d日";

    /**
     * 10位日期格式的短版:yyyy-M-d
     */
    public static final String PATTERN_DATE_10_SHORT = "yyyy-M-d";

    /**
     * 6位日期格式:yyyyMM
     */
    public static final String PATTERN_MONTHYEAR_6 = "yyyyMM";

    /**
     * 7位日期格式:yyyy-MM
     */
    public static final String PATTERN_MONTHYEAR_7 = "yyyy-MM";

    private static final char[] NUM_ARRAY = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    ///////////////////////////////////////////////////////////
    // 获取类函数 //
    ///////////////////////////////////////////////////////////

    /**
     * 获取当前时间(去掉毫秒精度)
     *
     * @return
     * @author 刘卫卫
     */
    public static Timestamp currentTimeToDeletePrecision() {
        String dateString = now(PATTERN_DATETIME_19);
        return toTimestamp(dateString, PATTERN_DATETIME_19);
    }

    /**
     * 获取当前时间
     *
     * @return
     * @author 满志强
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前日期
     *
     * @return
     * @author 满志强
     */
    public static Timestamp nowDate() {
        return startOfDay(now());
    }

    /**
     * 按照指定格式返回系统当前时间
     *
     * @return
     */
    public static String now(String pattern) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrYear() {
        // 获取系统日期
        String currentDateStr = DateUtils.now(PATTERN_DATE_10);
        String[] currentDateStrSplit = currentDateStr.split("\\-");
        return Integer.parseInt(currentDateStrSplit[0]);
    }

    /**
     * 取得当前系统时间
     *
     * @return yyyyMMddHHmm
     */
    public static String getCurrTime() {
        Date date_time = getCurrDateTime();
        return format(date_time, PATTERN_DATEMINUTE_12);
    }

    /**
     * 取得日期的年份
     *
     * @param date 日期
     * @return yyyy 年份字符串
     */
    public static String getYear(Date date) {
        return format(date, "yyyy");
    }

    /**
     * 取得日期的月份
     *
     * @param date 日期
     * @return mm 月份字符串
     */
    public static String getMonth(Date date) {
        return format(date, "MM");
    }

    /**
     * 取得日期的天份
     *
     * @param date 日期
     * @return dd 天字符串
     */
    public static String getDay(Date date) {
        return format(date, "dd");
    }

    /**
     * 取得日期的天份
     *
     * @param date 日期
     * @return dd 天字符串
     */
    public static String getHour(Date date) {
        return format(date, "HH");
    }

    /**
     * 取得日期的分钟
     *
     * @param date 时间
     * @return mm 分钟字符串
     */
    public static String getMinute(Date date) {
        return format(date, "mm");
    }

    /**
     * 取得时间的秒
     *
     * @param date 时间
     * @return ss 秒字符串
     */
    public static String getSecond(Date date) {
        return format(date, "ss");
    }

    //////////////////////////////////////////////////////////
    // 转换类函数 //
    //////////////////////////////////////////////////////////

    /**
     * 将时间日期字符串转换成日期字符串，转换格式为yyyy-MM-dd，小于10位的字符串将不做转换
     *
     * <pre>
     * DateUtils.toDate(null)                    ""
     * DateUtils.toDate("20140904")              "20140904"
     * DateUtils.toDate("2014-9-4")              "2014-9-4"
     * DateUtils.toDate("2014-09-04 12:59:59")   "2014-09-04"
     * </pre>
     *
     * @param datetime yyyy-MM-dd HH:mm:ss 格式的日期时间字符串
     * @return yyyy-MM-dd
     * @author 满志强
     */
    public static String extractDateString(String datetime) {
        if (StringUtils.isBlank(datetime)) {
            return "";
        } else if (datetime.length() <= 10) {
            return datetime;
        } else {
            return StringUtils.substring(datetime, 0, 10);
        }
    }

    /**
     * 字符串转换为日期
     *
     * @param dateString 日期格式字符串
     * @param sf         日期格式化定义
     * @return 转换后的日期
     */
    public static Date parseDate(String dateString, String sf) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(sf);
        return sdf.parse(dateString, pos);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatToSeason(Date date) {
        return StringUtils.join(getYear(date), "-", getSeason(date));
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 格式化时间戳
     *
     * @return
     */
    public static String format(Timestamp time, String pattern) {
        if (time == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(time);
    }

    /**
     * 字符串转换为时间戳
     *
     * @param dateString 日期格式字符串
     * @param pattern    日期格式化定义
     * @return 转换后的时间戳
     * @author 仝维
     * @date 2017年5月2日
     */
    public static Timestamp toTimestamp(String dateString, String pattern) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dt = sdf.parse(dateString, pos);
        return new Timestamp(dt.getTime());
    }

    /**
     * 支持常用格式日期的自动转换,本地语言为Locale.US<br />
     * 支持yyyyMMdd, yyyy-MM-dd, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd'T'HH:mm:ssZ, EEE MMM dd HH:mm:ss zzz yyyy, json标准格式
     *
     * @param date
     * @return
     * @author -
     */
    public static <T> Date autoParse(T date) {
        if (date == null) {
            return null;
        }
        if (date instanceof Long) {
            return new Date((Long) date);
        }
        try {
            if (date instanceof String) {
                String text = (String) date;
                if (!text.contains(",") && StringUtils.countMatches(text, " ") == 5) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                    return sdf.parse(text);
                }
                // yyyy-MM-dd HH:mm:ss
                String regExp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
                Pattern pattern = Pattern.compile(regExp);
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_DATETIME_19);
                }
                // yyyy-MM-dd HH:mm:ss.SSS
                regExp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{1,3}$";
                pattern = Pattern.compile(regExp);
                matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_DATETIME_19 + ".SSS");
                }
                // yyyyMMdd
                regExp = "^\\d{8}$";
                pattern = Pattern.compile(regExp);
                matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_DATE_8);
                }
                // yyyyMM
                regExp = "^\\d{6}$";
                pattern = Pattern.compile(regExp);
                matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_MONTHYEAR_6);
                }
                // yyyyMMddHHmmss
                regExp = "^\\d{14}$";
                pattern = Pattern.compile(regExp);
                matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_DATETIME_14);
                }
                // yyyy-MM-dd
                regExp = "^\\d{4}-\\d{2}-\\d{2}$";
                pattern = Pattern.compile(regExp);
                matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return parseDate(text, PATTERN_DATE_10);
                }
                StdDateFormat stdDateFormat = new StdDateFormat();
                stdDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));// 设置时区
                return stdDateFormat.parse(text);
            }
        } catch (ParseException e) {
            logger.error("error: ", e);
            throw new RuntimeException("时间格式转换异常：" + date, e);
        }
        throw new RuntimeException("无法识别的数据：" + date);
    }

    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * LocalDate转Date
     *
     * @param localDate
     * @return
     * @author 李丹
     */
    public static Date toDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * timestamp 转 date
     *
     * @param timestamp
     * @return
     * @author -
     */
    public static Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    /////////////////////////////////////////////////////////
    // 计算类函数 //
    /////////////////////////////////////////////////////////

    /**
     * 系统日期增加年
     *
     * @param date 日期，如果为空按照系统当前日期处理
     * @param year int年 支持负数，负数的时候为减
     */
    public static Date addDateWithYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, +year);
        date = calendar.getTime();
        return date;
    }

    /**
     * 系统日期增加季度
     *
     * @param date    日期，如果为空按照系统当前日期处理
     * @param seasons int季度 支持负数，负数的时候为减
     */
    public static Date addDateWithSeason(Date date, int seasons) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        return addDateWithMonth(date, seasons * 3);
    }

    /**
     * 系统日期增加月
     *
     * @param date  日期，如果为空按照系统当前日期处理
     * @param month int月 支持负数，负数的时候为减
     */
    public static Date addDateWithMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +month);
        date = calendar.getTime();
        return date;
    }

    /**
     * 系统日期增加日
     *
     * @param date 日期，如果为空按照系统当前日期处理
     * @param day  int日 支持负数，负数的时候为减
     */
    public static Date addDateWithDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, +day);
        date = calendar.getTime();
        return date;
    }

    /**
     * 系统日期增加分
     *
     * @param date    日期，如果为空按照系统当前日期处理
     * @param minutes minutes 分 支持负数，负数的时候为减
     */
    public static Date addDateWithMinute(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, +minutes);
        date = calendar.getTime();
        return date;
    }

    /**
     * 系统日期增加秒
     *
     * @param date    日期，如果为空按照系统当前日期处理
     * @param seconds int日 支持负数，负数的时候为减
     */
    public static Date addDateWithSecond(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, +seconds);
        date = calendar.getTime();
        return date;
    }

    public static Date getCurrDateTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 计算两个日期差（毫秒）date1 - date2
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差毫秒数
     */
    public static long diffTwoDate(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        return (l1 - l2);
    }

    /**
     * @param currentTime 计算的日期
     * @param type        偏移的类别
     * @param iQuantity   偏移数量
     * @return 偏移后的时间串
     */
    public static String getDateChangeTime(String currentTime, String type, int iQuantity) {
        boolean flag = false;
        if (currentTime.length() == 8) {
            currentTime += "000000";
        }
        Date curr = parseDate(currentTime, PATTERN_DATETIME_14);
        curr = getDateChangeTime(curr, type, iQuantity);
        if (flag) {
            return format(curr, PATTERN_DATE_8);
        } else {
            return format(curr, PATTERN_DATETIME_19);
        }
    }

    /**
     * @param currentTime 计算的日期
     * @param type        偏移的类别
     * @param iQuantity   偏移数量
     * @return 偏移后的时间
     */
    public static Date getDateChangeTime(Date currentTime, String type, int iQuantity) {
        int year = Integer.parseInt(format(currentTime, "yyyy"));
        int month = Integer.parseInt(format(currentTime, "MM"));
        // 月份修正,必须在日期变更前修改正月份，否则小月份都会产成问题
        month = month - 1;
        int day = Integer.parseInt(format(currentTime, "dd"));
        int hour = Integer.parseInt(format(currentTime, "HH"));
        int mi = Integer.parseInt(format(currentTime, "mm"));
        int ss = Integer.parseInt(format(currentTime, "ss"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, mi, ss);
        // 月份修正,必须在日期变更前修改正月份，否则小月份都会产成问题
        // gc.add(GregorianCalendar.MONTH, -1);
        if (type.equalsIgnoreCase("y")) {
            gc.add(GregorianCalendar.YEAR, iQuantity);
        } else if (type.equalsIgnoreCase("m")) {
            gc.add(GregorianCalendar.MONTH, iQuantity);
        } else if (type.equalsIgnoreCase("d")) {
            gc.add(GregorianCalendar.DATE, iQuantity);
        } else if (type.equalsIgnoreCase("h")) {
            gc.add(GregorianCalendar.HOUR, iQuantity);
        } else if (type.equalsIgnoreCase("mi")) {
            gc.add(GregorianCalendar.MINUTE, iQuantity);
        } else if (type.equalsIgnoreCase("s")) {
            gc.add(GregorianCalendar.SECOND, iQuantity);
        }
        return gc.getTime();
    }

    /**
     * 取月末时间
     *
     * @param date 日期
     * @return date
     */
    public static Date getMonthEnd(Date date) {
        int year = Integer.parseInt(format(date, "yyyy"));
        int month = Integer.parseInt(format(date, "MM"));
        int day = Integer.parseInt(format(date, "dd"));
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
        @SuppressWarnings("static-access")
        int monthLength = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String newDateStr = format(date, "yyyy") + format(date, "MM");
        if (monthLength < 10) {
            newDateStr += "0" + monthLength;
        } else {
            newDateStr += "" + monthLength;
        }
        return parseDate(newDateStr, PATTERN_DATE_8);
    }

    /**
     * 是否为旬末
     *
     * @param date 时间
     * @return 是或否
     */
    public static boolean IsXperiodEnd(Date date) {
        boolean flag = false;
        String day = getDay(date);
        // String month = getMonth(date);
        if (day.equalsIgnoreCase("10")) {
            flag = true;
        } else if (day.equalsIgnoreCase("20")) {
            flag = true;
        }
        // 月末不算旬末
        else if (getMonthEnd(date).compareTo(date) == 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 取月初
     *
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date) {
        String newDateStr = format(date, "yyyy-MM") + "-01";
        // formatDate(date, "yyyy-MM-dd");
        return parseDate(newDateStr, PATTERN_DATE_10);
    }

    /**
     * 取季度末时间
     *
     * @param date 日期
     * @return date
     */
    public static Date getSeasonEnd(Date date) {
        // int year = Integer.parseInt(formatDate(date, "yyyy"));
        int month = Integer.parseInt(format(date, "MM"));
        //
        String newDateStr = format(date, "yyyy");
        if (month >= 1 && month <= 3) {
            newDateStr += "0331";
        } else if (month >= 4 && month <= 6) {
            newDateStr += "0630";
        } else if (month >= 7 && month <= 9) {
            newDateStr += "0930";
        } else if (month >= 10 && month <= 12) {
            newDateStr += "1231";
        }
        return parseDate(newDateStr, PATTERN_DATE_8);
    }

    /**
     * 取季初
     *
     * @param date
     * @return
     */
    public static Date getSeasonBegin(Date date) {
        // int year = Integer.parseInt(formatDate(date, "yyyy"));
        int month = Integer.parseInt(format(date, "MM"));
        String newDateStr = format(date, "yyyy") + "-";
        if (month >= 1 && month <= 3) {
            newDateStr += "01-01";
        } else if (month >= 4 && month <= 6) {
            newDateStr += "04-01";
        } else if (month >= 7 && month <= 9) {
            newDateStr += "07-01";
        } else if (month >= 10 && month <= 12) {
            newDateStr += "10-01";
        }
        return parseDate(newDateStr, PATTERN_DATE_10);
    }

    /**
     * 取季度末时间
     *
     * @param year
     * @param season
     * @return date
     */
    public static Date getSeasonEnd(String year, int season) {
        String date = year;
        if (season == 1) {
            date += "0331";
        } else if (season == 2) {
            date += "0630";
        } else if (season == 3) {
            date += "0930";
        } else if (season == 4) {
            date += "1231";
        }
        return parseDate(date, PATTERN_DATE_8);
    }

    /**
     * 取季度初时间
     *
     * @param year
     * @param season
     * @return
     */
    public static Date getSeasonBegin(String year, int season) {
        String date = year;
        if (season == 1) {
            date += "0101";
        } else if (season == 2) {
            date += "0401";
        } else if (season == 3) {
            date += "0701";
        } else if (season == 4) {
            date += "1001";
        }
        return parseDate(date, PATTERN_DATE_8);
    }

    /**
     * 是否为半年末
     *
     * @param date 时间
     * @return
     */
    public static Date getHalfYearEnd(Date date) {
        int month = Integer.parseInt(format(date, "MM"));
        String newDateStr = format(date, "yyyy");
        if (month >= 1 && month <= 6) {
            newDateStr += "0630";
        } else if (month >= 7 && month <= 12) {
            newDateStr += "1231";
        }
        return parseDate(newDateStr, PATTERN_DATE_8);
    }

    /**
     * 是否为年末
     *
     * @param date 时间
     * @return
     */
    public static Date getYearEnd(Date date) {
        String newDateStr = format(date, "yyyy") + "1231";
        return parseDate(newDateStr, PATTERN_DATE_8);
    }

    /**
     * 取得年初
     *
     * @param date
     * @return
     */
    public static Date getYearBegin(Date date) {
        String newDateStr = format(date, "yyyy") + "-01-01";
        return parseDate(newDateStr, PATTERN_DATE_10);
    }

    public static Date getYearBegin(int year) {
        String newDateStr = String.valueOf(year) + "-01-01";
        return parseDate(newDateStr, PATTERN_DATE_10);
    }

    /**
     * 取得日以上粒度起始时间
     *
     * @param ga            粒度
     * @param statisticDate 结束时间
     * @return 起始时间
     */
    public static String getBeginDate(EGranularity ga, String statisticDate) {
        Date date = parseDate(statisticDate, PATTERN_DATE_8);
        Date beginDateTemp = null;
        switch (ga) {
            case Day:
                break;
            case Month:
                beginDateTemp = getMonthBegin(date);
                break;
            case Season:
                beginDateTemp = getSeasonBegin(date);
                break;
            case HalfYear:
                break;
            case Year:
                beginDateTemp = getYearBegin(date);
                break;
            default:
                break;
        }
        return format(beginDateTemp, PATTERN_DATE_8);
    }

    /**
     * 取得粒度结束时间
     *
     * @param ga            粒度
     * @param statisticDate 结束时间
     * @return 起始时间
     */
    public static String getEndDate(EGranularity ga, String statisticDate) {
        Date date = parseDate(statisticDate, PATTERN_DATE_8);
        Date beginDateTemp = null;
        switch (ga) {
            case Day:
                beginDateTemp = date;
                break;
            case Month:
                beginDateTemp = getMonthEnd(date);
                break;
            case Season:
                beginDateTemp = getSeasonEnd(date);
                break;
            case HalfYear:
                beginDateTemp = getHalfYearEnd(date);
                break;
            case Year:
                beginDateTemp = getYearEnd(date);
                break;
            default:
                break;
        }
        return format(beginDateTemp, PATTERN_DATE_8);
    }

    /**
     * 计算两个日期差（天） 时间1 - 时间2的結果 <br />
     * 为避免零头带来的误差，统一抹掉时分秒后再计算
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差天数
     */
    public static int diffTwoDateDay(Date date1, Date date2) {
        Assert.notNull(date1, "日期不能为空");
        Assert.notNull(date2, "日期不能为空");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        long l1 = c1.getTimeInMillis() / 3600 / 24 / 1000;
        long l2 = c2.getTimeInMillis() / 3600 / 24 / 1000;
        long diff = l1 - l2;
        return new Long(diff).intValue();
    }

    /**
     * 计算两个日期差（天） 时间1 - 时间2的結果 <br />
     * 带时分秒计算误差，注意误差
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差天数
     */
    public static BigDecimal diffTwoDateDayWithTime(Date date1, Date date2) {
        Assert.notNull(date1, "日期不能为空");
        Assert.notNull(date2, "日期不能为空");
        return (new BigDecimal(date1.getTime()).subtract(new BigDecimal(date2.getTime())))
                .divide(new BigDecimal(24 * 3600 * 1000), 8, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 根据当前时间，得到之前或者之后的日期
     */
    public static Date getIncDateTime(Date d, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 获取两个Timestamp时间相差天数
     *
     * @param nowSt
     * @param countSt
     * @return int 相差天数
     * @author 李丹
     * @date 2017年3月9日
     */
    public static int getPhaseDayByTime(Timestamp nowSt, Timestamp countSt) {
        Double countDay = new Long(nowSt.getTime() - countSt.getTime()).doubleValue() / (1000 * 60 * 60 * 24);
        if (countDay > 0) {
            return new Double(Math.ceil(countDay)).intValue();
        }
        return 0;
    }

    /**
     * 将两个Timestamp时间转换为(yyyy-MM-dd)计算相差天数
     *
     * @param nowSt
     * @param countSt
     * @return int 相差天数
     * @author 李丹
     * @date 2017年3月9日
     */
    public static int getPhaseDayByDate(Timestamp nowSt, Timestamp countSt) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_10);
        String now = sdf.format(nowSt);
        String count = sdf.format(countSt);
        Double countDay;
        try {
            countDay = new Long(diffTwoDate(sdf.parse(now), sdf.parse(count))).doubleValue() / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            logger.error("parse exception: ", e);
            countDay = new Long(nowSt.getTime() - countSt.getTime()).doubleValue() / (1000 * 60 * 60 * 24);
        }
        if (countDay > 0) {
            return new Double(Math.ceil(countDay)).intValue();
        }
        return 0;
    }

    /**
     * 清除输入日期的时分秒，如：2017-07-31 12:05:12 -> 2017-07-31 00:00:00
     *
     * @param value
     * @author 张耀武
     */
    public static Timestamp startOfDay(Date value) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
        Instant instant = localDateTime.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 获取日期的下一个日期凌晨，如：2017-07-31 12:05:12 -> 2017-08-01 00:00:00
     *
     * @author 张耀武
     */
    public static Timestamp startOfNextDay(Date value) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
        Instant instant =
                localDateTime.toLocalDate().atStartOfDay().plusDays(1).atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 获取日期的下n个日期凌晨，如：2017-07-31 12:05:12 -> 2017-08-01 00:00:00
     *
     * @author 张耀武
     */
    public static Timestamp startOfNextNDay(Date value, int n) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
        Instant instant =
                localDateTime.toLocalDate().atStartOfDay().plusDays(n).atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 获取日期的上一个日期凌晨，如：2017-07-31 12:05:12 -> 2017-07-30 00:00:00
     *
     * @author 张耀武
     */
    public static Timestamp startOfPreviousDay(Date value) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
        Instant instant =
                localDateTime.toLocalDate().atStartOfDay().minusDays(1).atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 获取日期的上n个日期凌晨，如：2017-07-31 12:05:12 -> 2017-07-30 00:00:00
     *
     * @author 张耀武
     */
    public static Timestamp startOfPreviousNDay(Date value, int n) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
        Instant instant =
                localDateTime.toLocalDate().atStartOfDay().minusDays(n).atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 获取当前日期是年中第几周，一年的第一周为1
     *
     * @param date
     * @return
     * @author -
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 计算某年某周的第一天即周一
     *
     * @param -
     * @param year
     * @param week
     * @return
     */
    public static Date getYearWeekFirstDay(final int year, final int week) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 计算某年某个周的最后一天即周日
     *
     * @param -
     * @param year
     * @param week
     * @return
     */
    public static Date getYearWeekEndDay(int year, int week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 判断一个字符串是不是指定的日期格式
     *
     * @param dateString 日期字符串
     * @param dateFormat 指定的日期格式
     * @return
     * @author: 张南南
     */
    public static boolean isLegalDateFormat(String dateString, String dateFormat) {
        boolean flag = true;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            format.setLenient(false);
            format.parse(dateString);
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 日期格式转换
     *
     * @param date          日期字符串
     * @param sourcePattern 原日期格式
     * @param destPattern   目标日期格式
     * @return
     * @author 仝维
     * @date 2019-10-10
     */
    public static String covertDatePattern(String date, String sourcePattern, String destPattern) {
        try {
            Date dateSourcePattern = toTimestamp(date, sourcePattern);
            return format(dateSourcePattern, destPattern);
        } catch (Exception e) {
            logger.error("日期格式转换错误,日期{};原格式{};目标格式:{}", date, sourcePattern, destPattern);
        }
        return null;
    }


    /**
     * 获取日期的中文时间
     *
     * @param date 日期
     * @return 中文时间(例 二零一九年十月十七日)
     */
    public static String getCNDate(Date date) {
        String year = getYear(date); // 得到年
        String month = format(date, "M"); // 得到月，因为从0开始的，所以要加1
        String day = format(date, "d"); // 得到天

        StringBuilder cnDate = new StringBuilder();
        for (int i = 0; i < year.length(); i++) {
            cnDate.append(NUM_ARRAY[Integer.valueOf(String.valueOf(year.charAt(i)))]);
        }

        cnDate.append("年");

        if (month.length() == 2) {
            Integer value = Integer.valueOf(String.valueOf(month.charAt(0)));
            if (!value.equals(1)) {
                cnDate.append(NUM_ARRAY[value]);
            }
            cnDate.append("十");
            if (!String.valueOf(month.charAt(1)).equals("0")) {
                cnDate.append(NUM_ARRAY[Integer.valueOf(String.valueOf(month.charAt(1)))]);
            }
        } else {
            cnDate.append(NUM_ARRAY[Integer.valueOf(String.valueOf(month.charAt(0)))]);
        }

        cnDate.append("月");

        if (day.length() == 2) {
            Integer value = Integer.valueOf(String.valueOf(day.charAt(0)));
            if (!value.equals(1)) {
                cnDate.append(NUM_ARRAY[value]);
            }
            cnDate.append("十");
            if (!String.valueOf(day.charAt(1)).equals("0")) {
                cnDate.append(NUM_ARRAY[Integer.valueOf(String.valueOf(day.charAt(1)))]);
            }
        } else {
            cnDate.append(NUM_ARRAY[Integer.valueOf(String.valueOf(day.charAt(0)))]);
        }

        cnDate.append("日");
        return cnDate.toString();
    }

    public static Boolean inSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Long begin = startOfDay(date1).getTime();
        Long end = startOfNextDay(date1).getTime();
        return (date2.getTime() >= begin && date2.getTime() < end);
    }

    /**
     * 时间间隔格式化
     * xxx（秒数） -> [xx]h[yy]m[zz]s
     *
     * @param secondsValue
     * @return
     */
    public static String regulateTimeDuration(BigInteger secondsValue) {
        if (Objects.isNull(secondsValue) || secondsValue.compareTo(BigInteger.ZERO) < 0) {
            return "";
        }
        Integer seconds = secondsValue.intValue();
        if (seconds < 60) {
            return seconds + "s";
        } else if (seconds > 60 && seconds < 3600) {
            int m = seconds / 60;
            int s = seconds % 60;
            return m + "m" + s + "s";
        } else {
            int h = seconds / 3600;
            int m = (seconds % 3600) / 60;
            int s = (seconds % 3600) % 60;
            return h + "h" + m + "m" + s + "s";
        }

    }
}
