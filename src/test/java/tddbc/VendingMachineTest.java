package tddbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class VendingMachineTest {

    public static class SimpleOperation {
        private VendingMachine sut;

        @Before
        public void setUp() {
            sut = new VendingMachine();
        }

        @Test
        public void 投入前に投入金額を取得したら0円() {
            assertThat(sut.getCreditAmount(), is(0));
        }

        @Test
        public void 投入後は投入金額が取得できる() {
            sut.insert(100);
            assertThat(sut.getCreditAmount(), is(100));
        }

        @Test
        public void 複数回投入して投入金額を取得() {
            sut.insert(100);
            sut.insert(100);
            assertThat(sut.getCreditAmount(), is(200));
        }

        @Test
        public void 在庫情報を取得できる() {
            String actual = sut.getStockText();
            assertThat(actual, is("コーラ:120円:5本"));
        }

        @Test
        public void 十分なお金を投入して購入すると在庫が減る() {
            sut.insert(1000);
            sut.purchase();
            assertThat(sut.getStockText(), is("コーラ:120円:4本"));
        }
    }

    @RunWith(Theories.class)
    public static class InsertMoneys {
        VendingMachine sut;

        @Before
        public void setUp() {
            sut = new VendingMachine();
            sut.insert(100);
        }

        @Theory
        public void 扱えるお金を投入したらその金額(@TestedOn(ints = {10, 50, 100, 500, 1000}) int amount) {
            sut.insert(amount);
            assertThat(sut.getCreditAmount(), is(100 + amount));
        }

        @Theory
        public void 扱えないお金を投入したら0円(@TestedOn(ints = {1, 5, 2000, 5000, 10000}) int amount) {
            sut.insert(amount);
            assertThat(sut.getCreditAmount(), is(100));
        }
    }
}
