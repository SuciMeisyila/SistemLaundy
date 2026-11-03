package com.mycompany.sistemlaundry;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemLaundry {

    public static void prosesLaundry(Laundry item) {
        item.aksiLaundry();
    }
    
    public static void jalankanAksiInterface(Laundry item, int bintang) {
        if (item instanceof DapatDiantar) {
            DapatDiantar layananAntar = (DapatDiantar) item;
            layananAntar.prosesAntar();
        } else {
            System.out.println("-> [INFO] Layanan ini tidak mendukung antar (ambil di outlet).");
        }

        if (item instanceof DapatDinilai) {
            DapatDinilai layananNilai = (DapatDinilai) item;
            layananNilai.beriRating(bintang);
        } else {
            System.out.println("-> [INFO] Layanan ini belum bisa diberi rating.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManajemenOutletLaundry outletUnila = new ManajemenOutletLaundry("Outlet Laundry Unila");
        outletUnila.bukaOutlet();
        boolean isRunning = true;

        try {
            outletUnila.tambahLaundry(new LaundryKiloan("Andi", "081234567890", 3.0, "Diproses", "Reguler", 7000));
            outletUnila.tambahLaundry(new LaundrySatuan("Budi", "082345678901", 0.5, "Selesai", "Jas", 1, 25000));
            outletUnila.tambahLaundry(new LaundryDryCleaning("Citra", "083456789012", 2.0, "Diproses", "Dry Cleaning", 30000));
            outletUnila.tambahLaundry(new LaundryKiloan("Dewi", "084567890123", 5.0, "Diambil", "Express", 10000));
            outletUnila.tambahLaundry(new LaundrySatuan("Eko", "085678901234", 1.0, "Diproses", "Selimut", 2, 20000));

            outletUnila.tambahPelanggan(new Pelanggan("P001", "Andi", "081234567890"));
            outletUnila.tambahPelanggan(new Pelanggan("P002", "Budi", "082345678901"));
            outletUnila.tambahPelanggan(new Pelanggan("P003", "Citra", "083456789012"));

        } catch (DataTidakValidException e) {
            System.out.println("Gagal memuat data awal: " + e.getMessage());
        }
        
        System.out.println("==============================================================");
        System.out.println("             SISTEM MANAJEMEN LAUNDRY                        ");
        System.out.println("==============================================================");

        while (isRunning) {
            System.out.println("\n----------------------- MENU UTAMA ---------------------------");
            System.out.println("1. Tambah Data Laundry");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Pencarian / Aksi Khusus");
            System.out.println("4. Manajemen Pelanggan (Agregasi)");
            System.out.println("5. Simulasi Transaksi (Asosiasi)");
            System.out.println("6. Aksi Interface (Antar & Rating)");
            System.out.println("7. Keluar");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Pilih Menu: 1-7: ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n--- PILIH TIPE LAUNDRY ---");
                        System.out.println("1. Laundry Kiloan");
                        System.out.println("2. Laundry Satuan");
                        System.out.println("3. Laundry Dry Cleaning");
                        System.out.print("Pilihan (1/2/3): ");

                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        try {
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

                                outletUnila.tambahLaundry(new LaundryKiloan(nama, telepon, berat, status, layanan, hargaKg));
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

                                outletUnila.tambahLaundry(new LaundrySatuan(nama, telepon, 0, status, barang, jumlahBarang, hargaItem));
                                System.out.println("Data laundry satuan berhasil ditambahkan.");

                            } else if (jenis == 3) {
                                System.out.print("Jenis Pakaian: ");
                                String jenisPakaian = scanner.nextLine();

                                System.out.print("Harga Dry Cleaning: Rp");
                                double harga = scanner.nextDouble();
                                scanner.nextLine();

                                outletUnila.tambahLaundry(new LaundryDryCleaning(nama, telepon, 0.0, status, jenisPakaian, harga));
                                System.out.println("Data laundry dry cleaning berhasil ditambahkan.");
                            } else {
                                System.out.println("Jenis laundry tidak valid. Data tidak ditambahkan.");
                            }
                        } catch (DataTidakValidException e) {
                            System.out.println("-> [ERROR VALIDASI] " + e.getMessage());
                        } finally {
                            System.out.println("-> [FINALLY] Blok penambahan data selesai.");
                        }
                    }

                    case 2 -> {
                        System.out.println("\n================ DAFTAR SELURUH LAUNDRY ================");
                        outletUnila.lihatSemuaLaundry();
                        System.out.println("----------------------------------------------------------");
                        System.out.println("Total objek laundry berhasil dibuat: " + Laundry.getTotalLaundryBerhasilDibuat());
                    }

                    case 3 -> {
                        System.out.println("\n------------- PENCARIAN LAUNDRY ----------------");
                        System.out.print("Masukkan nama pelanggan: ");
                        String kataKunci = scanner.nextLine();
                        outletUnila.cariLaundry(kataKunci);
                        }

                    case 4 -> {
                        System.out.println("\n--- Kelola Pelanggan (Agregasi) ---");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via ID");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih: ");

                        int menuPelanggan = scanner.nextInt();
                        scanner.nextLine();

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan ID Pelanggan: ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama: ");
                            String nama = scanner.nextLine();
                            System.out.print("Masukkan No Telepon: ");
                            String telp = scanner.nextLine();

                            outletUnila.tambahPelanggan(new Pelanggan(id, nama, telp));
                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan ID yang dicari: ");
                            outletUnila.cariPelanggan(scanner.nextLine());
                        } else if (menuPelanggan == 3) {
                            outletUnila.lihatSemuaPelanggan();
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }

                    case 5 -> {
                        System.out.println("\n--- Simulasi Transaksi (Asosiasi) ---");
                        System.out.print("Masukkan ID Pelanggan: ");
                        String inputId = scanner.nextLine();

                        Pelanggan pelanggan = outletUnila.getPelanggan(inputId);

                        if (pelanggan == null) {
                            System.out.println("-> [GAGAL] Pelanggan dengan ID tersebut belum terdaftar!");
                        } else if (outletUnila.getJumlahLaundry() == 0) {
                            System.out.println("-> [GAGAL] Belum ada data laundry yang tersedia di outlet.");
                        } else {
                            outletUnila.lihatSemuaLaundry();
                            System.out.print("Pilih Nomor Laundry yang ingin diproses: ");
                            int noLaundry = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (noLaundry >= 0 && noLaundry < outletUnila.getJumlahLaundry()) {
                                Laundry itemLaundry = outletUnila.getLaundryInt(noLaundry);
                                TransaksiLaundry.prosesTransaksi(pelanggan, itemLaundry);
                            } else {
                                System.out.println("-> [GAGAL] Nomor urut laundry tidak valid!");
                            }
                        }
                    }
                    
                    case 6 -> {
                        System.out.println("\n--- Aksi Interface: Antar & Rating ---");
                        if (outletUnila.getJumlahLaundry() == 0) {
                            System.out.println("Belum ada data laundry yang tersimpan.");
                        } else {
                            System.out.print("Masukkan nama pelanggan: ");
                            String namaCari = scanner.nextLine().trim();

                            boolean ditemukan = false;
                            for (int i = 0; i < outletUnila.getJumlahLaundry(); i++) {
                                Laundry item = outletUnila.getLaundryInt(i);
                                if (item.getNamaPelanggan().equalsIgnoreCase(namaCari)) {
                                    ditemukan = true;
                                    System.out.println("Data ditemukan: " + item.getNamaPelanggan()
                                            + " (" + item.getClass().getSimpleName() + ")");

                                    int bintang = 0;
                                    boolean ratingValid = false;
                                    while (!ratingValid) {
                                        System.out.print("Masukkan rating (1-5): ");
                                        try {
                                            bintang = Integer.parseInt(scanner.nextLine().trim());
                                            if (bintang >= 1 && bintang <= 5) {
                                                ratingValid = true;
                                            } else {
                                                System.out.println("Rating harus 1-5.");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Rating harus berupa angka.");
                                        }
                                    }

                                    jalankanAksiInterface(item, bintang);
                                }
                            }

                            if (!ditemukan) {
                                System.out.println("Data laundry dengan nama \"" + namaCari + "\" tidak ditemukan.");
                            }
                        }
                    }
                    
                    case 7 -> {
                        System.out.println("Terima kasih telah menggunakan Sistem Laundry!");
                        isRunning = false;
                    }

                    default -> {
                        System.out.println("Menu tidak tersedia. Silakan pilih 1-7.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Anda harus memasukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan: " + e.getMessage());
            } finally {
                System.out.println("-> [FINALLY] Eksekusi menu saat ini selesai.\n");
            }
        }
        scanner.close();
    }
}