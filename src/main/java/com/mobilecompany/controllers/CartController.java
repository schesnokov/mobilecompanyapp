package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.services.api.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/bucket", method = RequestMethod.GET)
    public String getCart(HttpServletRequest request, Model model) {
        model.addAttribute("bucket", request.getSession().getAttribute("bucket"));
        return "/bucket";
    }

    @RequestMapping(value = "/bucket/product/{contractId}", method = RequestMethod.POST)
    public String addProductToBucket(@ModelAttribute("contractChanges") ContractChanges contractChanges,
                                   @PathVariable(name = "contractId") Integer contractId, HttpServletRequest request) {
        Object bucket = request.getSession().getAttribute("bucket");
        if (bucket == null) {
            Map<Integer, ContractChanges> changes = new HashMap<>();
            cartService.addToCart(changes, contractId, contractChanges);
            request.getSession().setAttribute("bucket", changes);
        } else {
            cartService.addToCart((Map<Integer, ContractChanges>) bucket, contractId, contractChanges);
        }
        return "redirect: /bucket";
    }
}
