package tddbc;

public class VendingMachine {
    private int creditAmount;

    public int getCreditAmount() {
        return creditAmount;
    }

    public void insert(int insertAmount) {
        if (insertAmount == 5) {
            return;
        }
        creditAmount += insertAmount;
    }
}
