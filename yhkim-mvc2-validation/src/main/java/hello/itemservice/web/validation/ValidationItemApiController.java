package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출 : add");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 = {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("검증 성공후 로직 실행");
        return form;
    }

    private final ItemRepository itemRepository;

    @PostMapping("/update/{itemId}")
    public Object updateItem(@PathVariable Long itemId, @RequestBody@Validated ItemUpdateForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출 : update");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 = {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("검증 성공후 로직 실행");

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
        itemRepository.update(itemId, item);

        return itemRepository.findById(itemId);
    }


}
