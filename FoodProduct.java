
public class FoodProduct extends Product {

    // ==================== ATTRIBUTES ====================
    private String expiryDate;

    // ==================== CONSTRUCTORS ====================

    public FoodProduct(String productId, String name, double price, int stockQuantity, String expiryDate) {
        super(productId, name, price, stockQuantity);
        this.expiryDate = expiryDate;
    }

    // ==================== OVERRIDE METHOD ====================

    /**
     * Diskon 10% apabila stok lebih dari 10 unit.
     * @return 0.10 jika stok > 10, otherwise 0.0
     */
    @Override
    public double calculateDiscount() {
        if (getStockQuantity() > 10) {
            return 0.10; // 10%
        }
        return 0.0;
    }

    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.println("Tipe          : Makanan/Minuman");
        System.out.println("Kadaluarsa    : " + expiryDate);
    }

    // ==================== GETTERS & SETTERS ====================

    public String getExpiryDate()               { return expiryDate; }
    public void   setExpiryDate(String expiry)  { this.expiryDate = expiry; }

}
