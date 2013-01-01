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
        public void 無効なお金は受け付けない() {
            sut.insert(5);
            assertThat(sut.getCreditAmount(), is(0));
        }
    }

    @RunWith(Theories.class)
    public static class InsertMoneys {

        @Theory
        public void 扱えるお金を投入したらその金額(@TestedOn(ints = {10, 50, 100, 500, 1000}) int amount) {
            VendingMachine sut = new VendingMachine();
            sut.insert(amount);
            assertThat(sut.getCreditAmount(), is(amount));
        }

        @Theory
        public void 扱えないお金を投入したら0円(@TestedOn(ints = {1, 5, 2000, 5000, 10000}) int amount) {
            VendingMachine sut = new VendingMachine();
            sut.insert(amount);
            assertThat(sut.getCreditAmount(), is(0));
        }
    }
}
