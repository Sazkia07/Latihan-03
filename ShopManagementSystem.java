import java.util.ArrayList;

/**
 * Class utama ShopManagementSystem — entry point aplikasi POS Filkom Mart.
 * Mendemonstrasikan seluruh konsep OOP:
 *   - Inheritance     : FoodProduct, ElectronicProduct, ClothingProduct extends Product
 *   - Polymorphism    : array Product[] dengan method calculateDiscount() & getProductInfo()
 *   - Overriding      : setiap subclass override calculateDiscount()
 *   - Overloading     : updateStock() dan addItem() dengan signature berbeda
 *   - Encapsulation   : private fields + getter/setter di semua class
 */
public class ShopManagementSystem {

    public static void main(String[] args) {

        // ============================================================
        // 1. INISIALISASI PRODUK (min. 6 objek, 2 per subclass)
        // ============================================================

        // --- FoodProduct (2 objek) ---
        FoodProduct f1 = new FoodProduct("F001", "Indomie Goreng",   3_500,   15, "31-12-2025");
        FoodProduct f2 = new FoodProduct("F002", "Aqua 1.5L",        5_000,    8, "01-06-2025");

        // --- ElectronicProduct (2 objek) ---
        ElectronicProduct e1 = new ElectronicProduct("E001", "Earphone Bluetooth", 350_000, 20, "6 Bulan");
        ElectronicProduct e2 = new ElectronicProduct("E002", "Laptop ASUS VivoBook", 7_500_000,  5, "2 Tahun");

        // --- ClothingProduct (2 objek) ---
        ClothingProduct c1 = new ClothingProduct("C001", "Kaos Polos Hitam",  120_000, 30, "L",  "Uniqlo");
        ClothingProduct c2 = new ClothingProduct("C002", "Celana Jeans Slim", 250_000, 12, "M",  "Levi's");

        // ============================================================
        // 2. ARRAY PRODUCT[] — POLYMORPHISM
        // ============================================================
        Product[] products = { f1, f2, e1, e2, c1, c2 };

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║           FILKOM MART — INFO SEMUA PRODUK        ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Polymorphism: memanggil getProductInfo() dan calculateDiscount()
        // pada setiap elemen tanpa tahu tipe konkretnya
        for (Product p : products) {
            p.getProductInfo();  // overriding: tiap subclass punya implementasi sendiri
        }

        // ============================================================
        // 3. DEMO METHOD OVERLOADING updateStock()
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║             UPDATE STOK PRODUK                   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        f1.updateStock(5);                          // overload tanpa alasan
        f2.updateStock(-2, "Terjual ke pelanggan"); // overload dengan alasan
        e1.updateStock(10, "Restock dari supplier");
        c1.updateStock(-5, "Penjualan minggu ini");

        // ============================================================
        // 4. SIMULASI TRANSAKSI 1
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║               TRANSAKSI 1 — TRX-001              ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        Transaction trx1 = new Transaction("TRX-001");
        trx1.addItem(f1);          // overload: addItem(Product item)
        trx1.addItem(e1, 2);       // overload: addItem(Product item, int quantity)
        trx1.addItem(c1, 3);       // pakaian ukuran L -> diskon 15%
        double totalTrx1 = trx1.processSale();

        // ============================================================
        // 5. SIMULASI TRANSAKSI 2
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║               TRANSAKSI 2 — TRX-002              ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        Transaction trx2 = new Transaction("TRX-002");
        trx2.addItem(e2);          // laptop mahal -> diskon 7%
        trx2.addItem(f2, 4);       // stok <= 10 -> tanpa diskon
        trx2.addItem(c2);          // ukuran M -> tanpa diskon
        double totalTrx2 = trx2.processSale();

        // ============================================================
        // 6. LAPORAN AKHIR
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              LAPORAN AKHIR FILKOM MART            ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        double totalPenjualan = totalTrx1 + totalTrx2;
        System.out.printf("Total Transaksi 1 (TRX-001)  : Rp %.2f%n", totalTrx1);
        System.out.printf("Total Transaksi 2 (TRX-002)  : Rp %.2f%n", totalTrx2);
        System.out.printf("TOTAL PENJUALAN KESELURUHAN  : Rp %.2f%n", totalPenjualan);

        // Produk terlaris (berdasarkan jumlah item terjual)
        System.out.println("\n--- Ringkasan Item Terjual ---");
        hitungProdukTerlaris(trx1, trx2);

        // Tampilkan stok akhir semua produk
        System.out.println("\n--- Stok Akhir Produk ---");
        for (Product p : products) {
            System.out.printf("%-25s | Stok: %d unit%n", p.getName(), p.getStockQuantity());
        }
        System.out.println("==================================================");
    }

    /**
     * Menghitung dan menampilkan produk terlaris dari semua transaksi.
     * Menggunakan polymorphism melalui ArrayList<Product>.
     */
    private static void hitungProdukTerlaris(Transaction... transactions) {
        // Gabungkan semua item dari semua transaksi
        ArrayList<String> semuaNama = new ArrayList<>();
        for (Transaction t : transactions) {
            for (Product p : t.getItems()) {
                semuaNama.add(p.getName());
            }
        }

        // Hitung kemunculan tiap produk
        ArrayList<String> sudahDihitung = new ArrayList<>();
        String produkTerlaris = "";
        int maxJumlah = 0;

        for (String nama : semuaNama) {
            if (!sudahDihitung.contains(nama)) {
                int jumlah = 0;
                for (String n : semuaNama) {
                    if (n.equals(nama)) jumlah++;
                }
                System.out.printf("  %-25s : %d unit terjual%n", nama, jumlah);
                if (jumlah > maxJumlah) {
                    maxJumlah = jumlah;
                    produkTerlaris = nama;
                }
                sudahDihitung.add(nama);
            }
        }
        System.out.println("\n🏆 Produk Terlaris: " + produkTerlaris + " (" + maxJumlah + " unit)");
    }
}