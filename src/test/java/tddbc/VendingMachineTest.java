package tddbc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VendingMachineTest {

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
