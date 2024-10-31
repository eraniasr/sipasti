package com.sipasti.backend;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.List;

public class Surat {
    private SimpleStringProperty tanggalPengajuan, jamPengajuan, unitPengaju, nomorSurat, perihal, statusDisposisi,
            pelaksanaTugas, statusPengajuan, permasalahan, statusPembayaran;
    private String dekan, wadek, kabag, jftKeuangan, jftPengadaan;
    private Integer nomor;

    public Surat(List<Object> surat, int index){
        try{
            nomor = index;
            tanggalPengajuan = new SimpleStringProperty((String) surat.get(0));
            jamPengajuan = new SimpleStringProperty((String) surat.get(1));
            unitPengaju = new SimpleStringProperty((String) surat.get(4));
            nomorSurat = new SimpleStringProperty((String) surat.get(6));
            perihal = new SimpleStringProperty((String) surat.get(8));
            dekan = (String) surat.get(15);
            wadek = (String) surat.get(18);
            kabag = (String) surat.get(30);
            pelaksanaTugas = new SimpleStringProperty((String) surat.get(38));
            statusDisposisi = new SimpleStringProperty((String) surat.get(39));
            statusPengajuan = new SimpleStringProperty((String) surat.get(42));
            permasalahan = new SimpleStringProperty((String) surat.get(43));
            jftKeuangan = (String) surat.get(68);
            jftPengadaan = (String) surat.get(72);
            statusPembayaran = new SimpleStringProperty((String) surat.get(87));
        }

        // Catch seandainya error out of bound karena data tidak lengkap
        catch (IndexOutOfBoundsException e){
            //e.printStackTrace();
        }
    }

    public String[] createTimeline(){
        Timestamp dekanTimestamp, wadekTimestamp, kabagTimestamp, jftKeuanganTimestamp, jftPengadaanTimestamp;
        String durasiWadek, durasiKabag, durasiJftKeuangan, durasiJftPengadaan;

        dekanTimestamp = new Timestamp(dekan, Timestamp.DATE_TYPE.MDY);
        wadekTimestamp = new Timestamp(wadek, Timestamp.DATE_TYPE.MDY);
        kabagTimestamp = new Timestamp(kabag, Timestamp.DATE_TYPE.MDY);
        jftKeuanganTimestamp = new Timestamp(jftKeuangan, Timestamp.DATE_TYPE.MDY);
        jftPengadaanTimestamp = new Timestamp(jftPengadaan, Timestamp.DATE_TYPE.MDY);

        durasiWadek = dekanTimestamp.durasi(wadekTimestamp);
        durasiKabag = wadekTimestamp.durasi(kabagTimestamp);
        durasiJftKeuangan = kabagTimestamp.durasi(jftKeuanganTimestamp);
        durasiJftPengadaan = jftKeuanganTimestamp.durasi(jftPengadaanTimestamp);

        return new String[]{dekanTimestamp.getWaktuFormatted(), wadekTimestamp.getWaktuFormatted(),
                kabagTimestamp.getWaktuFormatted(), jftKeuanganTimestamp.getWaktuFormatted(),
                jftPengadaanTimestamp.getWaktuFormatted(),
                durasiWadek, durasiKabag, durasiJftKeuangan, durasiJftPengadaan};
    }

    // Set up untuk bagian tabel
    public StringProperty getNomor() {
        return new SimpleStringProperty(String.valueOf(nomor));
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
}
