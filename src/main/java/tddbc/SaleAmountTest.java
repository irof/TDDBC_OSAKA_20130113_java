package tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SaleAmountTest {
	
	   public static class お金が投入されていない状態 {
			private VendingMachine sut;
	
		    @Before
		    public void setUp() {
		        sut = new VendingMachine();
		    }
			
			@Test
			public void 売上金額の初期状態は0円である() {
				assertThat(sut.getSaleAmount(),is(0));
			}
	   }

	   public static class お金が投入されている状態 {
	        private VendingMachine sut;

	        @Before
	        public void setUp() {
	            sut = new VendingMachine();
	            sut.insert(500);
	        }

	        private void 在庫を0にする() {
				sut.purchase();
	        	sut.purchase();
	        	sut.purchase();
	        	sut.purchase();
	        	sut.purchase();
			}
	        
	        @Test
	        public void コーラの在庫がある場合に購入したら売上金額が１２０円になる() {
	        	sut.purchase();
	            assertThat(sut.getSaleAmount(), is(120));
	        }
	        
	        @Test
			public void コーラの在庫がない場合に購入したら売上金額が増えない() throws Exception {
	        	在庫を0にする();
	        	int expected = sut.getSaleAmount();//在庫をゼロにするために売上金額をメモ
	        	sut.purchase();
	        	assertThat(sut.getSaleAmount(),is(expected));
			}
	        
	        @Test
	        public void コーラを一本買って_払い戻しで380円返ってくる() {
	        	sut.purchase();
	        	sut.payback();
	            assertThat(sut.getChangeAmount(), is(380));
	        }
	   }

}
