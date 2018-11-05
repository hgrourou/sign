package com.signin.demo.secruity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean(name = "userDetailsService")
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean(name = "unauthorizedEntryPoint")
    protected UnauthorizedEntryPoint unauthorizedEntryPoint() {
        return new UnauthorizedEntryPoint();
    }

    @Bean(name = "csrfSecurityRequestMatcher")
    protected CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
        return new CsrfSecurityRequestMatcher();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
                // .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthenticationTokenProcessingFilter(userDetailsService()),
                        UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/user/login", "/user/register").permitAll()
                .antMatchers("/xu/add/account","/xu/reset","/xu/delete","/xu/article/create","/xu/article/delete/**",
                        "/xu/bbs/delete/**", "/xu/article/update/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/xu/profile/**","/xu/changePassword","/xu/article/list","/xu/bbs/list","/xu/article/fetch/**")
                .hasAnyAuthority("ADMIN","USER")
                .anyRequest().hasAnyAuthority("USER");

        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint())
                .accessDeniedPage("/error/403");

        csrfSecurityRequestMatcher().setExecludeUrls(excludedUrls());
        http.csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher());
    }

    /*
     * Add the excluded urls for csrf
     */
    private List<String> excludedUrls() {
        List<String> execludeUrls = new ArrayList<String>();

        execludeUrls.add("/");
        return execludeUrls;
    }
}
