package ru.phoenixflame.socialnetwork.api.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.phoenixflame.socialnetwork.api.dto.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullUserResponse extends UserResponse {
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "second_name")
    private String secondName;
    @JsonProperty(value = "birth_date")
    private String birthDate;
    private String city;
    private String biography;
    private Gender gender;
}
