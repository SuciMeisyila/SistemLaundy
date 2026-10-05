/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public class LaundryKiloan extends Laundry {
    private String jenisLayanan;
    private double hargaPerKg;

    public LaundryKiloan(String namaPelanggan, String noTelepon, double beratKg, String status, String jenisLayanan, double hargaPerKg) {
        super(namaPelanggan, noTelepon, beratKg, status);
        this.jenisLayanan = jenisLayanan;
        this.hargaPerKg = hargaPerKg;
    }

    public String getJenisLayanan() {
        return this.jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        if (jenisLayanan != null && !jenisLayanan.trim().isEmpty()) {
            this.jenisLayanan = jenisLayanan.trim();
        } else {
            System.out.println("Jenis layanan tidak boleh kosong.");
        }
    }

    public double getHargaPerKg() {
        return this.hargaPerKg;
    }

    public void setHargaPerKg(double hargaPerKg) {
        if (hargaPerKg > 0) {
            this.hargaPerKg = hargaPerKg;
        } else {
            System.out.println("Harga per kg harus lebih dari 0.");
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
}
