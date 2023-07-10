package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
// 기능이 너무 약하다 !
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

       @NotBlank(message = "{0}, 공백을 허용하지 않습니다", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;


    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
//    @Min(value = 1000, groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
