package com.bitirmeodev.halisahambe.domain.auth.user.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserCreationEvent;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.mailservice.impl.MailServiceImpl;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final MailServiceImpl mailService;
    private final JwtUtil jwtUtil;

    @Override
    public List<UserDto> getAll() {
        return repository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getById(String id) {
        return repository.findById(id).map(UserMapper::toDto).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));
    }

    @Override
    public UserDto getByEmail(String email) {
        return repository.findByEmail(email).map(UserMapper::toDto).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));
    }

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        return UserMapper.toDto(repository.save(UserMapper.toEntity(new User(),dto)));
    }

    @Transactional
    public User saveUser(UserDto dto){
        return repository.save(UserMapper.toEntity(new User(),dto));
    }

    @Override
    public UserDto update(String id, UserDto dto) {
        User user = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));
        return UserMapper.toDto(repository.save(UserMapper.toEntity(user,dto)));
    }

    @Override
    public void delete(String id) {
        User user = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));
        repository.delete(user);
    }

    @Transactional
    @Override
    public void verificateUser(String code){
        User user = repository.findByVerificationCode(code).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));
        user.setIsVerified(Boolean.TRUE);
        repository.save(user);
    }

    @Override
    public void sendVerificationMail(){
        String userId = jwtUtil.extractUserId();

        if (userId == null){
            throw new BaseException(MessageCodes.FAIL);
        }
        User user = repository.findById(userId).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName()));

        String verificationTarget = "http://localhost:8090/users/verificate?code=" + user.getVerificationCode();

        mailService.sendConfirmationMail(user.getEmail(),verificationTarget);

    }

    @EventListener
    @Order(1)
    public void createUserEvent(UserCreationEvent userCreationEvent){
        String verificationTarget = "http://localhost:8090/users/verificate?code=" + userCreationEvent.verificationCode();

        mailService.sendConfirmationMail(userCreationEvent.email(), verificationTarget);

    }


}
