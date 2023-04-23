package kr.co.dait.gamehub.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.repository.UserRepository;
import kr.co.dait.gamehub.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    public List<UserDto> findAll(){
        return ModelMapperUtil.mapList(userRepository.findAll(), UserDto.class) ;
    }

    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User saveUser(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByUserEmail(user.getUserEmail());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(userEmail);
        if(user == null){
            throw new UsernameNotFoundException(userEmail);
        }
        return user;
    }
   
}
