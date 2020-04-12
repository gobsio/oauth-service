package com.gobs.oauthservice.services.user;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.mappers.users.UserMapper;
import com.gobs.oauthservice.repositories.users.UsersRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Inject
    UsersRepository usersRepository;

    public List<UserDTO> fetch() throws Exception {
        return usersRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    } 

}