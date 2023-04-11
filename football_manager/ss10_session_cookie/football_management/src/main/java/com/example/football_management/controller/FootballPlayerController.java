package com.example.football_management.controller;

import com.example.football_management.dto.FootballPlayerDTO;
import com.example.football_management.exception.ExceptionHandle;
import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import com.example.football_management.service.ITeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@SessionAttributes("favourites")
@RequestMapping("/football-management")
class FootballPlayerController {

    @Autowired
    private IFootballPlayerService footballPlayerService;
    @Autowired
    private ITeamsService teamsService;

    @ModelAttribute("favourites")
    public List<FootballPlayer> getFavourites(){
        return new ArrayList<>();
    }

    @GetMapping("")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "") String nameSearch,
                          @RequestParam(required = false) @DateTimeFormat(
                                  pattern = "yyyy-MM-dd") LocalDate from,
                          @RequestParam(required = false) @DateTimeFormat(
                                  pattern = "yyyy-MM-dd") LocalDate to,
                          @RequestParam(required = false) Integer pageSizeInput,
                          @PageableDefault(size = 5) Pageable pageable) throws ExceptionHandle {
//            extractedShow phương thức chung ở cuối class
        List<FootballPlayer> footballPlayers = footballPlayerService.findAllByStatusIsTrue();
        if (footballPlayers.size() > 11) {
            throw new ExceptionHandle();
        }
        model.addAttribute("count", footballPlayers.size());
        model.addAttribute("footballPlayerDTO", new FootballPlayerDTO());
        extractedShow(model, nameSearch, from, to, pageSizeInput, pageable);
        return "/index";
    }

    @GetMapping("/teams")
    public String getTeams(Model model) {
        model.addAttribute("teams", teamsService.findAll());
        return "/index";
    }

    @PostMapping("/create")
    public String createPlayer(
            @RequestParam(required = false, defaultValue = "") String nameSearch,
            @Validated @ModelAttribute("footballPlayerDTO") FootballPlayerDTO footballPlayerDTO,
            BindingResult bindingResult,
            @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd") LocalDate to,
            RedirectAttributes redirectAttributes, Model model,
            @RequestParam(required = false) Integer pageSizeInput,
            @PageableDefault(size = 5) Pageable pageable) {

        if (bindingResult.hasErrors()){
            model.addAttribute("hasErrors","true");
        } else {
            footballPlayerService.create(footballPlayerDTO);
            redirectAttributes.addFlashAttribute("message", "Player created successfully");
        }
        extractedShow(model, nameSearch, from, to, pageSizeInput, pageable);
        return "/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("footballPlayer", footballPlayerService.findById(id));
        model.addAttribute("teams", teamsService.findAll());
        return "/update";
    }

    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        teamsService.findAll();
        footballPlayerService.update(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player update successfully");
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("player", footballPlayerService.findById(id));
        Cookie cookie = new Cookie("favourites", player + "");
        cookie.setMaxAge(1 * 24 * 60 * 60);
        response.addCookie(cookie);
        model.addAttribute("player", player);
        return "/detail";
    }

    @GetMapping("/delete")
    public String deletePlayer(@ModelAttribute("footballPlayer") FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        this.footballPlayerService.delete(footballPlayer);
        redirectAttributes.addFlashAttribute("message", "Player deleted successfully");
        return "redirect:/";
    }

    private void extractedShow(Model model, String nameSearch, LocalDate from, LocalDate to, Integer pageSizeInput, Pageable pageable) {
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

        List<Integer> pageNumberList;
        int currentPage = pageable.getPageNumber() + 1;
        int totalRecords = (int) footballPlayerPage.getTotalElements();

        pageable = PageRequest.of(currentPage - 1, pageSize, sort);
        Page<?> page = new PageImpl<>(Collections.emptyList(), pageable, totalRecords);
        pageNumberList = IntStream.rangeClosed(
                1, page.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("teams", teamsService.findAll());
    }
    @ExceptionHandler(ExceptionHandle.class)
    public String showException() {
        return "errorPage";
    }
}
