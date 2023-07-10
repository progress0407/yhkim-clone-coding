package hello.springmvc.basic;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HelloData {
    private String username;
    private int age;

    public HelloData(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public HelloData() {
    }
}
