package computer.games.configuration;

import computer.games.user.service.UserService;
import computer.games.user.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Created by Ivan on 25.11.2016.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

   @Autowired
   UserDetailsService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder( 10 );
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                                        .userDetailsService(userDetailService)
                                        .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                //.httpBasic()
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/**" ).permitAll()
//                .antMatchers( "/auth/**" ).permitAll()
//                .antMatchers( "/app-content/**", "/app-services/**", "/login/**", "/home/**", "/register/**" ).permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl( "/logout" )
                .permitAll();

    }

}
