package ru.phoenixflame.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.phoenixflame.socialnetwork.api.dto.response.AuthResponse;
import ru.phoenixflame.socialnetwork.entity.AuthEntity;
import ru.phoenixflame.socialnetwork.repository.AuthRepository;

import static ru.phoenixflame.socialnetwork.utils.Utils.hashPassword;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthRepository authRepository;

    /**
     * Вход пользователя в систему
     *
     * @param id - логин пользователя
     * @param password - пароль пользователя
     * @return токен
     */
    public AuthResponse loginUser(String id, String password) {
        AuthEntity authEntity = authRepository.findByUserIdAndPassword(id, hashPassword(password));
        return new AuthResponse(authEntity.getToken().toString());
    }
}
