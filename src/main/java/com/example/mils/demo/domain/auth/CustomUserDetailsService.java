package com.example.mils.demo.domain.auth;

import java.util.Optional;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mils.demo.domain.user.UserEntity;
import com.example.mils.demo.domain.user.UserRepository;

import lombok.AllArgsConstructor;

/**
 * SpringSecurityが提供するUserDetailsServiceインターフェース の実装クラス。
 * ユーザー詳細情報をデータベースから取得し、UserDetailsオブジェクトとして提供する。
 * このUserDetailsオブジェクトは、認証と認可の両方の目的で使用できる。
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { // UserDetailsServiceインターフェースを実装するクラス

    private UserRepository userRepository;


    @Override 
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        UserEntity user = userOptional.get();
        return new User(
            user.getEmail(),
            user.getPassword(),
            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()) // カンマで区切られた文字列を受け取り、その各部分を別々の権限と見なして、それをList<GrantedAuthority>に変換
        );
    }
}
