package computer.games.configuration;

import computer.games.user.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ivan on 25.11.2016.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Bean
    public UserDetailServiceImpl userDetailService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic()
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                //  .antMatchers("/**").access("hasRole('ROLE_ADMIN')")
                // .antMatchers("/home/**", "/login/**", "/register/**").access("hasRole('ROLE_USER')")
//                .antMatchers( "/auth/**" ).permitAll()
//                .antMatchers( "/app-content/**", "/app-services/**", "/login/**", "/home/**", "/register/**" ).permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout");
    }

}
