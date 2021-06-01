package employee.config;

import javax.annotation.security.DeclareRoles;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug=true)
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@DeclareRoles({"ROLE_SERVICE","ROLE_ACTUATOR"})
@ComponentScan(basePackages = "employee")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/*").hasRole("ACTUATOR")
                .antMatchers("/api_test/*").hasRole("ACTUATOR")
                //.anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .jee().mappableRoles("ACTUATOR","SERVICE")
        ;


    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) {
//                    return new User(username, "",
//                                    AuthorityUtils
//                                            .commaSeparatedStringToAuthorityList("testing"));
//            }
//        };
//    }



}
