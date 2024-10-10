package com.example.EcoCamper.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.EcoCamper.dao.ShopDAO;
import com.example.EcoCamper.dao.ShopReviewDAO;
import com.example.EcoCamper.dto.ShopDTO;
import com.example.EcoCamper.entity.Shop;
import com.example.EcoCamper.entity.ShopReview;
import com.example.EcoCamper.entity.User;
import com.example.EcoCamper.jwt.TokenProvider;
import com.example.EcoCamper.service.BuylistSevice;
import com.example.EcoCamper.service.ShopReviewService;
import com.example.EcoCamper.service.ShopService;
import com.example.EcoCamper.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {

	@Autowired
	ShopService service_shop;

	@Autowired
	ShopReviewService serveice_re;

	@Autowired
	BuylistSevice service_buy;

	@Autowired
	UserService service_user;

	@Value("${project.upload.path}")
	private String uploadpath;

	@Autowired
	private TokenProvider tokenProvider;

	@GetMapping("/shop/Zest")
	public String test() {
		return "/shop/Zest";
	}

	@GetMapping("/shop/Zest2")
	public String test2() {
		return "/shop/Zest2";
	}

	@GetMapping("/shop/shopmain")
	public String productmain(HttpServletRequest request, Model model) {
		String token = tokenProvider.resolveTokenFromCookie(request);
		// System.out.println("token : " + token);
		String userId = null;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);

		}
		model.addAttribute("userId", userId);
		model.addAttribute("req", "/shop/shopmain");
		return "/index";
	}

	@GetMapping("/shop/shopbuy")
	public String productbuy(HttpServletRequest request, Model model) {
		String token = tokenProvider.resolveTokenFromCookie(request);
		// System.out.println("token : " + token);
		String userId = null;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);
			if (userId != null) {
				User user = service_user.getUser(userId);
				model.addAttribute("userId", userId);
				model.addAttribute("user", user);
			}
		}

		String pcode = request.getParameter("pcode");
		int productqty = Integer.parseInt(request.getParameter("productqty"));
		// System.out.println(productcode);

		Shop dto = service_shop.view(pcode);

		int productprice = dto.getPprice() * productqty;

		model.addAttribute("productprice", productprice);
		model.addAttribute("productqty", productqty);
		model.addAttribute("dto", dto);
		model.addAttribute("req", "/shop/shopbuy");
		return "/index";
	}

	@GetMapping("/shop/shopcart")
	public String productcart(HttpServletRequest request, Model model) {
		String token = tokenProvider.resolveTokenFromCookie(request);
		// System.out.println("token : " + token);
		String userId = null;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);
		}
		model.addAttribute("userId", userId);
		model.addAttribute("req", "/shop/shopcart");
		return "/index";
	}

	@GetMapping("/shop/shopcartbuy")
	public String shopcartbuy(HttpServletRequest request, Model model) {
		String token = tokenProvider.resolveTokenFromCookie(request);
		// System.out.println("token : " + token);
		String userId = null;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);
			User user = service_user.getUser(userId);
			model.addAttribute("user", user);
		}
		model.addAttribute("userId", userId);
		model.addAttribute("req", "/shop/shopcartbuy");
		return "/index";
	}

	@GetMapping("/shop/shopview")
	public String productview(HttpServletRequest request, Model model) {
		// just view
		String pcode = request.getParameter("pcode");
		// System.out.println(productcode);
		Shop dto = service_shop.view(pcode);

		String token = tokenProvider.resolveTokenFromCookie(request);
		String userId = null;
		boolean order = false;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);
			// pcode랑 주문내역 pcode, id가 같으면 true, pcode 비교후 true 리턴
			order = service_buy.findByPcodeAndBuyId(pcode, userId);

		}

		// review
		int pg = 1;

		if (request.getParameter("pg") != null && !request.getParameter("pg").equals("")) {
			pg = Integer.parseInt(request.getParameter("pg"));
		}

		int endNum = pg * 5;
		int startNum = endNum - 4;

		List<ShopReview> list = serveice_re.reveiwList(startNum, endNum, pcode);

		int total = (int) serveice_re.getTotal(pcode); // 총 글 수
		int totalP = (total + 4) / 5; // 총 페이지

		int startPage = (pg - 1) / 3 * 3 + 1;
		int endPage = startPage + 2;
		if (endPage > totalP)
			endPage = totalP;

		// System.out.println(total);
		// System.out.println(list);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pg", pg);
		model.addAttribute("totalP", totalP);
		model.addAttribute("list", list);

		model.addAttribute("dto", dto);

		model.addAttribute("pcode", pcode);
		model.addAttribute("userId", userId);
		model.addAttribute("order", order);
		model.addAttribute("req", "/shop/shopview");
		return "/index";
	}

	@GetMapping("/shop/shopAllList")
	public String shopAllList(Model model, HttpServletRequest request) {

		String search = request.getParameter("search");
		model.addAttribute("search", search);

		String token = tokenProvider.resolveTokenFromCookie(request);
		// System.out.println("token : " + token);
		String userId = null;
		if (token != null) {
			userId = tokenProvider.validateAndGetUserId(token);

		}
		model.addAttribute("userId", userId);

		int pg = 1;

		if (request.getParameter("pg") != null && !request.getParameter("pg").equals("")) {
			pg = Integer.parseInt(request.getParameter("pg"));
		}

		int endNum = pg * 8; // 8 16 24
		int startNum = endNum - 7; // 1 9 17

		int total = 0;
		List<Shop> list = null;
		if (search == null) {
			list = service_shop.allList(startNum, endNum);
			total = service_shop.count();
		} else {
			// System.out.println(search);

			// list=service_shop.test(search);
			list = service_shop.condiallList(startNum, endNum, search);
			total = service_shop.searchcount(search);
		}

		// 12

		int totalP = (total + 7) / 8; // 총 페이지

		int startPage = (pg - 1) / 3 * 3 + 1;
		int endPage = startPage + 2;
		if (endPage > totalP)
			endPage = totalP;

		// System.out.println(list);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pg", pg);
		model.addAttribute("totalP", totalP);
		model.addAttribute("list", list);
		System.out.println(list);
		model.addAttribute("req", "/shop/shopAllList");
		return "/index";
	}

}
