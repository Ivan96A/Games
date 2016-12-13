package computer.games.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Ivan on 25.11.2016.
 */
@Configuration
@EnableWebSecurity

public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/**" ).permitAll();
    }

}
