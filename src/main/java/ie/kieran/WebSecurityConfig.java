package ie.kieran;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select user.userEmail, user.userPassword, user.userEnabled from user where user.userEmail = ?")
		.authoritiesByUsernameQuery("select role.userEmail, role.roleDescription from role where role.userEmail = ?");
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
	http.authorizeRequests().antMatchers("/css/**", "/h2/**", "/", "/index", "/register").permitAll().
	antMatchers("/api/**").hasAnyRole("API", "ADMIN").anyRequest().authenticated()
	.and().formLogin().and().httpBasic().and().csrf().disable();
	}
}
