/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import IO.FileIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rahmatridhams
 */
public class Applications {

    DaftarPegawai daftarPeg;
    ParkiranMobil parkirMobil;
    ParkiranMotor parkirMotor;
    TarifMobil tarifMobil;
    TarifMotor tarifMotor;
    Pegawai pegShift;
    Pegawai peg;
    KendaraanMobil kmob;
    KendaraanMotor kmot;
    int biaya;
    String historyParkir="", pegawaihist="";
    Kendaraan k;
    private FileIO save;

    public Applications() {
        this.save = new FileIO();
        this.daftarPeg = new DaftarPegawai();
        this.parkirMobil = new ParkiranMobil("parkirCar", 10);
        this.parkirMotor = new ParkiranMotor("parkirMtr", 5);
        Pegawai p = new Pegawai("aulia", "aulia", "123");
        daftarPeg.addPegawai(p);
        this.pegawaihist = "\n"+p.toString();

        this.tarifMobil = new TarifMobil();
        tarifMobil.setJamAwal(250);
        tarifMobil.setJamBerikut(200);
        tarifMobil.setDenda(500);

        this.tarifMotor = new TarifMotor();
        tarifMotor.setJamAwal(150);
        tarifMotor.setJamBerikut(100);
        tarifMotor.setDenda(500);
    }

    public void addPegawai(String name, String usrname, String pass, String passcon) {
        if (pass.equals(passcon)) {

            peg = new Pegawai(name, usrname, passcon);
            System.out.println("objek terbuat");
            daftarPeg.addPegawai(peg);
            System.out.println("objek nambah");
            this.pegawaihist += "\n" + peg.toString();

        } else {
            System.out.println("Password not match");
        }
    }

    public void addKendaraans(int index, String platnomors) {
        switch (index) {
            case 1:
                k = parkirMobil.findKendaraan(platnomors);
                if (k == null) {
                    kmob = new KendaraanMobil(platnomors);
                    this.parkirMobil.addKendaraan(kmob);
                } else {
                    System.out.println("mobil lagi di parkiran");
                }
                break;
            case 2:
                k = parkirMotor.findKendaraan(platnomors);
                System.out.println(k);
                if (parkirMotor.KendaraanP.size() <= parkirMotor.getKapasitas()) {
                    if (k == null) {
                        parkirMotor.findKendaraan(platnomors);
                        kmot = new KendaraanMotor(platnomors);
                        this.parkirMotor.addKendaraan(kmot);
                        System.out.println("parkir remaining" + (parkirMotor.KendaraanP.size() - parkirMotor.getKapasitas()));
                    } else {
                        System.out.println("parkiran penuh");
                    }
                } else {
                    System.out.println("motor lagi di parkiran");
                }
                break;
        }
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public void removeKendaraans(int pil, String platnomors, Boolean isSelectedDenda) {
//        if (parkirMobil.KendaraanP.isEmpty() || parkirMotor.KendaraanP.isEmpty()) {
//                System.out.println("Parkiran Kosong");
//            } else {
        if (pil == 2) {
            System.out.println("parkir remaining" + (parkirMotor.KendaraanP.size() - parkirMotor.getKapasitas()));
            k = (KendaraanMotor) parkirMotor.findKendaraan(platnomors);
            if (k == null) {
                System.out.println("Kendaraan belum ada");
            } else {
                if (isSelectedDenda) {
                    k.denda();
                    this.biaya = k.getBiaya(tarifMotor);
                    parkirMotor.removeKendaraan(k);
                    this.historyParkir += "\n" + k.toString()+" Biaya : "+ k.getBiaya(tarifMotor) + " Pegawai : " + pegShift.getNamaPegawai();
                } else {
                    this.biaya = k.getBiaya(tarifMotor);
                    parkirMotor.removeKendaraan(k);
                    this.historyParkir += "\n" + k.toString()+" Biaya : "+ k.getBiaya(tarifMotor) + " Pegawai : " + pegShift.getNamaPegawai();
                }
            }

        } else if (pil == 1) {
            KendaraanMobil temp = (KendaraanMobil) parkirMobil.findKendaraan(platnomors);
            if (temp == null) {
                System.out.println("Kendaraan belum ada");
            } else {
                if (isSelectedDenda) {
                    temp.denda();
                    this.biaya = temp.getBiaya(tarifMobil);
                    System.out.println(biaya);
                    parkirMotor.removeKendaraan(temp);
                    this.historyParkir += "\n" + temp.toString()+" Biaya : "+ temp.getBiaya(tarifMobil) + " Pegawai : " + pegShift.getNamaPegawai();
                } else {
                    this.biaya = temp.getBiaya(tarifMobil);
                    System.out.println(biaya);
                    parkirMobil.removeKendaraan(temp);
                    this.historyParkir += "\n" + temp.toString()+" Biaya : "+ temp.getBiaya(tarifMobil) + " Pegawai : " + pegShift.getNamaPegawai();
                }
            }
        } else {

            System.out.println("kendaraan errror");
        }

    }

    public void saveRekap() throws FileNotFoundException, IOException {
        try {
            save.saveObject(historyParkir, "RekapKendaraan.txt");
        } catch (FileNotFoundException ex) {
            File f = new File("employee.txt");
            f.createNewFile();
            f = new File("log.txt");
            f.createNewFile();
        } catch (IOException ex) {
            throw new IOException("error " + ex.getMessage());
        }
    }

    public DaftarPegawai getDaftarPeg() {
        return daftarPeg;
    }

    public ParkiranMobil getParkirMobil() {
        return parkirMobil;
    }

    public ParkiranMotor getParkirMotor() {
        return parkirMotor;
    }

    public TarifMobil getTarifMobil() {
        return tarifMobil;
    }

    public TarifMotor getTarifMotor() {
        return tarifMotor;
    }

    public Pegawai getPegShift() {
        return pegShift;
    }

    public void setPegShift(Pegawai pegShift) {
        this.pegShift = pegShift;
    }

    public Pegawai getPeg() {
        return peg;
    }

    public void setPegawaihist(String pegawaihist) {
        this.pegawaihist = pegawaihist;
    }

    public KendaraanMobil getKmob() {
        return kmob;
    }

    public KendaraanMotor getKmot() {
        return kmot;
    }

    public String getPegawaihist() {
        return pegawaihist;
    }

    public int getBiaya() {
        return biaya;
    }

    public String getHistoryParkir() {
        return historyParkir;
    }

    public ArrayList<Pegawai> getDaftarPegawai() {
        return daftarPeg.getDaftarP();
    }

}
