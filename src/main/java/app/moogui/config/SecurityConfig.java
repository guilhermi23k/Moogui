package app.moogui.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	List<String> publicApis = List.of( "/users");
	
	 @Bean
	 SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf((csrf) -> csrf.disable())
	                .authorizeHttpRequests(
	                		 r -> r.requestMatchers(
                                     publicApis.stream()
                                             .map(AntPathRequestMatcher::new)
                                             .toArray(RequestMatcher[]::new)
                             )
                             .permitAll()
                             .requestMatchers(HttpMethod.POST, "/register").permitAll()
                             .anyRequest()
                             .authenticated()
                    )
//	                		(requests) -> requests
//	                        .requestMatchers("/users", "/titles").authenticated()
//	                        .requestMatchers("/register").permitAll())
	                .formLogin(Customizer.withDefaults())
	                .httpBasic(Customizer.withDefaults());
	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
