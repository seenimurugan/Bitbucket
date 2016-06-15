package sis.tv.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sis.tv.model.FootballTeam;

public interface FootballTeamRepository extends PagingAndSortingRepository<FootballTeam, Long> {

    FootballTeam findByName(String name);
}
