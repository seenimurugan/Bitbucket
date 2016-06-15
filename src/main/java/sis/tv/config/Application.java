package sis.tv.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = {"sis.tv.controller", "sis.tv.service"})
@EnableJpaRepositories("sis.tv.repository")
@EntityScan(basePackages = {"sis.tv.model"})
public class Application extends SpringBootServletInitializer {

//    private static Class<Application> applicationClass = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        /**
         * This section defines the user accounts which can be used for
         * authentication as well as the roles each user has.
         *
         * @see
         * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().//
                    withUser("sis").password("password").roles("USER").and().//
                    withUser("admin").password("password").roles("USER", "ADMIN");
        }

        /**
         * This section defines the security policy for the app.
         * <p>
         * <ul>
         * <li>BASIC authentication is supported (enough for this REST-based
         * demo).</li>
         * <li>/employees is secured using URL security shown below.</li>
         * <li>CSRF headers are disabled since we are only testing the REST
         * interface, not a web one.</li>
         * </ul>
         * NOTE: GET is not shown which defaults to permitted.
         *
         * @param http
         * @throws Exception
         * @see
         * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic().and().authorizeRequests().//
                    antMatchers(HttpMethod.POST, "/createFootballTeam").hasRole("ADMIN").//
                    antMatchers(HttpMethod.GET, "/getAllFootballTeams").hasRole("USER").//
                    antMatchers(HttpMethod.GET, "/getFootballTeamByName").hasRole("USER").and().//
                    csrf().disable();
        }
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
}
