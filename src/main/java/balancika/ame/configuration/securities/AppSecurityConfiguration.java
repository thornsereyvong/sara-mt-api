package balancika.ame.configuration.securities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/layout/**", "/login", "/rest/database/list", "/api/**").permitAll();
		http.authorizeRequests().anyRequest().hasAnyAuthority("")
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/login")
		.usernameParameter("ame_username")
		.passwordParameter("ame_password")
		.failureUrl("/login?error")
		.and()
		.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Bean
	public UsernamePasswordAuthenticationFilter authenticationFilter() {
		CustomUsernamPasswordAuthenticationFilter authFilter = new CustomUsernamPasswordAuthenticationFilter();
	    List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
	    authenticationProviderList.add(authenticationProvider);
	    AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
	    authFilter.setAuthenticationManager(authenticationManager);
	    authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
	    authFilter.setUsernameParameter("ame_username");
	    authFilter.setPasswordParameter("ame_password");
	    return authFilter;
	}
}
