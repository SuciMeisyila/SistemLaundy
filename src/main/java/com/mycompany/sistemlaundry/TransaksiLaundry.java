/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

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
        laundry.hitungEstimasiSelesai();
        
        if (laundry instanceof DapatDiantar) {
            ((DapatDiantar) laundry).prosesAntar();
        }
        System.out.println("===========================");
    }
}