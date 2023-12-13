import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class CustomKey {
    public final Integer[] integers;
    public final String stringValue;

    public CustomKey(Integer[] integers, String stringValue) {
        this.integers = integers;
        this.stringValue = stringValue;
    }

    // Override hashCode and equals methods
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(integers);
        result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomKey customKey = (CustomKey) obj;
        // Check equality for both fields
        return Arrays.equals(integers, customKey.integers) &&
                (stringValue.equals(customKey.stringValue));
    }

    // Other methods as needed
}