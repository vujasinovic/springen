/**
    ######################################## BEGIN ###############################################
     generated class: BarController.java, template used : controller.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.controller;

import rs.ftn.service.*;
import rs.ftn.dto.BarDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ftn.service.FooService;


@Controller
@RequestMapping(value = "/bars")
public class BarController {
    private static final String BASE_PAGE = "bars";
    private static final String FORM_PAGE = "createBar";
    private static final String OVERVIEW_PAGE = "bar";


    private FooService fooService;
    private BarService barService;

    @GetMapping
    public String createBar(Model model) {
        model.addAttribute("fooicis", fooService.getAll());
        model.addAttribute("bar", new BarDto());

        return FORM_PAGE;
    }

    @GetMapping("/{id}")
    public String getOverview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("fooicis", fooService.getAll());
        model.addAttribute("bar", barService.getOne(id));
        return OVERVIEW_PAGE;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("bars", barService.getAll());

        return BASE_PAGE;
    }

    @PostMapping
    public String createOrUpdateBar(final @ModelAttribute("bar") BarDto barDto) {
        barService.save(barDto);

        return "redirect:/bars/all";
    }

    @GetMapping("/edit")
    public String editBar(@RequestParam("id") Integer id, Model model) {
        final BarDto barDto = barService.getOne(id);
        model.addAttribute("fooicis", fooService.getAll());
        model.addAttribute("bar", barDto);

        return FORM_PAGE;
    }

    @GetMapping("/delete")
    public String deleteBar(@RequestParam("id") Integer id) {
        barService.deleteById(id);

        return "redirect:/bars/all";
    }

    @Autowired
    public void setFooService(FooService fooService) {
        this.fooService = fooService;
    }

    @Autowired
    public void setBarService(BarService barService) {
        this.barService = barService;
    }

}
