package com.example.clientsservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.clientsservice.models.User.Role.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImplement userDetailsService;

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeRequests()
                .antMatchers("/registration", "/login", "/resources/**").permitAll()
                .antMatchers("/", "/main").hasAnyAuthority(USER.name(), ADMIN.name())
                .antMatchers("/users", "/accounts").hasAnyAuthority(ADMIN.name())       // UPDATE `user_role` SET `role`="ADMIN" WHERE user_id = 1
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login");
    }



//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/registration", "/login", "/resources/**").permitAll()
//                .antMatchers("/", "/main").hasAnyAuthority(USER.name(), ADMIN.name())
//                .antMatchers("/users", "/accounts").hasAnyAuthority(ADMIN.name())       // UPDATE `user_role` SET `role`="ADMIN" WHERE user_id = 1
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login");
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("a").password("p").roles(ADMIN.name()).
//                and().
//                withUser("u").password("p").roles(USER.name());
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers(
//                "/js/**",
//                "/style.css",
//                "/*.css",
//                "/*.js"
//                );
//    }




//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                //Доступ только для не зарегистрированных пользователей
//                .antMatchers("/registration").not().fullyAuthenticated()
//                //Доступ разрешен всем пользователей
//                .antMatchers("/", "/users", "/resources/**").permitAll()
//                //Все остальные страницы требуют аутентификации
//                .anyRequest().authenticated()
//                .and()
//                //Настройка для входа в систему
//                .formLogin()
//                .loginPage("/login")
//                //Перенарпавление на главную страницу после успешного входа
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/");
//    }


}
