/**
    ######################################## BEGIN ###############################################
     generated class: UserController.java, template used : controller.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.controller;

import rs.ftn.service.*;
import rs.ftn.dto.UserDto;

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
import rs.ftn.base.child.service.HomeService;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    private static final String BASE_PAGE = "users";
    private static final String FORM_PAGE = "createUser";
    private static final String OVERVIEW_PAGE = "user";


    private AddressService addressService;
    private HomeService homeService;
    private UserService userService;

    @GetMapping
    public String createUser(Model model) {
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("homes", homeService.getAll());
        model.addAttribute("user", new UserDto());

        return FORM_PAGE;
    }

    @GetMapping("/{id}")
    public String getOverview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("homes", homeService.getAll());
        model.addAttribute("user", userService.getOne(id));
        return OVERVIEW_PAGE;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());

        return BASE_PAGE;
    }

    @PostMapping
    public String createOrUpdateUser(final @ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);

        return "redirect:/users/all";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Integer id, Model model) {
        final UserDto userDto = userService.getOne(id);
        model.addAttribute("addresses", addressService.getAll());
        model.addAttribute("homes", homeService.getAll());
        model.addAttribute("user", userDto);

        return FORM_PAGE;
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        userService.deleteById(id);

        return "redirect:/users/all";
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
