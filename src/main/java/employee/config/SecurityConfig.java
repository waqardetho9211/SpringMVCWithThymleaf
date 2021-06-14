package employee.config;

import javax.annotation.security.DeclareRoles;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug=true)
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@DeclareRoles({"SERVICE","ACTUATOR"})
@ComponentScan(basePackages = "employee")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll();
                //.antMatchers("/api/*").hasRole("ACTUATOR")
//                .antMatchers("/guests").hasRole("ACTUATOR")
        http.authorizeRequests().antMatchers("/guests")
                .access("hasRole('SERVICE') or hasRole('ACTUATOR')");
                //.anyRequest().hasRole("ACTUATOR")
//                .anyRequest().authenticated()
        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .jee().mappableRoles("ACTUATOR","SERVICE")
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
