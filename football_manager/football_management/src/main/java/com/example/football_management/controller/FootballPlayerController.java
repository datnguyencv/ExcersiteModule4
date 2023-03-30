package com.example.football_management.controller;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/football-management")
public class FootballPlayerController {

    @Autowired
    private IFootballPlayerService footballPlayerService;

    @GetMapping("")
    public String PlayerList(Model model) {
        model.addAttribute("footballPlayerList", footballPlayerService.findAll());
        return "/list";
    }

    @PostMapping("/create")
    public String createPlayer(
            @ModelAttribute("newPlayer") FootballPlayer footballPlayer,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
         footballPlayerService.create(footballPlayer);
         redirectAttributes.addFlashAttribute("message", "Player created successfully");
         return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("player" , footballPlayerService.findById(id));
        return "/detail";
    }

    @GetMapping ("/delete")
    public String deletePlayer(@RequestParam("idDelete") Integer id) {
        this.footballPlayerService.delete(id);
        return "redirect:/";
    }
}
