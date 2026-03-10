/**
 * Subclass ElectronicProduct — mewarisi dari Product.
 * Mewakili produk elektronik di Filkom Mart.
 * Override calculateDiscount(): 5% + bonus 2% jika harga > 500.000.
 */
public class ElectronicProduct extends Product {

    // ==================== ATTRIBUTES ====================
    private String warrantyPeriod;

    // ==================== CONSTRUCTORS ====================

    /** Constructor parameterized */
    public ElectronicProduct(String productId, String name, double price, int stockQuantity, String warrantyPeriod) {
        super(productId, name, price, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    // ==================== OVERRIDE METHOD ====================

    /**
     * Diskon 5% untuk semua produk elektronik.
     * Bonus tambahan 2% apabila harga > Rp 500.000.
     * @return total diskon (0.05 atau 0.07)
     */
    @Override
    public double calculateDiscount() {
        double discount = 0.05; // Diskon dasar 5%
        if (getPrice() > 500_000) {
            discount += 0.02; // Bonus 2% untuk harga > 500rb
        }
        return discount;
    }

    /**
     * Override getProductInfo() untuk menambahkan informasi garansi.
     */
    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.println("Tipe          : Elektronik");
        System.out.println("Garansi       : " + warrantyPeriod);
    }

    // ==================== GETTERS & SETTERS ====================

    public String getWarrantyPeriod()                  { return warrantyPeriod; }
    public void   setWarrantyPeriod(String warranty)   { this.warrantyPeriod = warranty; }
}