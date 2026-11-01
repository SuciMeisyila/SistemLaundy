package com.mycompany.sistemlaundry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemLaundry {

    public static void cariLaundry(String nama, ArrayList<Laundry> daftarLaundry) throws DataTidakValidException {
        if (nama == null || nama.trim().isEmpty()) {
            throw new DataTidakValidException("Kata kunci pencarian nama tidak boleh kosong.");
        }
        System.out.println("Mencari laundry dengan Nama Pelanggan: " + nama);
        boolean ditemukan = false;
        for (int i = 0; i < daftarLaundry.size(); i++) {
            if (daftarLaundry.get(i).getNamaPelanggan().equalsIgnoreCase(nama)) {
                daftarLaundry.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("-> [INFO] Data laundry tidak ditemukan.");
        }
    }

    public static void cariLaundry(String status, ArrayList<Laundry> daftarLaundry, boolean berdasarkanStatus) throws DataTidakValidException {
        if (status == null || status.trim().isEmpty()) {
            throw new DataTidakValidException("Status pencarian tidak boleh kosong.");
        }
        System.out.println("Mencari laundry dengan Status: " + status);
        boolean ditemukan = false;
        for (int i = 0; i < daftarLaundry.size(); i++) {
            if (berdasarkanStatus && daftarLaundry.get(i).getStatus().equalsIgnoreCase(status)) {
                daftarLaundry.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("-> [INFO] Data laundry tidak ditemukan.");
        }
    }

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
        ArrayList<Laundry> daftarLaundry = new ArrayList<>();
        HashMap<String, String> dataPelanggan = new HashMap<>();
        boolean isRunning = true;

        try {
            daftarLaundry.add(new LaundryKiloan("Andi", "081234567890", 3.0, "Diproses", "Reguler", 7000));
            daftarLaundry.add(new LaundrySatuan("Budi", "082345678901", 0.5, "Selesai", "Jas", 1, 25000));
            daftarLaundry.add(new LaundryDryCleaning("Citra", "083456789012", 2.0, "Diproses", "Dry Cleaning", 30000));
            daftarLaundry.add(new LaundryKiloan("Dewi", "084567890123", 5.0, "Diambil", "Express", 10000));
            daftarLaundry.add(new LaundrySatuan("Eko", "085678901234", 1.0, "Diproses", "Selimut", 2, 20000));

            dataPelanggan.put("P001", "Andi");
            dataPelanggan.put("P002", "Budi");
            dataPelanggan.put("P003", "Citra");

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
            System.out.println("4. Manajemen Pelanggan (Demo HashMap)");
            System.out.println("5. Simulasi Proses");
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

                                daftarLaundry.add(new LaundryKiloan(nama, telepon, berat, status, layanan, hargaKg));
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

                                daftarLaundry.add(new LaundrySatuan(nama, telepon, 0, status, barang, jumlahBarang, hargaItem));
                                System.out.println("Data laundry satuan berhasil ditambahkan.");

                            } else if (jenis == 3) {
                                System.out.print("Jenis Pakaian: ");
                                String jenisPakaian = scanner.nextLine();

                                System.out.print("Harga Dry Cleaning: Rp");
                                double harga = scanner.nextDouble();
                                scanner.nextLine();

                                daftarLaundry.add(new LaundryDryCleaning(nama, telepon, 0.0, status, jenisPakaian, harga));
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

                        if (daftarLaundry.isEmpty()) {
                            System.out.println("Belum ada data laundry yang tersimpan");
                        } else {
                            for (int i = 0; i < daftarLaundry.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarLaundry.get(i).tampilkanInfo();
                                daftarLaundry.get(i).aksiLaundry();
                                daftarLaundry.get(i).hitungEstimasiSelesai();

                                if (daftarLaundry.get(i) instanceof DapatDiantar) {
                                    DapatDiantar itemAntar = (DapatDiantar) daftarLaundry.get(i);
                                    itemAntar.prosesAntar();
                                }
                                if (daftarLaundry.get(i) instanceof DapatDinilai) {
                                    DapatDinilai itemBisaDinilai = (DapatDinilai) daftarLaundry.get(i);
                                    if (itemBisaDinilai.getRating() == 0) {
                                        System.out.println("-> [RATING] Belum ada rating dari pelanggan.");
                                    } else {
                                        System.out.println("-> [RATING] Rating pelanggan: " + itemBisaDinilai.getRating() + "/5 Bintang.");
                                    }
                                }
                                System.out.println();
                            }
                            System.out.println("----------------------------------------------------------");
                            System.out.println("Total objek laundry berhasil dibuat: " + Laundry.getTotalLaundryBerhasilDibuat());
                        }
                    }

                    case 3 -> {
                        System.out.println("\n------------- PENCARIAN / AKSI KHUSUS ----------------");
                        System.out.println("1. Cari berdasarkan nama pelanggan");
                        System.out.println("2. Cari berdasarkan status");
                        System.out.println("3. Tampilkan cara/aksi proses berdasarkan tipe");

                        System.out.print("Pilih (1-3): ");
                        try {
                            int mode = Integer.parseInt(scanner.nextLine());
                            if (mode == 1) {
                                System.out.print("Masukkan nama pelanggan: ");
                                String kataKunci = scanner.nextLine();
                                cariLaundry(kataKunci, daftarLaundry);

                            } else if (mode == 2) {
                                System.out.print("Masukkan status: ");
                                String statusCari = scanner.nextLine();
                                cariLaundry(statusCari, daftarLaundry, true);

                            } else if (mode == 3) {
                                if (daftarLaundry.isEmpty()) {
                                    System.out.println("Belum ada data laundry.");
                                } else {
                                    for (int i = 0; i < daftarLaundry.size(); i++) {
                                        System.out.printf("%2d. %s%n", i + 1, daftarLaundry.get(i).getNamaPelanggan());
                                        daftarLaundry.get(i).aksiLaundry();
                                    }
                                }
                            } else {
                                System.out.println("Pilihan pencarian tidak valid.");
                            }
                        } catch (DataTidakValidException e) {
                            System.out.println("-> [ERROR PENCARIAN] " + e.getMessage());
                        } finally {
                            System.out.println("-> [FINALLY] Proses pencarian selesai.");
                        }
                    }

                    case 4 -> {
                        System.out.println("\n--- Kelola Pelanggan (HashMap) ---");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via ID");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih: ");

                        int menuPelanggan = scanner.nextInt();
                        scanner.nextLine(); // membersihkan buffer

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan ID (sebagai key): ");
                            String idPelanggan = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (sebagai value): ");
                            String namaPelanggan = scanner.nextLine();

                            dataPelanggan.put(idPelanggan, namaPelanggan);
                            System.out.println("Berhasil menambahkan pelanggan!");

                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan ID yang di cari: ");
                            String searchId = scanner.nextLine();

                            if (dataPelanggan.containsKey(searchId)) {
                                System.out.println("Nama Pelanggan: " + dataPelanggan.get(searchId));
                            } else {
                                System.out.println("Pelanggan dengan ID tersebut tidak terdaftar.");
                            }

                        } else if (menuPelanggan == 3) {
                            System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
                            for (String keyId : dataPelanggan.keySet()) {
                                System.out.println("ID: " + keyId + " | Nama: " + dataPelanggan.get(keyId));
                            }
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }

                    case 5 -> {
                        System.out.println("Simulasi Proses Laundry");
                        if (daftarLaundry.isEmpty()) {
                            System.out.println("Belum ada data laundry yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarLaundry.size(); i++) {
                                System.out.println("Proses laundry ke-" + (i + 1));
                                prosesLaundry(daftarLaundry.get(i));
                            }
                        }
                    }
                    
                    case 6 -> {
                        System.out.println("\n--- Aksi Interface: Antar & Rating ---");
                        if (daftarLaundry.isEmpty()) {
                            System.out.println("Belum ada data laundry yang tersimpan.");
                        } else {
                            System.out.print("Masukkan nama pelanggan: ");
                            String namaCari = scanner.nextLine().trim();

                            boolean ditemukan = false;
                            for (int i = 0; i < daftarLaundry.size(); i++) {
                                if (daftarLaundry.get(i).getNamaPelanggan().equalsIgnoreCase(namaCari)) {
                                    ditemukan = true;
                                    System.out.println("Data ditemukan: " + daftarLaundry.get(i).getNamaPelanggan()
                                            + " (" + daftarLaundry.get(i).getClass().getSimpleName() + ")");

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

                                    jalankanAksiInterface(daftarLaundry.get(i), bintang);
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