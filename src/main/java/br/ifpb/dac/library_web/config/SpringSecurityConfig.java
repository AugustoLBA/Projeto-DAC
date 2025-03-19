package br.ifpb.dac.library_web.config;

import br.ifpb.dac.library_web.config.jwt.JwtAuthenticationEntryPoint;
import br.ifpb.dac.library_web.config.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static br.ifpb.dac.library_web.util.Constants.ADMIN;
import static br.ifpb.dac.library_web.util.Constants.USER;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SpringSecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Desabilita o CSRF, necessário para APIs REST
//                .formLogin(form -> form.disable()) // Desabilita o formulário de login
//                .httpBasic(basic -> basic.disable()) // Desabilita o HTTP basic
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/v1/user").permitAll() // Permite acesso sem autenticação à criação de usuários
//                        .requestMatchers(HttpMethod.POST, "/v1/auth").permitAll() // Permite acesso sem autenticação para login
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll() // Permite o acesso completo ao Swagger
//                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
//                )
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define que a sessão será stateless (não mantém estado)
//                .addFilterBefore(
//                        jwtAuthorizationFilter(), // Adiciona o filtro de autorização JWT
//                        UsernamePasswordAuthenticationFilter.class
//                )
//                .exceptionHandling(ex -> ex
//                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())) // Trata falhas de autenticação
//                .build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth
                        // Libera o acesso ao Swagger e seus arquivos estáticos
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/auth").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/v1/user").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT,  "/v1/").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET,  "/v1/book").hasAnyRole(ADMIN,USER)
                        .requestMatchers(HttpMethod.GET,  "/v1/author").hasAnyRole(ADMIN,USER)
                        .requestMatchers(HttpMethod.GET,  "/v1/publisher").hasAnyRole(ADMIN,USER)
                        .requestMatchers(HttpMethod.POST,  "/v1/**").hasRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Corrigido
                .addFilterBefore(
                        jwtAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*
        Essa criptografia é atualmente a mais utilizada e segura.
        Ela basicamente faz com que senhas iguais sejam criptografadas
        diferentes, assim dificultando a quebra da senha.
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        /*
        Esse metodo retorna um objeto de gerenciamento de autenticação
         */
        return authenticationConfiguration.getAuthenticationManager();
    }


}
