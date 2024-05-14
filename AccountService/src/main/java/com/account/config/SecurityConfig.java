package com.account.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.account.filter.JwtFilter;
import com.account.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private CustomUserDetailsService userDetailsService;
	@Autowired
    private JwtFilter jwtFilter;
	
   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
//        http csrf().disable().authorizeRequests()
//    .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
//    .antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
//    .antMatchers(HttpMethod.GET,"/v2/api-docs").permitAll()
//			.antMatchers("/customer/add-customer", "/account/authenticate").permitAll()
//			.antMatchers("/customer/get-customer/{name}", "/customer/customer-id/{id}",
//					"/customer/get-all-customer", "/customer/update-profile/{id}")
//			.anyRequest().authenticated();
////                .and().exceptionHandling().and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().authorizeRequests().antMatchers("/account/authenticate").
    	permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).cors();
}
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Allow requests from any origin 
        configuration.addAllowedMethod("*"); // Allow all HTTP methods
        configuration.addAllowedHeader("*"); // Allow all headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
