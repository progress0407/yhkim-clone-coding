package yhkim.mvc1.itemservice.web.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yhkim.mvc1.itemservice.domain.item.Item;
import yhkim.mvc1.itemservice.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";

    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV2(@ModelAttribute("item") Item item /*, Model model*/) {
        itemRepository.save(item);
//        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV3(@ModelAttribute Item item , Model model) { // model을 생략해도 잘 된다
//        Item -> item
        itemRepository.save(item);
//        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV3_2(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV4(Item item) { // 심지어 @ModelAttribute 생략 가능..
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV5(Item item) { // 심지어 @ModelAttribute 생략 가능..
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String saveItemV6(Item item, RedirectAttributes redirectAttributes) { // 심지어 @ModelAttribute 생략 가능..
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // ?status=true
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

//    @PostMapping("/{itemId}/edit")
    public String update_swcho_2(@PathVariable long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        // @ModelAttribute 의 기능으로 model 필요 없음
        return "basic/item";
    }


//    @PostMapping("/edit")
    public String update_swcho(@ModelAttribute Item item, Model model) {
        log.info("item = {}", item);
        itemRepository.update(item.getId(), item);
        model.addAttribute("model", model);
        return "basic/item";
    }

    /*
    // 에러
    @PostMapping("/add")
    public String save(@RequestBody Item item) {
        log.info("item = {}", item);
        itemRepository.save(item);
        return "basic/items";
    }
    */
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
