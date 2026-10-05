package com.mycompany.sistemlaundry;

import java.util.Scanner;

public class SistemLaundry {

    private static final int KAPASITAS = 20;

    public static void cariLaundry(String nama, Laundry[] daftarLaundry, int jumlahLaundry) {
        System.out.println("Mencari laundry dengan Nama Pelanggan: " + nama);
        boolean ditemukan = false;
        for (int i = 0; i < jumlahLaundry; i++) {
            if (daftarLaundry[i].getNamaPelanggan().equalsIgnoreCase(nama)) {
                daftarLaundry[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data laundry tidak ditemukan.");
    }

    public static void cariLaundry(String status, Laundry[] daftarLaundry, int jumlahLaundry, boolean berdasarkanStatus) {
        System.out.println("Mencari laundry dengan Status: " + status);
        boolean ditemukan = false;
        for (int i = 0; i < jumlahLaundry; i++) {
            if (berdasarkanStatus && daftarLaundry[i].getStatus().equalsIgnoreCase(status)) {
                daftarLaundry[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data laundry tidak ditemukan.");
    }

    public static void prosesLaundry(Laundry item) {
        item.aksiLaundry();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Laundry[] daftarLaundry = new Laundry[KAPASITAS];
            int jumlahLaundry = 0;
            boolean isRunning = true;

            daftarLaundry[jumlahLaundry++] = new LaundryKiloan(
                    "Andi", "081234567890", 3.0,
                    "Diproses", "Reguler", 7000);

            daftarLaundry[jumlahLaundry++] = new LaundrySatuan(
                    "Budi", "082345678901", 0.5,
                    "Selesai", "Jas", 1, 25000);

            daftarLaundry[jumlahLaundry++] = new LaundryDryCleaning(
                    "Citra", "083456789012", 2.0,
                    "Diproses", "Dry Cleaning", 30000);
            
                    System.out.println("==============================================================");
                    System.out.println("             SISTEM MANAJEMEN LAUNDRY                        ");
                    System.out.println("==============================================================");

            while (isRunning) {
                System.out.println("\n----------------------- MENU UTAMA ---------------------------");
                System.out.println("1. Tambah Data Laundry");
                System.out.println("2. Tampilkan Seluruh Data");
                System.out.println("3. Pencarian / Aksi Khusus");
                System.out.println("4. Simulasi Proses");
                System.out.println("5. Keluar");
                System.out.println("--------------------------------------------------------------");
                System.out.print("Pilih Menu: 1-5: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        if (jumlahLaundry >= daftarLaundry.length) {
                            System.out.println("Kapasitas data laundry sudah penuh.");
                            break;
                        }

                        if (jumlahLaundry < daftarLaundry.length) {
                            System.out.println("\n--- PILIH TIPE LAUNDRY ---");
                            System.out.println("1. Laundry Kiloan");
                            System.out.println("2. Laundry Satuan");
                            System.out.println("3. Laundry Dry Cleaning");
                            System.out.print("Pilihan (1/2/3): ");

                            int jenis = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Nama Pelanggan: ");
                            String nama = scanner.nextLine();

                            System.out.print("No. telepon (10-13 digit): ");
                            String telepon = scanner.nextLine();

                            System.out.print("Status (Diproses/Selesai/Diambil): ");
                            String status = scanner.nextLine();

                            if (jenis == 1) {
                                System.out.print("Berat Laundry (kg): ");
                                double berat = scanner.nextDouble();
                                scanner.nextLine();

                                System.out.print("Jenis Layanan (Reguler/Express): ");
                                String layanan = scanner.nextLine();

                                System.out.print("Harga per Kg: Rp");
                                double hargaKg = scanner.nextDouble();
                                scanner.nextLine();

                                daftarLaundry[jumlahLaundry] = new LaundryKiloan(nama, telepon, berat, status, layanan, hargaKg);
                                jumlahLaundry++;
                                System.out.println("Data laundry kiloan berhasil ditambahkan.");

                            } else if (jenis == 2) {
                                System.out.print("Jenis barang (Jas/Selimut/Sepatu/dll): ");
                                String barang = scanner.nextLine();

                                System.out.print("Jumlah Barang: ");
                                int jumlahBarang = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Harga per Item: Rp");
                                double hargaItem = scanner.nextDouble();
                                scanner.nextLine();

                                daftarLaundry[jumlahLaundry] = new LaundrySatuan(nama, telepon, 0, status, barang, jumlahBarang, hargaItem);
                                jumlahLaundry++;
                                System.out.println("Data laundry satuan berhasil ditambahkan.");

                            } else if (jenis == 3) {
                                System.out.print("Jenis Pakaian: ");
                                String jennisPakaian = scanner.nextLine();

                                System.out.print("Jenis Layanan (Reguler/Express): ");
                                String layanan = scanner.nextLine();

                                System.out.print("Harga Dry Cleaning: Rp");
                                double harga = scanner.nextDouble();
                                scanner.nextLine();

                                daftarLaundry[jumlahLaundry] = new LaundryDryCleaning( nama, telepon, jenis, status, layanan, harga);
                                jumlahLaundry++;
                                
                                System.out.println("Data laundry satuan berhasil ditambahkan.");
                            } else {
                                System.out.println("Jenis laundry tidak valid. Data tidak ditambahkan.");
                            }
                        }
                    }

                    case 2 -> {
                        System.out.println("\n================ DAFTAR SELURUH LAUNDRY ================");

                        if (jumlahLaundry == 0) {
                            System.out.println("Belum ada data laundry yang tersimpan");
                        } else {
                            for (int i = 0; i < jumlahLaundry; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarLaundry[i].tampilkanInfo();
                                daftarLaundry[i].aksiLaundry();
                                System.out.println();
                            }
                            System.out.println( "----------------------------------------------------------");
                            System.out.println("Total objek laundry berhasil dibuat: " + Laundry.getTotalLaundryBerhasilDibuat());
                        }
                    }


                    case 3 -> {
                        System.out.println("\n------------- PENCARIAN / AKSI KHUSUS ----------------");
                        System.out.println("1. Cari berdasarkan nama pelanggan");
                        System.out.println("2. Cari berdasarkan status");
                        System.out.println("3. Tampilkan cara/aksi proses berdasarkan tipe");

                        System.out.print("Pilih (1-3): ");
                        int mode = Integer.parseInt(scanner.nextLine());
                        if (mode == 1) {
                            System.out.print("Masukkan nama pelanggan: ");
                            String kataKunci = scanner.nextLine();

                            cariLaundry(kataKunci, daftarLaundry, jumlahLaundry);

                        } else if (mode == 2) {
                            System.out.print("Masukkan status: ");
                            String statusCari = scanner.nextLine();

                            cariLaundry(statusCari, daftarLaundry, jumlahLaundry, true);

                        } else if (mode == 3) {
                            if (jumlahLaundry == 0) {
                                System.out.println("Belum ada data laundry.");
                            } else {
                                for (int i = 0; i < jumlahLaundry; i++) {
                                    System.out.printf("%2d. %s%n", i + 1, daftarLaundry[i].getNamaPelanggan());
                                    daftarLaundry[i].aksiLaundry();
                                }
                            }

                        } else {
                            System.out.println("Pilihan pencarian tidak valid.");
                        }
                        }

                    case 4 -> {
                        System.out.println( "Simulasi Proses Laundry");
                        if (jumlahLaundry == 0) {
                            System.out.println( "Belum ada data laundry yang tersimpan.");
                        } else {
                            for (int i = 0; i < jumlahLaundry; i++) {
                                System.out.println( "Proses laundry ke-" + (i + 1));
                                prosesLaundry(daftarLaundry[i]);
                            }
                        }
                    }
                    
                    case 5 -> {
                    System.out.println(
                                "Terima kasih telah menggunakan Sistem Laundry!");

                        isRunning = false;
                    }

                    default -> {
                        System.out.println(
                                "Menu tidak tersedia. Silakan pilih 1-5.");

                        System.out.println(
                                "\nTekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                    }
                }
            }
        }
    }
}