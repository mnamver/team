package com.betbull.repository;

import com.betbull.controller.TeamController;
import com.betbull.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Boolean existsByTeamId(Long teamId);
    Boolean existsByTeamName(String teamName);
    Boolean existsByCountry(String country);
    Team findByTeamId(Long Id);
    void deleteByTeamId(Long Id);

}
