package edu.utcluj.track.utils;


/*@EnableWebSecurity
@Configuration*/
public class WebSecurityConfig {
/*

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/authentication/login").failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

*/
}
