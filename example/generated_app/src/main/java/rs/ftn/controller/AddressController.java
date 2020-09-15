/**
    ######################################## BEGIN ###############################################
     generated class: AddressController.java, template used : controller.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.controller;

import rs.ftn.service.*;
import rs.ftn.dto.AddressDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value = "/addresses")
public class AddressController {
    private static final String BASE_PAGE = "addresses";
    private static final String FORM_PAGE = "createAddress";
    private static final String OVERVIEW_PAGE = "address";


    private AddressService addressService;

    @GetMapping
    public String createAddress(Model model) {
        model.addAttribute("address", new AddressDto());

        return FORM_PAGE;
    }

    @GetMapping("/{id}")
    public String getOverview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("address", addressService.getOne(id));
        return OVERVIEW_PAGE;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("addresses", addressService.getAll());

        return BASE_PAGE;
    }

    @PostMapping
    public String createOrUpdateAddress(final @ModelAttribute("address") AddressDto addressDto) {
        addressService.save(addressDto);

        return "redirect:/addresses/all";
    }

    @GetMapping("/edit")
    public String editAddress(@RequestParam("id") Integer id, Model model) {
        final AddressDto addressDto = addressService.getOne(id);
        model.addAttribute("address", addressDto);

        return FORM_PAGE;
    }

    @GetMapping("/delete")
    public String deleteAddress(@RequestParam("id") Integer id) {
        addressService.deleteById(id);

        return "redirect:/addresses/all";
    }


    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

}
