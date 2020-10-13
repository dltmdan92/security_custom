package com.seungmoo.springboot.security_custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/hello").permitAll()  // "/", "/hello" 요청에 대해서는 접근 허용
                .anyRequest().authenticated()   // 그리고 그외의 request들은 필수로 인증
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    /**
     * 스프링 시큐리티에서는 password encoder가 필수이다. 스프링
     * but NoOp 인코더를 통해 아무런 encoding/decoding을 하지 않는 평문 그대로 password를 인코딩한 것처럼 pass한다.
     * 절대로 쓰면 안되는 방법 (보안 이슈)
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 스프링 시큐리티에서 권장하는 인코더
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 쓰면 안되는 방법 (보안 이슈)
        //return NoOpPasswordEncoder.getInstance();
    }
}
