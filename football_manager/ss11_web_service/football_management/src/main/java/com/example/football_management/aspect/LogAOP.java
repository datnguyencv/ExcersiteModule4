package com.example.football_management.aspect;

import com.example.football_management.dto.FootballPlayerDTO;
import com.example.football_management.model.FootballPlayer;
import com.example.football_management.service.IFootballPlayerService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAOP {
    @Autowired
    private IFootballPlayerService footballPlayerService;

    @Pointcut("execution(* com.example.football_management.controller.FootballPlayerController.update(..))&&args(footballPlayerDTO,*,*,*)")
    public void getAllUpdate(FootballPlayerDTO footballPlayerDTO) {
    }

    @After(value = "getAllUpdate(footballPlayerDTO)", argNames = "footballPlayerDTO")
    public void printUpdate(FootballPlayerDTO footballPlayerDTO) {
        int count = 0;
        for (FootballPlayer footballPlayer : footballPlayerService.findAll()) {
            if (footballPlayer.getStatus()) {
                count++;
            }
        }

        System.out.println("Player's Status" + footballPlayerDTO.getName() + " has been converted to " + footballPlayerDTO.isStatus());
        System.out.println("The number of registered players is: " + count);
    }
}
