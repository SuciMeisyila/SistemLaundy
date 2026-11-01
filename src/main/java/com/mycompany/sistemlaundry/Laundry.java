/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public abstract class Laundry {
    private String namaPelanggan;
    private String noTelepon;
    private double beratKg;
    private String status;

    private static int totalLaundryBerhasilDibuat = 0;

    public Laundry(String namaPelanggan, String noTelepon, double beratKg, String status) throws DataTidakValidException {
        setNamaPelanggan(namaPelanggan);
        setNoTelepon(noTelepon);
        setBeratKg(beratKg);
        setStatus(status);
        totalLaundryBerhasilDibuat++;
    }

    public String getNamaPelanggan() {
        return this.namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) throws DataTidakValidException {
        if (namaPelanggan != null && !namaPelanggan.trim().isEmpty()) {
            this.namaPelanggan = namaPelanggan.trim();
        } else {
            throw new DataTidakValidException("Nama pelanggan tidak boleh kosong.");
        }
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) throws DataTidakValidException {
        if (noTelepon != null && noTelepon.matches("\\d{10,13}")) {
            this.noTelepon = noTelepon;
        } else {
            throw new DataTidakValidException("Nomor telepon harus 10-13 digit angka.");
        }
    }

    public double getBeratKg() {
        return this.beratKg;
    }

    public void setBeratKg(double beratKg) throws DataTidakValidException {
        if (beratKg >= 0) {
            this.beratKg = beratKg;
        } else {
            throw new DataTidakValidException("Berat laundry tidak boleh bernilai negatif.");
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) throws DataTidakValidException {
        if (status != null && (status.equalsIgnoreCase("Diproses")
                || status.equalsIgnoreCase("Selesai")
                || status.equalsIgnoreCase("Diambil"))) {
            this.status = normalisasiStatus(status);
        } else {
            throw new DataTidakValidException("Status hanya boleh: Diproses, Selesai, atau Diambil.");
        }
    }

    private String normalisasiStatus(String status) {
        if (status.equalsIgnoreCase("Diproses")) return "Diproses";
        if (status.equalsIgnoreCase("Selesai")) return "Selesai";
        return "Diambil";
    }

    public static int getTotalLaundryBerhasilDibuat() {
        return totalLaundryBerhasilDibuat;
    }

    public void tampilkanInfo() {
        System.out.printf("[Laundry] Pelanggan: %-15s | Telp: %-13s | Berat: %5.1f kg | Status: %-9s%n",
                this.namaPelanggan, this.noTelepon, this.beratKg, this.status);
    }

    public void aksiLaundry() {
        System.out.println("-> Pesanan sedang diproses oleh petugas laundry.");
    }

    public abstract void hitungEstimasiSelesai();
}