
public class ClothingProduct extends Product {

    // ==================== ATTRIBUTES ====================
    private String size;
    private String brand;

    // ==================== CONSTRUCTORS ====================

    public ClothingProduct(String productId, String name, double price, int stockQuantity, String size, String brand) {
        super(productId, name, price, stockQuantity);
        this.size = size;
        this.brand = brand;
    }

    // ==================== OVERRIDE METHOD ====================

    /**
     * Diskon 15% apabila ukuran produk adalah L atau XL.
     * @return 0.15 untuk ukuran L/XL, otherwise 0.0
     */
    @Override
    public double calculateDiscount() {
        if (size.equalsIgnoreCase("L") || size.equalsIgnoreCase("XL")) {
            return 0.15; // 15%
        }
        return 0.0;
    }

    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.println("Tipe          : Pakaian");
        System.out.println("Ukuran        : " + size);
        System.out.println("Merek         : " + brand);
    }

    // ==================== GETTERS & SETTERS ====================

    public String getSize()             { return size; }
    public void   setSize(String size)  { this.size = size; }

    public String getBrand()              { return brand; }
    public void   setBrand(String brand)  { this.brand = brand; }

}
