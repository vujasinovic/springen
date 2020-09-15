/**
    ######################################## BEGIN ###############################################
     generated class: FooController.java, template used : controller.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.controller;

import rs.ftn.service.*;
import rs.ftn.dto.FooDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ftn.service.BarService;


@Controller
@RequestMapping(value = "/foos")
public class FooController {
    private static final String BASE_PAGE = "foos";
    private static final String FORM_PAGE = "createFoo";
    private static final String OVERVIEW_PAGE = "foo";


    private BarService barService;
    private FooService fooService;

    @GetMapping
    public String createFoo(Model model) {
        model.addAttribute("bars", barService.getAll());
        model.addAttribute("foo", new FooDto());

        return FORM_PAGE;
    }

    @GetMapping("/{id}")
    public String getOverview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bars", barService.getAll());
        model.addAttribute("foo", fooService.getOne(id));
        return OVERVIEW_PAGE;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("foos", fooService.getAll());

        return BASE_PAGE;
    }

    @PostMapping
    public String createOrUpdateFoo(final @ModelAttribute("foo") FooDto fooDto) {
        fooService.save(fooDto);

        return "redirect:/foos/all";
    }

    @GetMapping("/edit")
    public String editFoo(@RequestParam("id") Integer id, Model model) {
        final FooDto fooDto = fooService.getOne(id);
        model.addAttribute("bars", barService.getAll());
        model.addAttribute("foo", fooDto);

        return FORM_PAGE;
    }

    @GetMapping("/delete")
    public String deleteFoo(@RequestParam("id") Integer id) {
        fooService.deleteById(id);

        return "redirect:/foos/all";
    }

    @Autowired
    public void setBarService(BarService barService) {
        this.barService = barService;
    }

    @Autowired
    public void setFooService(FooService fooService) {
        this.fooService = fooService;
    }

}
