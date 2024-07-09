package ru.phoenixflame.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.phoenixflame.socialnetwork.api.dto.request.UserRequest;
import ru.phoenixflame.socialnetwork.api.dto.response.FullUserResponse;
import ru.phoenixflame.socialnetwork.api.dto.response.UserResponse;
import ru.phoenixflame.socialnetwork.entity.UserEntity;
import ru.phoenixflame.socialnetwork.repository.AuthRepository;
import ru.phoenixflame.socialnetwork.repository.UserRepository;
import ru.phoenixflame.socialnetwork.utils.Utils;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    /**
     * Get user by id
     *
     * @param id - unique id
     * @return UserDto
     */
    public FullUserResponse getUserById(String id) throws EntityNotFoundException {
        UserEntity userEntity =  userRepository
                .findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class.getSimpleName() + " not found"));
        return userMapper(userEntity);
    }

    /**
     * Register new user
     *
     * @param userDto - user data
     * @return UserDto
     */
    @Transactional
    public UserResponse addUser(UserRequest userDto) {
        UserEntity userEntity = userRepository.addUser(
                userDto.getFirstName(), userDto.getSecondName(), userDto.getBiography(),
                userDto.getGender().name(), Utils.dateFromString(userDto.getBirthDate()),
                userDto.getCity());
        authRepository.addToken(userEntity.getId(), Utils.hashPassword(userDto.getPassword()), UUID.randomUUID().toString());
        return new UserResponse(userEntity.getId());
    }

    private FullUserResponse userMapper(UserEntity entity) {
        FullUserResponse dto = new FullUserResponse();
        dto.setUserId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setBirthDate(Utils.stringFromDate(entity.getBirthDate()));
        dto.setGender(entity.getGender());
        dto.setBiography(entity.getBiography());
        return dto;
    }
}
