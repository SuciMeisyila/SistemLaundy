package com.mycompany.sistemlaundry;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

   public void cariLaundry(String nama) throws DataTidakValidException {
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

    public ArrayList<Laundry> getDaftarLaundry() {
        return daftarLaundry;
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

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_laundry.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(this.daftarLaundry);
            mesinPembeku.writeObject(this.dataPelanggan);

            mesinPembeku.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan (Serialization) ke dalam Harddisk!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_laundry.dat");
            if (!cekFile.exists()) {
                System.out.println("-> [SYSTEM INFO] File data_laundry.dat belum ditemukan (sesi baru).");
                return;
            }

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);

            this.daftarLaundry = (ArrayList<Laundry>) mesinPembangkit.readObject();
            this.dataPelanggan = (HashMap<String, Pelanggan>) mesinPembangkit.readObject();

            Laundry.totalLaundryBerhasilDibuat = this.daftarLaundry.size();

            mesinPembangkit.close();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang dari penyimpanan (" + daftarLaundry.size() + " data laundry).");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        System.out.println("\n--- Riwayat Log Transaksi ---");
        try {
            File fileLog = new File("log_transaksi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada log transaksi laundry.");
                return;
            }
            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log.");
        }
    }
}