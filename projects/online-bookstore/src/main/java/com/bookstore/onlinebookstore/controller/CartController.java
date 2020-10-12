package com.bookstore.onlinebookstore.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstore.onlinebookstore.model.Address;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.User;
import com.bookstore.onlinebookstore.model.forms.AddressForm;
import com.bookstore.onlinebookstore.service.AddressService;
import com.bookstore.onlinebookstore.service.BookService;
import com.bookstore.onlinebookstore.service.CartService;
import com.bookstore.onlinebookstore.service.OrderService;
import com.bookstore.onlinebookstore.service.OrderedBookService;
import com.bookstore.onlinebookstore.service.PaymentService;
import com.bookstore.onlinebookstore.service.ShoppingCartService;
import com.bookstore.onlinebookstore.service.UserService;

@Controller
@RequestMapping("/cart")
@SessionAttributes({ "cart", "userAddress" })
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderedBookService orderedBookService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@PostMapping("/cart.do")
	public String cartItem(ModelMap modelMap, HttpServletRequest request) {
		cartService.addItemToCart(modelMap, request);
		return "redirect:/cart/view";
	}

	@PostMapping("/update")
	public String updateItem(ModelMap modelMap, HttpServletRequest request) {
		cartService.updateItem(modelMap, request);
		return "redirect:/cart/view";
	}

	@RequestMapping("/view")
	public String getShoppingCart(ModelMap modelMap) {
		return "shopping-cart";
	}

	@RequestMapping("/checkout")
	@PostMapping("/checkout")
	public String checkoutCart(ModelMap modelMap, HttpServletRequest request, AddressForm addressForm) {
		// if shopping cart is empty redirect user to the home page
		// mostly recently used shipping address
		User user = (User) request.getSession().getAttribute("user");
		addressService.getRecentlyUsedAddress(modelMap, user.getId());
		return "checkout";
	}

	/* ******* Generate a BrainTreeGateway token for payment transaction ****** */
	@RequestMapping(value = "/payment/token", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, String> getClientToken() {
		return paymentService.getClientToken();
	}

	@PostMapping("/address/add")
	public String addNewShippingAddress(ModelMap modelMap, @Valid AddressForm addressForm, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			System.out.println(
					"--------------------------Error: Adding New Shipping Address-----------------------------");
			System.out.println(bindingResult.getFieldError());
			return "/cart/checkout";
		}
		User user = (User) request.getSession().getAttribute("user");
		addressService.addAddress(addressForm, user.getId());

		return "redirect:/cart/checkout";
	}

	@PostMapping("/address/update")
	public String updateShippingAddress(ModelMap modelMap, @Valid AddressForm addressForm, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			System.out.println("--------------------------Error: Update Shipping Address-----------------------------");
			System.out.println(bindingResult.getFieldError());
			return "redirect:/cart/checkout";
		}
		User user = (User) request.getSession().getAttribute("user");
		addressService.updateAddress(addressForm, user.getId());
		return "redirect:/cart/checkout";
	}

	@PostMapping("/place-order")
	public String placeOrder(@RequestParam("payment_method_nonce") String paymentMethodNonce, ModelMap modelMap,
			HttpServletRequest request, RedirectAttributes redirectAttr) {
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = (Cart) modelMap.get("cart");

		Address address = (Address) request.getSession().getAttribute("userAddress");
		Long orderId = orderService.placeOrder(modelMap, user.getId(), cart.getTotalCost(), address.getAddressId());

		Boolean paymentSuccessful = paymentService.makePayment(orderId, cart.getTotalCost(), paymentMethodNonce);
		if (paymentSuccessful) {
			String hash = orderService.getOrderHash(orderId);
			// *****************************************
			orderService.updatePayed(orderId);
			orderedBookService.insert(cart, orderId);
			shoppingCartService.clearUserCart(user.getId(), cart, request, modelMap);
			bookService.updateBookStock(cart);
			userService.sendOrderDetailsEmail(hash, user);
			// *****************************************
			redirectAttr.addFlashAttribute("orderId", orderId);
			return "redirect:/account/order-details?orderID=" + hash;
		} else {
			redirectAttr.addFlashAttribute("PAYMENT_METHOD_ERROR_MESSAGE_1", "Error! Your paymethod was declined.");
			redirectAttr.addFlashAttribute("PAYMENT_METHOD_ERROR_MESSAGE_2",
					"Please try again or choose a different payment method.");
			return "redirect:/cart/checkout";
		}
	}
}