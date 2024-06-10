package DAO;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class chuyenNgay {
    public static String chuyenSangVietTat(String thuTiengAnh) {
        switch (thuTiengAnh.toLowerCase()) { // Chuyển về chữ thường để không phân biệt hoa thường
            case "monday":
                return "T2";
            case "tuesday":
                return "T3";
            case "wednesday":
                return "T4";
            case "thursday":
                return "T5";
            case "friday":
                return "T6";
            case "saturday":
                return "T7";
            case "sunday":
                return "CN";
            default:
                return "Không hợp lệ"; // Xử lý trường hợp nhập sai
        }
    }

    public static String getThu(int ngay, int thang, int nam) {
        LocalDate localDate = LocalDate.of(nam, thang, ngay);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return chuyenSangVietTat(String.valueOf(dayOfWeek));
    }

    public static Integer getDays(int thang, int nam) {
        YearMonth yearMonthObject = YearMonth.of(nam, thang);
        return yearMonthObject.lengthOfMonth();
    }

    public static void main(String[] args) {
        System.out.println(chuyenNgay.getThu(14, 5, 2003));
        System.out.println(chuyenNgay.getDays(6, 2024));
        Date curDate = new Date();
        LocalDate localDate = curDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();
        System.out.println(day);
    }
}
