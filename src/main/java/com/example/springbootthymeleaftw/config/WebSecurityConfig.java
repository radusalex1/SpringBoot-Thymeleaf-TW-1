package com.example.springbootthymeleaftw.config;

import com.example.springbootthymeleaftw.model.entity.QuestionEntity;
import com.example.springbootthymeleaftw.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {
    private static final String[] METHODS_ALLOWED = {
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name(),
    };

    @Bean
    CommandLineRunner commandLineRunner(QuestionRepository questionRepository){
        return args -> {
            if (questionRepository.findAll().size()==0) {

                QuestionEntity question1 =  new QuestionEntity();
                question1.setText("Cum il cheama poe tinel?");
                question1.setCorrecAnswer("Tinel");

                QuestionEntity question2 = new QuestionEntity();
                question2.setText("Unde locuieste tinel?");
                question2.setCorrecAnswer("Brasov");

                questionRepository.saveAll(List.of(question1,question2));
            }
        };
    }


    @Bean
    SecurityFilterChain resources (HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers( "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                //.failureUrl("/login-error") //if you want a separate page for failed auth.
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and().build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(METHODS_ALLOWED)
                .allowedOrigins("*");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
