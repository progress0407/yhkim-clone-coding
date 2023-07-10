package jpa.app.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa.app.shop.domain.Member;
import jpa.app.shop.domain.Order;
import jpa.app.shop.domain.item.Item;
import jpa.app.shop.repository.OrderSearch;
import jpa.app.shop.service.ItemService;
import jpa.app.shop.service.MemberService;
import jpa.app.shop.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping("/order")
	public String createForm(Model model) {

		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();

		model.addAttribute("members", members);
		model.addAttribute("items", items);

		return "order/orderForm";
	}

	@PostMapping("/order")
	public String order(
		@RequestParam Long memberId,
		@RequestParam Long itemId,
		@RequestParam int count) {

		orderService.order(memberId, itemId, count);

		return "redirect:/orders";
	}

	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);

		return "order/orderList";
	}

	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}
