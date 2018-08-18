package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.BucketChanges;
import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.services.api.CartService;
import com.mobilecompany.services.api.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BucketController {

    private static Logger LOGGER = LoggerFactory.getLogger(BucketController.class);

    private CartService cartService;
    private ContractService contractService;

    @Autowired
    public BucketController(CartService cartService, ContractService contractService) {
        this.cartService = cartService;
        this.contractService = contractService;
    }

    @RequestMapping(value = "/bucket", method = RequestMethod.GET)
    public String getBucket(HttpServletRequest request, Model model) {
        LOGGER.info("Returning bucket page");
        model.addAttribute("bucket", request.getSession().getAttribute("bucket"));
        model.addAttribute("contractChanges", request.getSession().getAttribute("contractChanges"));
        model.addAttribute("contract", request.getSession().getAttribute("contract"));
        model.addAttribute("orderResult", request.getSession().getAttribute("orderResult"));
        return "/bucket";
    }

    @RequestMapping(value = "/bucket/product/{contractId}", method = RequestMethod.POST)
    public String addProductToBucket(@ModelAttribute("contractChanges") ContractChanges contractChanges, String ids,
                                     @PathVariable(name = "contractId") Integer contractId, HttpServletRequest request) {
        LOGGER.info("Adding products to bucket");
        LOGGER.info("qweqwe" + ids);
        Object bucket = request.getSession().getAttribute("bucket");
        if (bucket == null) {
            BucketChanges bucketChanges = new BucketChanges();
            cartService.addToCart(bucketChanges, contractId, contractChanges);
            request.getSession().setAttribute("bucket", bucketChanges);
            request.getSession().setAttribute("contractChanges", contractChanges);
            request.getSession().setAttribute("contract", contractService.getContract(contractId));
            request.getSession().setAttribute("orderResult", contractService.getOrderResult(contractChanges.getTariffId(),
                    contractChanges.getOptionsIds()));
        } else {
            cartService.addToCart((BucketChanges) bucket, contractId, contractChanges);
            request.getSession().setAttribute("contractChanges", contractChanges);
            request.getSession().setAttribute("contract", contractService.getContract(contractId));
            request.getSession().setAttribute("orderResult", contractService.getOrderResult(contractChanges.getTariffId(),
                    contractChanges.getOptionsIds()));
        }
        return "redirect: /bucket";
    }
}
