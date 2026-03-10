/**
 * Class abstrak Product sebagai superclass untuk semua produk di Filkom Mart.
 * Menerapkan enkapsulasi dengan private fields dan getter/setter.
 */
public abstract class Product {

    // === ATRIBUT (private untuk enkapsulasi) ===
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    // =============================================
    // KONSTRUKTOR
    // =============================================

    /** Konstruktor default */
    public Product() {
        this.productId = "";
        this.name = "Unknown";
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    /** Konstruktor parameterized */
    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // =============================================
    // METHOD ABSTRAK
    // =============================================

    /**
     * Method abstrak — wajib di-override oleh setiap subclass.
     * Menghitung besaran diskon (dalam Rupiah) untuk produk.
     */
    public abstract double calculateDiscount();

    // =============================================
    // METHOD KONKRET
    // =============================================

    /**
     * Menampilkan informasi dasar produk.
     */
    public void getProductInfo() {
        System.out.println("  ID        : " + productId);
        System.out.println("  Nama      : " + name);
        System.out.printf ("  Harga     : Rp %.2f%n", price);
        System.out.println("  Stok      : " + stockQuantity + " unit");
        System.out.printf ("  Diskon    : Rp %.2f%n", calculateDiscount());
        System.out.printf ("  Harga Net : Rp %.2f%n", price - calculateDiscount());
    }

    // =============================================
    // METHOD OVERLOADING — updateStock
    // =============================================

    /**
     * Overload 1: Perbarui stok tanpa alasan.
     * @param quantity jumlah perubahan stok (positif = tambah, negatif = kurang)
     */
    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
        System.out.println("  [Stock Update] " + name + " → stok baru: " + this.stockQuantity);
    }

    /**
     * Overload 2: Perbarui stok dengan keterangan alasan.
     * @param quantity jumlah perubahan stok
     * @param reason   alasan perubahan stok
     */
    public void updateStock(int quantity, String reason) {
        this.stockQuantity += quantity;
        System.out.println("  [Stock Update] " + name
                + " → stok baru: " + this.stockQuantity
                + " (Alasan: " + reason + ")");
    }

    // =============================================
    // GETTER & SETTER
    // =============================================

    public String getProductId()            { return productId; }
    public void   setProductId(String id)   { this.productId = id; }

    public String getName()                 { return name; }
    public void   setName(String name)      { this.name = name; }

    public double getPrice()                { return price; }
    public void   setPrice(double price)    { this.price = price; }

    public int    getStockQuantity()              { return stockQuantity; }
    public void   setStockQuantity(int qty)       { this.stockQuantity = qty; }

    @Override
    public String toString() {
        return String.format("[%s] %s - Rp %.2f (Stok: %d)", productId, name, price, stockQuantity);
    }
}