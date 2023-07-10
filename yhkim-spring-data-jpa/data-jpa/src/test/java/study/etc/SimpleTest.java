package study.etc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    void 동일성_동등성() {
        String s1 = new String("foo");
        String s2 = new String("foo");

        Assertions.assertThat(s1).isEqualTo(s2); // 동등성
        Assertions.assertThat(s1 == s2).isFalse(); // 동일성
    }
}
