package kr.co.dait.gamehub.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    public List<UserDto> findAll(){
        return ModelMapperUtil.mapList(userRepository.findAll(), UserDto.class) ;
    }

    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    //회원가입
    public UserDto addUser(UserDto userDto) {
        //패스워드 암호화
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        //기존 가업 회원 확인
        User findUser = userRepository.findByUserEmail(userDto.getUserEmail().trim());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 이메일 주소 입니다.");
        }        
        //회원정보 저장
        User user = ModelMapperUtil.map(userDto, User.class);
        userRepository.save(user);
        return userDto;
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
