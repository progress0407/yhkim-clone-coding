package yhkim.mvc1.itemservice.domain.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {

    public static Map<Long, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put(1L, "one");
        map.put(2L, "two");
        map.forEach((k,v)->{
            System.out.printf("k=%d, v=%s \n",k,v);
        });
        boolean containsValue = map.containsValue("two2");
        System.out.println("containsValue = " + containsValue);
        map.values();
        System.out.println("map.values() = " + map.values());
        Optional<String> any = map.values().stream().findAny();
        System.out.println("any.get() = " + any.get());
        List<String> two = map.values().stream().filter(val -> val.equals("two")).collect(Collectors.toList());
        System.out.println("two = " + two);
        map.replace(2L, "twotwo");
        System.out.println("map.get(2L) = " + map.get(2L));
    }
}
