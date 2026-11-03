package com.mycompany.sistemlaundry;

import java.util.ArrayList;
import java.util.HashMap;

public class ManajemenOutletLaundry {
    private String namaOutlet;
    private RuangLaundry ruangCuciUtama;
    private ArrayList<Laundry> daftarLaundry;
    private HashMap<String, Pelanggan> dataPelanggan;

    public ManajemenOutletLaundry(String namaOutlet) {
        this.namaOutlet = namaOutlet;
        this.ruangCuciUtama = new RuangLaundry("Ruang Cuci Utama", 50);
        this.daftarLaundry = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaOutlet() {
        System.out.println("\n... Menginisialisasi Sistem " + this.namaOutlet + " ...");
        ruangCuciUtama.bukaRuangan();
    }

    public void tambahLaundry(Laundry l) {
        daftarLaundry.add(l);
        System.out.println("Sukses! Data laundry ditambahkan. (Total Laundry: " + daftarLaundry.size() + ")");
    }

    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getIdPelanggan(), p);
        System.out.println("Berhasil menambahkan pelanggan!");
    }

    public void lihatSemuaLaundry() {
        if (daftarLaundry.isEmpty()) {
            System.out.println("Belum ada data laundry yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarLaundry.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarLaundry.get(i).tampilkanInfo();
        }
    }
    
    public static void cariLaundry(String nama, ArrayList<Laundry> daftarLaundry) throws DataTidakValidException {
        if (nama == null || nama.trim().isEmpty()) {
            throw new DataTidakValidException("Kata kunci pencarian nama tidak boleh kosong.");
        }
        System.out.println("Mencari laundry dengan Nama Pelanggan: " + nama);
        boolean ditemukan = false;
        for (int i = 0; i < daftarLaundry.size(); i++) {
            if (daftarLaundry.get(i).getNamaPelanggan().equalsIgnoreCase(nama)) {
                daftarLaundry.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("-> [INFO] Data laundry tidak ditemukan.");
        }
    }

    public void cariPelanggan(String idPelanggan) {
        if (dataPelanggan.containsKey(idPelanggan)) {
            System.out.println("Ditemukan! Nama: " + dataPelanggan.get(idPelanggan).getNama());
        } else {
            System.out.println("Pelanggan dengan ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- ID: " + p.getIdPelanggan() + " | Nama: " + p.getNama() + " | Telp: " + p.getNoTelepon());
        }
    }

    public Pelanggan getPelanggan(String idPelanggan) {
        return dataPelanggan.get(idPelanggan);
    }

    public Laundry getLaundryInt(int index) {
        return daftarLaundry.get(index);
    }

    public int getJumlahLaundry() {
        return daftarLaundry.size();
    }

    void cariLaundry(String kataKunci) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}