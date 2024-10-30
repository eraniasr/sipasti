package com.sipasti.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Timestamp implements Comparable<Timestamp> {

    private LocalDate tanggal;
    private LocalTime jam;
    private LocalDateTime waktu;
    private DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH.mm");

    // DATE_TYPE dibuat karena ada format tanggal yang dd/mm/yyyy, ada yang mm/dd/yyyy
    protected enum DATE_TYPE {
        DMY, MDY
    }

    public Timestamp(Object date, Object time, DATE_TYPE dateType){
        if (date != null){
            String[] dateStr = date.toString().split("/");
            if (dateStr.length == 3){
                switch (dateType){
                    case DMY:
                        tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
                    case MDY:
                        tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]));
                }
                jam = LocalTime.parse(time.toString(), tf);
                waktu = jam.atDate(tanggal);
            }
        }
    }

    public Timestamp(Object date, DATE_TYPE dateType){
        if (date != null) {
            String[] dateStr = date.toString().split("/");
            if (dateStr.length == 3){
                switch (dateType){
                    case DMY:
                        tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
                    case MDY:
                        tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]));
                }
                waktu = LocalTime.MIN.atDate(tanggal);
            }
        }
    }

    // Untuk sort timestamp, jadi bikin timelinenya berurutan
    @Override
    public int compareTo(Timestamp ts) {
        return waktu.compareTo(ts.getWaktu());
    }

    // Hitung durasi antara 2 timestamp, return string "* hari * jam * menit"
    public String durasi(Timestamp selesai){
        /// https://stackoverflow.com/questions/25747499/java-8-difference-between-two-localdatetime-in-multiple-units

        if (selesai.getWaktu() == null || waktu == null){
            return "-";
        }

        LocalDateTime tempDateTime = LocalDateTime.from(waktu);

        long hari = tempDateTime.until(selesai.getWaktu(), ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(hari);

        long jam = tempDateTime.until(selesai.getWaktu(), ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusDays(jam);

        long menit = tempDateTime.until(selesai.getWaktu(), ChronoUnit.MINUTES);

        return (hari + " hari");
        //return (hari + " hari, " + jam + " jam, " + menit + " menit");
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public String getWaktuFormatted(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id"));
        if (waktu == null) return "";
        return dtf.format(waktu);
    }
}
