/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class TransaksiLaundry {
    public static void prosesTransaksi(Pelanggan pelanggan, Laundry laundry) {
        System.out.println("\n===== STRUK TRANSAKSI =====");
        System.out.println("Pelanggan : " + pelanggan.getNama() + " (" + pelanggan.getIdPelanggan() + ")");
        System.out.println("Item      : " + laundry.getClass().getSimpleName());
        System.out.println("Status    : Berhasil dicatat di sistem.");
        
        laundry.aksiLaundry();
        if (laundry instanceof DapatDiantar) {
            ((DapatDiantar) laundry).prosesAntar();
        }
        System.out.println("===================================");

        try {
            FileWriter pipaTulis = new FileWriter("log_transaksi.txt", true);
            String log = "Pelanggan: " + pelanggan.getNama() + " (" + pelanggan.getIdPelanggan() + ")"
                       + " | Berat: " + laundry.getBeratKg() + " kg | Status: " + laundry.getStatus() + "\n";
            pipaTulis.write(log);
            pipaTulis.close(); 
            System.out.println("-> [FILE I/O] Log transaksi berhasil ditulis ke log_transaksi.txt");
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi ke file. " + e.getMessage());
        }
    }
}