package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화 시킴
@Configuration // IoC에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        // DB에 있는 패스워드를 암호화함
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      super.configure(http); // super.~ 삭제를 하면 기존 시큐리티가 가지고 있는 기능이 전부 비활성화된다.
        http.csrf().disable(); // CSRF 토큰 비활성화
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated() // 이 주소로 시작되면 인증이 필요하고
                .anyRequest().permitAll() // 그게 아닌 모든 요청은 허용하겠다 라는 뜻임
                .and()
                .formLogin()
                .loginPage("/auth/signin") // 위에 주소로 이동하면 /auth/signin 으로 자동 이동시킨다라는 뜻
                .defaultSuccessUrl("/"); // 로그인이 정상적이면 "/" 이동
    }
}
