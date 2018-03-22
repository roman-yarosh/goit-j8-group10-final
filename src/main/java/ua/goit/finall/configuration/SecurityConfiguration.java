package ua.goit.finall.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        System.out.println("Password 123 encoded : " + passwordEncoder.encode("123"));
        auth.
             //   inMemoryAuthentication().withUser("test").password("test").roles("ADMIN");
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers( "/employeeInfo/**").hasAnyRole("USER","MODERATOR","ADMIN")
                .antMatchers( "/api/personalSalaries/**").hasAnyRole("USER","MODERATOR","ADMIN")
                .antMatchers( "/api/employees/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/departments/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/events/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/eventTypes/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/positions/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/salaries/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/statuses/**").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers( "/api/users/**").hasAnyRole("ADMIN")
                .antMatchers( "/api/roles/**").hasAnyRole("ADMIN")
                .antMatchers( "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                // Config for Login Form
                .and()
                .formLogin()//
                // Submit URL of login page.
                //.loginProcessingUrl("/j_spring_security_check") // Submit URL
                //.loginPage("/login")//when custom login form will be ready
                .permitAll()
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                //.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error403");

    }

/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources*//**", "/static*//**", "/css*//**", "/js*//**", "/images*//**");
    }*/
}

/*
* http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/home")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
* */