package com.betbull.controller;


import com.betbull.model.*;
import com.betbull.repository.TeamRepository;
import com.betbull.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @PostMapping("/createteam")
    public TeamInfoResponse createTeamInfo(@Valid @RequestBody TeamInfoRequest request){
        return teamService.createTeam(request);
    }

    @PostMapping("/updateteam")
    public UpdateTeamInfoResponse updateTeam(@Valid @RequestBody UpdateTeamInfoRequest request) {

        return teamService.updateTeam(request);
    }

    @PostMapping("/deleteteam")
    public DeleteTeamInfoResponse deleteTeam(@Valid @RequestBody DeleteTeamInfoRequest request) {

        return teamService.deleteTeam(request);
    }

    @GetMapping("/getteamlist")
    public GetTeamListResponse getTeamList(){

        return teamService.getTeamList();
    }

    @GetMapping("/getteaminfolist")
    public List<GetTeamInfoListResponse> getTeamInfoList(){

        return teamService.getTeamInfoList();
    }

}
