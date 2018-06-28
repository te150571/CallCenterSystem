package jp.te4a.zoo.spring.boot.CallCenterSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/*
 * ログインコンフィグ
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web)throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/failed").permitAll()
				.antMatchers("/user_test").permitAll()
				.antMatchers("/user_test/add").permitAll()
				.antMatchers("/user_test/create").permitAll()
				.antMatchers("/user_test/test-user-create").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login/form")
				.failureUrl("/failed")
				.successForwardUrl("/success")
//				.defaultSuccessUrl("/success", true)
				.usernameParameter("username").passwordParameter("password")
			.and()
				.logout()
					.logoutSuccessUrl("/");
	}
}
