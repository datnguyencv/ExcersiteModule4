package com.example.football_management.controller;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import com.example.football_management.service.ITeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/football-management")
class FootballPlayerController {

    @Autowired
    private IFootballPlayerService footballPlayerService;
    @Autowired
    private ITeamsService teamsService;

    @GetMapping("")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "") String nameSearch,
                          @RequestParam(required = false) @DateTimeFormat(
                                  pattern = "yyyy-MM-dd") LocalDate from,
                          @RequestParam(required = false) @DateTimeFormat(
                                  pattern = "yyyy-MM-dd") LocalDate to,
                          @RequestParam(required = false) Integer pageSizeInput,
                          @PageableDefault(size = 5) Pageable pageable) {

        int pageSize = pageable.getPageSize();
        if (pageSizeInput != null && pageSizeInput > 0) {
            pageSize = pageSizeInput;
        }

        Sort sort = nameSearch.isEmpty() ? Sort.by("dateOfBirth").ascending() : Sort.by("name").ascending();
        Page<FootballPlayer> footballPlayerPage;
        if (from != null && to != null) {
            String fromStr = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String toStr = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            footballPlayerPage = footballPlayerService.findByDateOfBirthBetweenAndNameContaining(
                    fromStr, toStr, nameSearch, PageRequest.of(
                            pageable.getPageNumber(), pageSize, sort));
        } else {
            footballPlayerPage = footballPlayerService.findAllByNames(
                    nameSearch, PageRequest.of(pageable.getPageNumber(), pageSize, sort));
        }
        model.addAttribute("footballPlayerList", footballPlayerPage);
        model.addAttribute("footballPlayer", new FootballPlayer());

        List<Integer> pageNumberList;
        int currentPage = pageable.getPageNumber() + 1;
        int totalRecords = (int) footballPlayerPage.getTotalElements();

        pageable = PageRequest.of(currentPage - 1, pageSize, sort);
        Page<?> page = new PageImpl<>(Collections.emptyList(), pageable, totalRecords);
        pageNumberList = IntStream.rangeClosed(
                1, page.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumberList", pageNumberList);
        return "/index";
    }

    @PostMapping("/create")
    public String createPlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        teamsService.findAll();
        footballPlayerService.create(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player created successfully");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        teamsService.findAll();
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
