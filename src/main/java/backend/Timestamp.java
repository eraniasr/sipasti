package backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Timestamp implements Comparable<Timestamp> {

    private String event;
    private LocalDate tanggal;
    private LocalTime jam;
    private LocalDateTime waktu;
    private DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH.mm");

    // DATE_TYPE dibuat karena ada format tanggal yang dd/mm/yyyy, ada yang mm/dd/yyyy
    protected enum DATE_TYPE {
        DMY, MDY
    }

    public Timestamp(String ev, Object date, Object time, DATE_TYPE dateType){
        event = ev;
        String[] dateStr = date.toString().split("/");
        switch (dateType){
            case DMY:
                tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
            case MDY:
                tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]));
        }
        jam = LocalTime.parse(time.toString(), tf);
        waktu = jam.atDate(tanggal);
    }

    public Timestamp(String ev, Object date, DATE_TYPE dateType){
        event = ev;
        String[] dateStr = date.toString().split("/");
        switch (dateType){
            case DMY:
                tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
            case MDY:
                tanggal = LocalDate.of(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]));
        }
    }

    // Untuk sort timestamp, jadi bikin timelinenya berurutan
    @Override
    public int compareTo(Timestamp ts) {
        return waktu.compareTo(ts.getWaktu());
    }

    // Hitung durasi antara 2 timestamp, return string "* hari * jam * menit"
    public String durasi(Timestamp mulai, Timestamp selesai){
        /// https://stackoverflow.com/questions/25747499/java-8-difference-between-two-localdatetime-in-multiple-units
        LocalDateTime tempDateTime = LocalDateTime.from(mulai.getWaktu());

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

    public String getEvent() {
        return event;
    }
}
