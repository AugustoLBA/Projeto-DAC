package br.ifpb.dac.library_web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Altere conforme a URL do seu fronten
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true) // Permitir cookies se necessário
                .allowedHeaders("Authorization", "Content-Type") // Permite esses headers
                .exposedHeaders("Authorization"); // Permite que o frontend veja esse header
    }
}
