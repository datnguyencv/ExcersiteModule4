package com.example.football_management.controller;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @PostMapping("")
//    public FootballPlayer createPlayer(Model model) {
//        model.addAttribute("footballPlayer" , footballPlayer);
//        footballPlayerService.create(FootballPlayer);
//        return "/create";
//    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("player" , footballPlayerService.playerFindById(id));
        return "/detail";
    }

    @GetMapping ("/delete")
    public String deletePlayer(@RequestParam("idDelete") Integer id) {
        this.footballPlayerService.delete(id);
        return "redirect:/";
    }
}
