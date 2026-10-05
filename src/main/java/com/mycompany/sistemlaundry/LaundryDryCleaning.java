/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public class LaundryDryCleaning extends Laundry {
    private String jenisPakaian;
    private double hargaLayanan;

    public LaundryDryCleaning(String namaPelanggan, String noTelepon, double beratKg, String status, String jenisPakaian, double hargaLayanan) {
        super(namaPelanggan, noTelepon, beratKg, status);
        this.jenisPakaian = jenisPakaian;
        this.hargaLayanan = hargaLayanan;
    }

    public String getJenisPakaian() {
        return this.jenisPakaian;
    }

    public void setJenisPakaian(String jenisPakaian) {
        if (jenisPakaian != null && !jenisPakaian.trim().isEmpty()) {
            this.jenisPakaian = jenisPakaian.trim();
        } else {
            System.out.println("Jenis pakaian tidak boleh kosong.");
        }
    }

    public double getHargaLayanan() {
        return this.hargaLayanan;
    }

    public void setHargaLayanan(double hargaLayanan) {
        if (hargaLayanan > 0) {
            this.hargaLayanan = hargaLayanan;
        } else {
            System.out.println("Harga layanan harus lebih dari 0.");
        }
    }

    public double hitungTotal() {
        return this.hargaLayanan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf(
                "[DRY CLEANING] Pelanggan: %-15s | Telp: %-13s | Jenis Pakaian: %-10s | Rp%,10.0f | Status: %-9s%n",
                getNamaPelanggan(), getNoTelepon(), this.jenisPakaian, hitungTotal(), getStatus());
    }

    @Override
    public void aksiLaundry() {
        System.out.println("-> Aksi Dry Cleaning: pakaian dibersihkan dengan metode dry cleaning, dikeringkan, dan dicek kembali.");
    }
}