package tddbc;

public class VendingMachine {
    private static final int[] ALLOW_MONEYS = {10, 50, 100, 500, 1000};

    private int creditAmount;

    public int getCreditAmount() {
        return creditAmount;
    }

    public void insert(int insertAmount) {
        for (int allowMoney : ALLOW_MONEYS) {
            if (allowMoney == insertAmount) {
                creditAmount += insertAmount;
            }
        }
    }

    public String getStockText() {
        return null;
    }
}
