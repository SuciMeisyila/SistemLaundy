/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public class LaundryKiloan extends Laundry implements DapatDiantar, DapatDinilai {
    private String jenisLayanan;
    private double hargaPerKg;
    private int rating = 0;

    public LaundryKiloan(String namaPelanggan, String noTelepon, double beratKg, String status, String jenisLayanan, double hargaPerKg) throws DataTidakValidException {
        super(namaPelanggan, noTelepon, beratKg, status);
        setJenisLayanan(jenisLayanan);
        setHargaPerKg(hargaPerKg);
    }

    public String getJenisLayanan() {
        return this.jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) throws DataTidakValidException {
        if (jenisLayanan != null && !jenisLayanan.trim().isEmpty()) {
            this.jenisLayanan = jenisLayanan.trim();
        } else {
            throw new DataTidakValidException("Jenis layanan tidak boleh kosong.");
        }
    }

    public double getHargaPerKg() {
        return this.hargaPerKg;
    }

    public void setHargaPerKg(double hargaPerKg) throws DataTidakValidException {
        if (hargaPerKg > 0) {
            this.hargaPerKg = hargaPerKg;
        } else {
            throw new DataTidakValidException("Harga per kg harus lebih dari 0.");
        }
    }

    public double hitungTotal() {
        return getBeratKg() * this.hargaPerKg;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[KILOAN] Pelanggan: %-15s | Telp: %-13s | Berat: %4.1f kg | Layanan: %-10s | Rp%,10.0f | Status: %-9s%n",
                getNamaPelanggan(), getNoTelepon(), getBeratKg(),
                this.jenisLayanan, hitungTotal(), getStatus());
    }

    @Override
    public void aksiLaundry() {
        System.out.println("-> Aksi: Cucian kiloan ditimbang, dicuci, dikeringkan, lalu dilipat.");
    }

    @Override
    public void hitungEstimasiSelesai() {
        System.out.println("-> [ESTIMASI] Laundry kiloan selesai dalam 2 hari (Express: 1 hari).");
    }

    @Override
    public void prosesAntar() {
        System.out.println("-> [ANTAR] Cucian kiloan dikemas dan diantar kurir ke alamat pelanggan.");
    }

    @Override
    public void beriRating(int bintang) {
        this.rating = bintang;
        System.out.println("-> [ULASAN KILOAN] Layanan kiloan mendapat rating " + bintang + "/5 Bintang.");
    }
    
    @Override
    public int getRating() {
        return this.rating;
    }
}