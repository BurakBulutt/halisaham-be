package com.bitirmeodev.halisahambe.domain.auth.user.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserCreationEvent;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileCreationEvent;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileDto;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileService;
import com.bitirmeodev.halisahambe.domain.mailservice.impl.MailServiceImpl;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;

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
        UserDto user = UserMapper.toDto(repository.save(UserMapper.toEntity(new User(),dto)));
    //    eventPublisher.publishEvent(new UserCreationEvent(user.getEmail(),user.getVerificationCode()));
        eventPublisher.publishEvent(new UserProfileCreationEvent(user.getId()));
        return user;
    }

    @Transactional
    public User saveUser(UserDto dto){
        User user = repository.save(UserMapper.toEntity(new User(),dto));
        eventPublisher.publishEvent(new UserCreationEvent(user.getEmail(),user.getVerificationCode()));
        return user;
    }

    public User getByRole(UserType userType){
        return repository.findByUserType(userType).orElse(null);
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
        eventPublisher.publishEvent(new UserProfileCreationEvent(user.getId()));
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

    public List<UserDto> getAllByUserIdIn(List<String> userIds) {
        return repository.findAllById(userIds).stream().map(UserMapper::toDto).toList();
    }


}
