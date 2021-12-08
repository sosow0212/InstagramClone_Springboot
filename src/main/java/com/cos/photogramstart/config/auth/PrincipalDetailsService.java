package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // userRepository 부를 때 필요함
@Service // IoC
public class PrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    // username만 받으면 password는 Spring-security가 알아서 맞춰서 처리해준다.
    // return이 잘되면 자동으로 UserDetails 타입을 세션을 만들어준다. (즉 userEntity를 잘 받아오면)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        System.out.println(username + "/// " + userEntity);

        if(userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }
    }
}
