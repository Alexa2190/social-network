package ru.phoenixflame.socialnetwork.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {
    private String id;
    private String password;
}
