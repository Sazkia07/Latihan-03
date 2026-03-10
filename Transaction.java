import java.util.ArrayList;

/**
 * Class Transaction — mengelola satu sesi transaksi penjualan.
 * Menggunakan ArrayList<Product> (sesuai hint soal) untuk fleksibilitas.
 * Mendukung method overloading addItem.
 */
public class Transaction {

    // ==================== ATTRIBUTES ====================
    private String transactionId;
    private ArrayList<Product> items;  // Menggunakan ArrayList sesuai petunjuk soal
    private int totalItems;

    // ==================== CONSTRUCTOR ====================

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
        this.items = new ArrayList<>();
        this.totalItems = 0;
    }

    // ==================== METHODS ====================

    /**
     * Method Overloading addItem — menambahkan 1 item ke transaksi.
     * @param item produk yang ditambahkan
     */
    public void addItem(Product item) {
        items.add(item);
        totalItems++;
        System.out.println("[+] Ditambahkan: " + item.getName() + " (qty: 1)");
    }

    /**
     * Method Overloading addItem — menambahkan item sebanyak quantity ke transaksi.
     * @param item     produk yang ditambahkan
     * @param quantity jumlah unit yang dibeli
     */
    public void addItem(Product item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
        totalItems += quantity;
        System.out.println("[+] Ditambahkan: " + item.getName() + " (qty: " + quantity + ")");
    }

    /**
     * Menghitung total harga seluruh item setelah diskon.
     * Menggunakan polymorphism: setiap item memanggil calculateDiscount() miliknya sendiri.
     * @return total harga transaksi setelah diskon
     */
    public double processSale() {
        double total = 0.0;
        System.out.println("\n========== PROSES TRANSAKSI: " + transactionId + " ==========");
        for (Product item : items) {
            double discount = item.calculateDiscount();
            double finalPrice = item.getPrice() - (item.getPrice() * discount);
            System.out.printf("  %-25s | Harga: Rp %-12.2f | Diskon: %.0f%% | Bayar: Rp %.2f%n",
                    item.getName(), item.getPrice(), discount * 100, finalPrice);
            total += finalPrice;
        }
        System.out.printf("  %-25s | TOTAL TRANSAKSI: Rp %.2f%n", "", total);
        System.out.println("=".repeat(60));
        return total;
    }

    /**
     * Menampilkan ringkasan laporan transaksi.
     */
    public void printReport() {
        System.out.println("\n---------- LAPORAN TRANSAKSI " + transactionId + " ----------");
        System.out.println("Total Item    : " + totalItems + " unit");
        System.out.printf ("Total Bayar   : Rp %.2f%n", processSale());
    }

    // ==================== GETTERS & SETTERS ====================

    public String              getTransactionId()   { return transactionId; }
    public ArrayList<Product>  getItems()           { return items; }
    public int                 getTotalItems()       { return totalItems; }
}