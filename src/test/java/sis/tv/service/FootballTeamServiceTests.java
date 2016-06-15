package sis.tv.service;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sis.tv.config.Application;
import sis.tv.exception.SISException;
import sis.tv.model.FootballTeam;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FootballTeamServiceTests {

    @Autowired
    FootballTeamService footballTeamService;

    @After
    public void deleteAllTeam() {
        footballTeamService.deleteAllFootballTeam();
    }

    @Test
    public void testCreateFootballTeam() {
        FootballTeam footballTeam = new FootballTeam("Manchester", "SIS-1", "Test-1", "user-1", 23000, (byte) 16);
        footballTeamService.saveFootballTeam(footballTeam);
        assertTrue(footballTeamService.getAllFootballTeams("name").size() == 1);
    }

    @Test
    public void testCreateFootballTeamWithSameNameTwice() {
        try {
            FootballTeam footballTeam1 = new FootballTeam("Manchester", "SIS-1", "Test-1", "user-1", 23000, (byte) 16);
            FootballTeam footballTeam2 = new FootballTeam("Manchester", "SIS-1", "Test-1", "user-1", 23000, (byte) 16);
            footballTeamService.saveFootballTeam(footballTeam1);
            footballTeamService.saveFootballTeam(footballTeam2);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Football team with this name already exists.");
        }
    }

    @Test(expected = SISException.class)
    public void testCreateFootballTeamWithoutName() {
        FootballTeam footballTeam = new FootballTeam("London", null, "Test", "user-1", 23000, (byte) 16);
        footballTeamService.saveFootballTeam(footballTeam);
    }

    @Test
    public void testGetFootballTeamByName() {
        FootballTeam footballTeam = new FootballTeam("London", "SIS-2", "Test-2", "user-2", 23001, (byte) 16);
        footballTeamService.saveFootballTeam(footballTeam);
        assertTrue(footballTeamService.getFootballTeamByName("SIS-2").getCity().equals("London"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFootballTeamByIncorrectName() {
        FootballTeam footballTeam = new FootballTeam("London", "SIS-2", "Test-2", "user-2", 23001, (byte) 16);
        footballTeamService.saveFootballTeam(footballTeam);
        assertFalse(footballTeamService.getFootballTeamByName("SIS-3").getCity().equals("London"));
    }
}
