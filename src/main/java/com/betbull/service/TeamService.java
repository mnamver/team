package com.betbull.service;


import com.betbull.exception.ErrorCodes;
import com.betbull.exception.TeamException;
import com.betbull.model.*;
import com.betbull.repository.TeamRepository;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamInfoResponse createTeam(TeamInfoRequest request){
        Team t  = new Team();
        TeamInfoResponse teamInfoResponse = new TeamInfoResponse();
        try {
            if(teamRepository.existsByTeamId(request.getTeamId())){
                throw new TeamException("TeamId already exist", ErrorCodes.TEAM_ID_ALREADY_EXIST);
            }else if(teamRepository.existsByTeamName(request.getTeamName())){
                throw new TeamException("TeamName already exist", ErrorCodes.TEAM_NAME_ALREADY_EXIST);
            }
            t.setTeamId(request.getTeamId());
            t.setTeamName(request.getTeamName());
            t.setCountry(request.getCountry());
            t.setCurrency(request.getCurrency());
            teamRepository.save(t);
            teamInfoResponse.setResultCode(0);
            teamInfoResponse.setResultDesc("Success");
        }catch (TeamException e){
            teamInfoResponse.setResultCode(e.getErrorCode());
            teamInfoResponse.setResultDesc(e.getMessage());
            return teamInfoResponse;
        }catch (Exception e){
            teamInfoResponse.setResultCode(ErrorCodes.GENERAL_ERROR);
            teamInfoResponse.setResultDesc("GENERAL_ERROR");
        }
    return  teamInfoResponse;
    }

    public UpdateTeamInfoResponse updateTeam(UpdateTeamInfoRequest request){
        UpdateTeamInfoResponse updateTeamInfoResponse = new UpdateTeamInfoResponse();
        try {
            if (!teamRepository.existsByTeamId(request.getTeamId())) {
                throw new TeamException("TeamId is not exist", ErrorCodes.TEAM_ID_IS_NOT_EXIST);
            }
            Team updatedTeam = teamRepository.findByTeamId(request.getTeamId());

            updatedTeam.setTeamName(request.getTeamName());
            updatedTeam.setCurrency(request.getCurrency());
            updatedTeam.setCountry(request.getCountry());

            teamRepository.save(updatedTeam);
            updateTeamInfoResponse.setResultCode(0);
            updateTeamInfoResponse.setResultDesc("Success");
        }catch (TeamException e){
            updateTeamInfoResponse.setResultCode(e.getErrorCode());
            updateTeamInfoResponse.setResultDesc(e.getMessage());
            return updateTeamInfoResponse;
        }catch (Exception e){
            updateTeamInfoResponse.setResultCode(ErrorCodes.GENERAL_ERROR);
            updateTeamInfoResponse.setResultDesc("GENERAL_ERROR");
        }

        return updateTeamInfoResponse;
    }

    @Transactional
    public DeleteTeamInfoResponse deleteTeam(DeleteTeamInfoRequest request){
        DeleteTeamInfoResponse deleteTeamInfoResponse = new DeleteTeamInfoResponse();
        try {
            if (!teamRepository.existsByTeamId(request.getTeamId())) {
                throw new TeamException("TeamId is not exist", ErrorCodes.TEAM_ID_IS_NOT_EXIST);
            }
            teamRepository.deleteByTeamId(request.getTeamId());
            deleteTeamInfoResponse.setResultCode(0);
            deleteTeamInfoResponse.setResultDesc("Success");
        }catch (TeamException e){
            deleteTeamInfoResponse.setResultCode(e.getErrorCode());
            deleteTeamInfoResponse.setResultDesc(e.getMessage());
            return deleteTeamInfoResponse;
        }catch (Exception e){
            deleteTeamInfoResponse.setResultCode(ErrorCodes.GENERAL_ERROR);
            deleteTeamInfoResponse.setResultDesc("GENERAL_ERROR");
        }

        return deleteTeamInfoResponse;

    }

    public GetTeamListResponse getTeamList(){
        GetTeamListResponse getTeamListResponse = new GetTeamListResponse();
        List<String> teamList = new ArrayList<>();
        try{
          List<Team> teams =   teamRepository.findAll();
          for(Team team: teams){
              teamList.add(team.getTeamName());
          }

            getTeamListResponse.setTeamList(teamList);
            getTeamListResponse.setResultCode(0);
            getTeamListResponse.setResultDesc("Success");
        }catch (Exception e){
            getTeamListResponse.setResultCode(ErrorCodes.GENERAL_ERROR);
            getTeamListResponse.setResultDesc("GENERAL_ERROR");
         }

        return getTeamListResponse;

    }

    public List<GetTeamInfoListResponse> getTeamInfoList(){
        GetTeamInfoListResponse getTeamInfoListResponse = null ;
        List<GetTeamInfoListResponse> teamInfoList = new ArrayList<>();

            List<Team> teams =   teamRepository.findAll();

            for(Team team: teams){
                getTeamInfoListResponse = new GetTeamInfoListResponse();
                getTeamInfoListResponse.setCurrency(team.getCurrency());
                getTeamInfoListResponse.setTeamId(team.getTeamId());
                getTeamInfoListResponse.setTeamName(team.getTeamName());
                teamInfoList.add(getTeamInfoListResponse);
            }

        return teamInfoList;

    }

}
