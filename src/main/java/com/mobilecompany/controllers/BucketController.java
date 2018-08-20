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

/**
 * The Bucket controller.
 */
@Controller
public class BucketController {

    private static Logger LOGGER = LoggerFactory.getLogger(BucketController.class);

    private CartService cartService;
    private ContractService contractService;

    /**
     * Instantiates a new Bucket controller.
     *
     * @param cartService     the cart service
     * @param contractService the contract service
     */
    @Autowired
    public BucketController(CartService cartService, ContractService contractService) {
        this.cartService = cartService;
        this.contractService = contractService;
    }

    /**
     * Gets bucket.
     *
     * @param request the request
     * @param model   the model
     * @return the bucket page
     */
    @RequestMapping(value = "/bucket", method = RequestMethod.GET)
    public String getBucket(HttpServletRequest request, Model model) {
        LOGGER.info("Returning bucket page");
        model.addAttribute("bucket", request.getSession().getAttribute("bucket"));
        model.addAttribute("contractChanges", request.getSession().getAttribute("contractChanges"));
        model.addAttribute("contract", request.getSession().getAttribute("contract"));
        model.addAttribute("orderResult", request.getSession().getAttribute("orderResult"));
        return "/bucket";
    }

    /**
     * Add product to bucket.
     *
     * @param contractChanges the contract changes
     * @param ids             the ids of new options
     * @param contractId      the contract id
     * @param request         the request
     * @return the Bucket Page
     */
    @RequestMapping(value = "/bucket/product/{contractId}", method = RequestMethod.POST)
    public String addProductToBucket(@ModelAttribute("contractChanges") ContractChanges contractChanges, String ids,
                                     @PathVariable(name = "contractId") Integer contractId, HttpServletRequest request) {
        LOGGER.info("Adding products to bucket");
        Object bucket = request.getSession().getAttribute("bucket");

        if (bucket == null) {
            BucketChanges bucketChanges = new BucketChanges();
            cartService.addToCart(bucketChanges, contractId, contractChanges, ids);
            request.getSession().setAttribute("bucket", bucketChanges);
            request.getSession().setAttribute("contractChanges", contractChanges);
            request.getSession().setAttribute("contract", contractService.getContract(contractId));
            request.getSession().setAttribute("orderResult", contractService.getOrderResult(contractChanges.getTariffId(),
                    contractChanges.getOptionsIds1()));
        } else {
            cartService.addToCart((BucketChanges) bucket, contractId, contractChanges, ids);
            request.getSession().setAttribute("contractChanges", contractChanges);
            request.getSession().setAttribute("contract", contractService.getContract(contractId));
            request.getSession().setAttribute("orderResult", contractService.getOrderResult(contractChanges.getTariffId(),
                    contractChanges.getOptionsIds1()));
        }
        return "redirect: /bucket";
    }
}
