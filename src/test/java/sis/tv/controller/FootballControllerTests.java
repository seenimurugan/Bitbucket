package sis.tv.controller;

import sis.tv.config.Application;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FootballControllerTests {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void createTeamRequestWithoutTeamShouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(post("/createFootballTeam"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void gettingFootballTeamWithoutNameShouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(get("/getFootballTeamByName"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void gettingFootballTeamByNameShouldReturnSuccessStatus() throws Exception {

        this.mockMvc.perform(get("/getFootballTeamByName").param("name", "sis"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
