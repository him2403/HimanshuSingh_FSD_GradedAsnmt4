package com.gl.empmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gl.empmgmt.service.UserServiceImpl;

@Configuration
@EnableWebSecurity

public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf()
		.disable()
		.authorizeRequests()
        .antMatchers("/register/**").permitAll()
        .antMatchers("/role/showRoleFormForEdit/**").hasAuthority("Admin")
        .antMatchers("/role/delete/**").hasAuthority("Admin")
        .antMatchers("/employee/showEmployeeFormForEdit/**").hasAnyAuthority("Admin","User")
        .antMatchers("/employee/delete/**").hasAnyAuthority("Admin","User")
        .antMatchers("/employee/**").authenticated()
        .antMatchers("/role/**").authenticated()
        .anyRequest().authenticated()
        .and()
			.formLogin()
			.loginPage("/mylogin")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll().and().logout().invalidateHttpSession(true)
			.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/mylogin?logout").permitAll().and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
		}
}
