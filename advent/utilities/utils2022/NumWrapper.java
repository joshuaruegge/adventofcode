package advent.utilities.utils2022;

import java.util.Objects;

public class NumWrapper {

    public long num;
    public int origPos;

    public NumWrapper(long num, int origPos) {
        this.num = num;
        this.origPos = origPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumWrapper that = (NumWrapper) o;
        return num == that.num && origPos == that.origPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, origPos);
    }
}
