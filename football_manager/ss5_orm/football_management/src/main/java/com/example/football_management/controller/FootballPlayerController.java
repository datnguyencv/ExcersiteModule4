    package com.example.football_management.controller;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/football-management")
class FootballPlayerController {

    @Autowired
    private IFootballPlayerService footballPlayerService;

    @GetMapping("")
    public String getHome(Model model) {
        model.addAttribute("footballPlayerList", footballPlayerService.findAll());
        model.addAttribute("footballPlayer", new FootballPlayer());
        return "/index";
    }

    @PostMapping("/create")
    public String createPlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        if (footballPlayerService.create(footballPlayer)) {
            redirectAttributes.addFlashAttribute("message", "Player created successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Player not created");
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        footballPlayerService.save(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player update successfully");
        return "redirect:/update";
    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("player", footballPlayerService.findById(id));
        return "/detail";
    }

    @GetMapping("/delete")
    public String deletePlayer(@RequestParam("idDelete") Integer id, RedirectAttributes redirectAttributes) {
        this.footballPlayerService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Player deleted successfully");
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchPlayer(@RequestParam("name") String name, Model model) {
        model.addAttribute("playerList", footballPlayerService.findByName(name));
        return "/index";
    }
}
