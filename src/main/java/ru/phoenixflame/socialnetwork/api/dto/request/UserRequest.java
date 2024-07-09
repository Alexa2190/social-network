package ru.phoenixflame.socialnetwork.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenixflame.socialnetwork.api.dto.Gender;

@Data
@NoArgsConstructor
public class UserRequest {
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "second_name")
    private String secondName;
    @JsonProperty(value = "birth_date")
    private String birthDate;
    private String city;
    private String biography;
    private Gender gender;
    private String password;
}
