/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemlaundry;

import java.io.Serializable;
/**
 *
 * @author ASUS
 */
public class Pelanggan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idPelanggan;
    private String nama;
    private String noTelepon;

    public Pelanggan(String idPelanggan, String nama, String noTelepon) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.noTelepon = noTelepon;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }
    public String getNama() {
        return nama;
    }
    public String getNoTelepon() {
        return noTelepon;
    }
}