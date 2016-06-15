package sis.tv.service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sis.tv.exception.SISException;
import sis.tv.model.FootballTeam;
import sis.tv.repository.FootballTeamRepository;

@Service
public class FootballTeamService {

    protected final Logger LOG = LoggerFactory.getLogger(FootballTeamService.class);

    @Autowired
    private FootballTeamRepository footballTeamRepository;

    public FootballTeam createFootballTeamFromRequestParam(Map<String, Object> requestParam) {

        String name = (String) requestParam.get("name");
        String city = (String) requestParam.get("city");
        String owner = (String) requestParam.get("owner");
        String competition = (String) requestParam.get("competition");
        Integer numberOfPlayers = (Integer) requestParam.get("numberOfPlayers");
        Integer stadiumCapacity = (Integer) requestParam.get("capacity");

        return new FootballTeam(city, name, competition, owner, stadiumCapacity, numberOfPlayers.byteValue());
    }

    public void saveFootballTeam(FootballTeam footballTeam) {
        validateFootballTeamName(footballTeam);
        footballTeamRepository.save(footballTeam);
    }

    public void validateFootballTeamName(FootballTeam footballTeam) {
        String name = footballTeam.getName();
        LOG.debug("validating football team name {}", name);
        String validationError = (name == null || name.isEmpty()) ? "Please provide Football team name."
                : footballTeamRepository.findByName(name) != null ? "Football team with this name already exists." : null;
        if (validationError != null) {
            throw new SISException(validationError);
        }
    }

    public Set<FootballTeam> getAllFootballTeams(String sortBy) {
        sortBy = (sortBy == null || sortBy.isEmpty()) ? "name" : sortBy.equalsIgnoreCase("capacity") ? "stadiumCapacity" : sortBy;
        Set<FootballTeam> footballTeams = new LinkedHashSet<>();
        for (FootballTeam footballTeam : footballTeamRepository.findAll(new Sort(Sort.Direction.ASC, sortBy))) {
            footballTeams.add(footballTeam);
        }
        return footballTeams;
    }

    public FootballTeam getFootballTeamByName(String name) {
        return footballTeamRepository.findByName(name);
    }
    
    public void deleteAllFootballTeam() {
        footballTeamRepository.deleteAll();
    }

}
