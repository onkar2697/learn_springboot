package org.example.bookmyshow;

import org.example.bookmyshow.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ApiResponseJsonTest {

    @Autowired
    private JacksonTester<UserResponseDTO> json;

    @Test
    void shouldSerializeUserResponse() throws Exception {
        UserResponseDTO user = new UserResponseDTO();
        user.setId(1L);
        user.setName("ajroy");
        user.setEmail("ajroy@example.com");
        user.setAge(25);

        assertThat(json.write(user))
                .extractingJsonPathNumberValue("$.id")
                .isEqualTo(1);

        assertThat(json.write(user))
                .extractingJsonPathStringValue("$.name")
                .isEqualTo("ajroy");
    }
}