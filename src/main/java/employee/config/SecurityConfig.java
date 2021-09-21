package employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug=true)
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@DeclareRoles({"ROLE_SERVICE","ROLE_ACTUATOR"})
@ComponentScan(basePackages = "employee")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication)
            throws Exception
    {
        authentication.inMemoryAuthentication().withUser("admin").password("admin").roles("ACTUATOR");
        authentication.inMemoryAuthentication().withUser("user1").password("user1").roles("USER");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
//                //.antMatchers("/api/*").hasRole("ACTUATOR")
//                .antMatchers("/guests").hasRole("ACTUATOR")
//                //.anyRequest().hasRole("ACTUATOR")
////                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .logout().invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/logout-success").permitAll()
//                .and()
//                .jee().mappableRoles("ACTUATOR","SERVICE")
//        ;
//
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll();
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

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(){
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }

}
