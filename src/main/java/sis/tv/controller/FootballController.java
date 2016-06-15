package sis.tv.controller;

import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sis.tv.model.FootballTeam;
import sis.tv.service.FootballTeamService;
import sis.tv.util.JsonResponse;

@RestController
public class FootballController extends AbstractController {

    @Autowired
    private FootballTeamService footballTeamService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/createFootballTeam")
    public JsonResponse createFootballTeam(HttpServletRequest request, @RequestBody Map<String, Object> requestParam) {
        try {
            FootballTeam footballTeam = footballTeamService.createFootballTeamFromRequestParam(requestParam);
            LOG.debug("footballTeam: {}", footballTeam);
            footballTeamService.saveFootballTeam(footballTeam);
            JsonResponse jsonResponse;
            jsonResponse = getSuccessJsonResponse("Football team created successfully.");
            jsonResponse.getResult().put("footballTeam", footballTeam);
            return jsonResponse;
        } catch (Exception ex) {
            LOG.error("Exception occured while creating the Football team: ", ex);
            return getExceptionJsonResponse(ex, "Football team can not be created.");
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getAllFootballTeams")
    public JsonResponse getAllFootballTeams(@RequestParam(value = "sort", required = false, defaultValue = "name") String sortBy) {
        try {
            Set<FootballTeam> footballTeams = footballTeamService.getAllFootballTeams(sortBy);
            JsonResponse jsonResponse;
            if (footballTeams.size() > 0) {
                jsonResponse = getSuccessJsonResponse("Football teams retrived successfully.");
                jsonResponse.getResult().put("footballTeams", footballTeams);
            } else {
                jsonResponse = getSuccessJsonResponse("Could not find any football team.");
            }
            return jsonResponse;
        } catch (Exception ex) {
            LOG.error("Exception occured while getting all the Football teams: ", ex);
            return getExceptionJsonResponse(ex, "Internel error occured. Please try again.");
        }

    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getFootballTeamByName")
    public JsonResponse getFootballTeamByName(
            @RequestParam(value = "name", required = true) String footballTeamName) {
        try {
            JsonResponse jsonResponse;
            FootballTeam footballTeam = footballTeamService.getFootballTeamByName(footballTeamName);
            if (footballTeam != null) {
                jsonResponse = getSuccessJsonResponse("Football team retrived successfully.");
                jsonResponse.getResult().put("footballTeam", footballTeam);
            } else {
                jsonResponse = getSuccessJsonResponse("Could not find football team.");
            }
            return jsonResponse;
        } catch (Exception ex) {
            LOG.error("Exception occured while getting the Football team: ", ex);
            return getExceptionJsonResponse(ex, "Internel error occured. Please try again.");
        }

    }
}
