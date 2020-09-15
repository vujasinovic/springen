/**
    ######################################## BEGIN ###############################################
     generated class: HomeController.java, template used : controller.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.base.child.controller;

import rs.ftn.base.child.service.*;
import rs.ftn.base.child.dto.HomeDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ftn.service.AddressService;
import rs.ftn.service.UserService;


@Controller
@RequestMapping(value = "/homes")
public class HomeController {
    private static final String BASE_PAGE = "homes";
    private static final String FORM_PAGE = "createHome";
    private static final String OVERVIEW_PAGE = "home";


    private AddressService addressService;
    private UserService userService;
    private HomeService homeService;

    @GetMapping
    public String createHome(Model model) {
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("userses", userService.getAll());
        model.addAttribute("home", new HomeDto());

        return FORM_PAGE;
    }

    @GetMapping("/{id}")
    public String getOverview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("userses", userService.getAll());
        model.addAttribute("home", homeService.getOne(id));
        return OVERVIEW_PAGE;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("homes", homeService.getAll());

        return BASE_PAGE;
    }

    @PostMapping
    public String createOrUpdateHome(final @ModelAttribute("home") HomeDto homeDto) {
        homeService.save(homeDto);

        return "redirect:/homes/all";
    }

    @GetMapping("/edit")
    public String editHome(@RequestParam("id") Integer id, Model model) {
        final HomeDto homeDto = homeService.getOne(id);
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("userses", userService.getAll());
        model.addAttribute("home", homeDto);

        return FORM_PAGE;
    }

    @GetMapping("/delete")
    public String deleteHome(@RequestParam("id") Integer id) {
        homeService.deleteById(id);

        return "redirect:/homes/all";
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

}
