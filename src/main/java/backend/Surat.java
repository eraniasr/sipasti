package backend;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Surat {
    private SimpleStringProperty tanggalPengajuan, jamPengajuan, unitPengaju, nomorSurat, perihal, statusDisposisi,
            pelaksanaTugas, statusPengajuan, permasalahan, statusPembayaran;
    private String dekan, wadek, kabag, jft;
    private Integer nomor;
    private List<Object> timestamps;

    public Surat(List<Object> surat, int index){
        try{
            nomor = index;
            tanggalPengajuan = new SimpleStringProperty((String) surat.get(0));
            jamPengajuan = new SimpleStringProperty((String) surat.get(1));
            unitPengaju = new SimpleStringProperty((String) surat.get(4));
            nomorSurat = new SimpleStringProperty((String) surat.get(6));
            perihal = new SimpleStringProperty((String) surat.get(8));
            pelaksanaTugas = new SimpleStringProperty((String) surat.get(38));
            statusDisposisi = new SimpleStringProperty((String) surat.get(39));
            statusPengajuan = new SimpleStringProperty((String) surat.get(42));
            permasalahan = new SimpleStringProperty((String) surat.get(43));
            statusPembayaran = new SimpleStringProperty((String) surat.get(87));
            // blm kepikiran gimana baiknya bikin timestamp untuk timeline nanti
            dekan = (String) surat.get(15);
            wadek = (String) surat.get(18);
            kabag = (String) surat.get(30);
            jft = (String) surat.get(68);
            //timestamps = surat;
        }

        // Catch seandainya error out of bound karena data tidak lengkap
        catch (IndexOutOfBoundsException e){
            //e.printStackTrace();
        }
    }

    public String[] createTimeline(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id"));
        Timestamp kabag = null, dekan = null, wadek = null, jft = null;
        System.out.println(dekan);
        System.out.println(wadek);
        System.out.println(kabag);
        System.out.println(jft);
        System.out.println("done");
        if(timestamps.get(15) != ""){
            dekan = new Timestamp("Disposisi Dekan", timestamps.get(15), Timestamp.DATE_TYPE.MDY);
        }
        if(timestamps.get(18) != ""){
            wadek = new Timestamp("Disposisi Wadek", timestamps.get(18), Timestamp.DATE_TYPE.MDY);
        }
        if(!Objects.equals((String) timestamps.get(30), "")){
            System.out.println(timestamps.get(30));
            System.out.println();
            System.out.println("eror kabag");
            kabag = new Timestamp("Disposisi Kabag", timestamps.get(30), Timestamp.DATE_TYPE.MDY);
        }
        if(timestamps.get(68) != ""){
            jft = new Timestamp("Verifikator JFT Keuangan", timestamps.get(68), Timestamp.DATE_TYPE.MDY);
        }
        return new String[]{dtf.format(dekan.getWaktu()), dtf.format(wadek.getWaktu()), dtf.format(kabag.getWaktu()), dtf.format(jft.getWaktu())};
    }

    // Set up untuk bagian tabel
    public StringProperty getNomor() {
        return new SimpleStringProperty(String.valueOf(nomor));
    }

    public String getDekan() {
        return dekan;
    }

    public StringProperty getUnitPengaju(){
        return unitPengaju;
    }

    public StringProperty getNomorSurat() {
        return nomorSurat;
    }

    public StringProperty getPelaksanaTugas() {
        return pelaksanaTugas;
    }

    public StringProperty getPerihal() {
        return perihal;
    }

    public StringProperty getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public StringProperty getJamPengajuan() {
        return jamPengajuan;
    }

    public StringProperty getPermasalahan() {
        return permasalahan;
    }

    public StringProperty getStatusDisposisi() {
        return statusDisposisi;
    }

    public StringProperty getStatusPembayaran() {
        return statusPembayaran;
    }

    public StringProperty getStatusPengajuan() {
        return statusPengajuan;
    }

//    public List<Timestamp> verifKabag(){
//        List<Timestamp> kabag = new ArrayList<>();
//        Timestamp terimaKabag = null;
//        Timestamp keluarKabag = null;
//
//        if(timestamps.get(46) != ""){
//            terimaKabag = new Timestamp("Diterima Kabag", timestamps.get(46),timestamps.get(47), Timestamp.DATE_TYPE.MDY);
//            keluarKabag = new Timestamp("Diterima Kabag", timestamps.get(48),timestamps.get(49), Timestamp.DATE_TYPE.MDY);
//        }
//
//        kabag.add(terimaKabag);
//        kabag.add(keluarKabag);
//
//        return kabag;
//    }
//
//    public List<Timestamp> verifWadek(){
//        List<Timestamp> wadek = new ArrayList<>();
//        Timestamp terimaWadek = null;
//        Timestamp keluarWadek = null;
//
//        if(timestamps.get(46) != ""){
//            terimaWadek = new Timestamp("Diterima Kabag", timestamps.get(46),timestamps.get(47), Timestamp.DATE_TYPE.MDY);
//            keluarWadek = new Timestamp("Diterima Kabag", timestamps.get(48),timestamps.get(49), Timestamp.DATE_TYPE.MDY);
//        }
//
//        wadek.add(terimaWadek);
//        wadek.add(keluarWadek);
//
//        return wadek;
//    }
}
