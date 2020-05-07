package org.foot.server.service.security;

import org.foot.server.service.security.Filter.CustomUserDetailService;
import org.foot.server.service.security.Filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@org.springframework.context.annotation.Configuration
public class Configuration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//.addFilterBefore(new AdapterFilter(), WebAsyncManagerIntegrationFilter.class)
        http
                .cors()
                .and()
                .csrf().disable()
                .logout()
                .permitAll()
                .logoutUrl("/logoutUser")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new LogoutSuccessHandler() {

                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                        System.out.println("logout success");
                    }
                })
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(unauthorizedHandler)

                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()));


        http
                .sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://10.188.67.156:4200", "http://192.168.137.14:4200")
                        .allowCredentials(true)
                        .allowedMethods("PUT","POST","GET","DELETE");
            }
        };
    }

}
