package tddbc;

public class VendingMachine {
    private static final int[] ALLOW_MONEYS = {10, 50, 100, 500, 1000};
    private final int PRICE = 120;

    private int creditAmount;
    private int number = 5;
    private int changeAmount;
    private int saleAmount;

    public int getCreditAmount() {
        return creditAmount;
    }

    public void insert(int insertAmount) {
    	if(isAllow(insertAmount)){
        	creditAmount += insertAmount;
        } else {
        	changeAmount += insertAmount;
        }
    }

	private boolean isAllow(int insertAmount) {
		boolean b = false;
        for (int allowMoney : ALLOW_MONEYS) {
            if (allowMoney == insertAmount) {
                b = true;
            }
        }
		return b;
	}

    public String getStockText() {
        return String.format("コーラ:%d円:%d本", PRICE, number);
    }

    public void purchase() {
        if (canPurcase()) {
            number--;
            creditAmount -= PRICE;
            saleAmount += PRICE;
        }
    }

	private boolean canPurcase() {
		return isRich() && hasStock();
	}

	private boolean hasStock() {
		return number > 0;
	}

	private boolean isRich() {
		return creditAmount >= PRICE;
	}

    public int getChangeAmount() {
        return changeAmount;
    }

    public void payback() {
        changeAmount += creditAmount;
        creditAmount = 0;
    }

	public int getSaleAmount() {
		return this.saleAmount;
	}
}
