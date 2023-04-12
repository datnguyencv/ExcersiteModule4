package com.example.football_management.controller;

import com.example.football_management.dto.FootballPlayerDTO;
import com.example.football_management.exception.ExceptionHandle;
import com.example.football_management.exception.ValidationException;
import com.example.football_management.model.FootballPlayer;
import com.example.football_management.model.Teams;
import com.example.football_management.service.IFootballPlayerService;
import com.example.football_management.service.ITeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/football")
@CrossOrigin("*")
public class FootballPlayerRestfull {
    @Autowired
    private IFootballPlayerService footballPlayerService;

    @Autowired
    private ITeamsService teamsService;

    @GetMapping
    public ResponseEntity<List<FootballPlayer>> getAllPlayers(
            @RequestParam(required = false, defaultValue = "") String nameSearch,
            @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd") LocalDate to,
            @RequestParam(required = false) Integer pageSizeInput,
            @PageableDefault(size = 5) Pageable pageable) throws ExceptionHandle {

        List<FootballPlayer> footballPlayers = footballPlayerService.findAllByStatusIsTrue();
        if (footballPlayers.size() > 11) {
            throw new ExceptionHandle();
        }

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

        List<FootballPlayer> content = footballPlayerPage.getContent();
            return ResponseEntity.ok(content);
    }

        @GetMapping("/teams")
        public ResponseEntity<Iterable<Teams>> getAllTeams() {
            Iterable<Teams> teams = teamsService.findAll();
            return ResponseEntity.ok(teams);
        }

    @GetMapping("/{id}")
    public ResponseEntity<FootballPlayer> getPlayerById(@PathVariable Integer id) {
        FootballPlayer footballPlayer = footballPlayerService.findById(id);
        return ResponseEntity.ok(footballPlayer);
    }

    @PostMapping
    public ResponseEntity<FootballPlayer> createPlayer(
            @RequestBody FootballPlayerDTO footballPlayerDTO,
            BindingResult bindingResult) throws ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation failed", bindingResult);
        }

        FootballPlayer footballPlayer = footballPlayerService.create(footballPlayerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(footballPlayer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FootballPlayer> updatePlayer(
            @PathVariable Integer id,
            @RequestBody FootballPlayer footballPlayer) {
        footballPlayer.setId(id);
        footballPlayerService.update(footballPlayer);
        return ResponseEntity.ok(footballPlayer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Void> deletePlayer(@PathVariable Integer id) {
        footballPlayerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

