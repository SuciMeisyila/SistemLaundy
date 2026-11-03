/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

/**
 *
 * @author ASUS
 */
public class RuangLaundry {
    private String namaRuangan;
    private int kapasitasMesin;

    public RuangLaundry(String namaRuangan, int kapasitasMesin) {
        this.namaRuangan = namaRuangan;
        this.kapasitasMesin = kapasitasMesin;
    }

    public void bukaRuangan() {
        System.out.println("-> [KOMPOSISI] [" + this.namaRuangan + "] dibuka. Kapasitas Mesin: " + this.kapasitasMesin + " unit.");
    }
}