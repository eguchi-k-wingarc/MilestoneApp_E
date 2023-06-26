package com.example.mils.demo.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.mils.demo.domain.auth.CustomUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * 認証プロバイダのセット(customUserDetailsServiceを適用させる)
     * 
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 認証プロバイダをセット
                .authenticationProvider(authenticationProvider())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        // loginとh2-consoleパスへのアクセスを許可
                        .mvcMatchers("/login**", "/h2-console/**").permitAll()
                        // それ以外のすべてのリクエストは認証が必要とする
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        // ログインページを指定
                        .loginPage("/login")
                        // ログインに成功したときのデフォルトのリダイレクトを設定
                        .defaultSuccessUrl("/milestones", true)
                        // ユーザ名とパスワードのパラメータ名を指定します
                        .usernameParameter("email")
                        .passwordParameter("password"))
                // H2コンソールへのCSRF保護を無効にする
                .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
                // H2コンソールを表示するためにフレームオプションを設定
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                // セッション管理のポリシーを設定
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
                
                
        return http.build();
    }

}
