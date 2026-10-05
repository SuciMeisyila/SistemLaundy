/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public class LaundrySatuan extends Laundry {
    private String jenisBarang;
    private int jumlahBarang;
    private double hargaPerItem;

    public LaundrySatuan(String namaPelanggan, String noTelepon, double beratKg, String status, String jenisBarang, int jumlahBarang, double hargaPerItem) {
        super(namaPelanggan, noTelepon, beratKg, status);
        this.jenisBarang = jenisBarang;
        this.jumlahBarang = jumlahBarang;
        this.hargaPerItem = hargaPerItem;
    }

    public String getJenisBarang() {
        return this.jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        if (jenisBarang != null && !jenisBarang.trim().isEmpty()) {
            this.jenisBarang = jenisBarang.trim();
        } else {
            System.out.println("Jenis barang tidak boleh kosong.");
        }
    }

    public int getJumlahBarang() {
        return this.jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        if (jumlahBarang > 0) {
            this.jumlahBarang = jumlahBarang;
        } else {
            System.out.println("Jumlah barang harus lebih dari 0.");
        }
    }

    public double getHargaPerItem() {
        return this.hargaPerItem;
    }

    public void setHargaPerItem(double hargaPerItem) {
        if (hargaPerItem > 0) {
            this.hargaPerItem = hargaPerItem;
        } else {
            System.out.println("Harga per item harus lebih dari 0.");
        }
    }

    public double hitungTotal() {
        return this.jumlahBarang * this.hargaPerItem;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[SATUAN] Pelanggan: %-15s | Telp: %-13s | Barang: %-10s | Jumlah: %2d | Rp%,10.0f | Status: %-9s%n",
                getNamaPelanggan(), getNoTelepon(), this.jenisBarang,
                this.jumlahBarang, hitungTotal(), getStatus());
    }

    @Override
    public void aksiLaundry() {
        System.out.println("-> Aksi: Barang satuan diproses dan diperiksa satu per satu.");
    }
}
