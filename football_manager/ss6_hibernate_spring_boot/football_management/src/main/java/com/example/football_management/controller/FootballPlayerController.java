package com.example.football_management.controller;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/football-management")
class FootballPlayerController {

    @Autowired
    private IFootballPlayerService footballPlayerService;

    @GetMapping("/home")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "") String name,
                          @PageableDefault(size = 5) Pageable pageable) {
        Sort sort = name.isEmpty() ? Sort.by("dateOfBirth").ascending() : Sort.by("name").ascending();
        Page<FootballPlayer> footballPlayerPage = footballPlayerService
                .findByName(name, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));
        model.addAttribute("footballPlayerList", footballPlayerPage);
        model.addAttribute("footballPlayer", new FootballPlayer());
        List<Integer> pageNumberList = IntStream.rangeClosed(
                1, footballPlayerPage.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumberList", pageNumberList);
        return "/index";
    }

    @PostMapping("/create")
    public String createPlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        footballPlayerService.create(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player created successfully");

        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        footballPlayerService.update(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player update successfully");
        return "redirect:/update";
    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("player", footballPlayerService.findById(id));
        return "/detail";
    }

    @GetMapping("/delete")
    public String deletePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        this.footballPlayerService.delete(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player deleted successfully");
        return "redirect:/";
    }
}
