package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import org.hibernate.validator.constraints.Range;

public class ItemSaveForm {

    private String itemName;
    private Integer price;
    private Integer quantity;

}
