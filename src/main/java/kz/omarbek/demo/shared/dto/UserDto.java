package kz.omarbek.demo.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String userId;
    private String email;
    private String password;
    private String encryptedPassword;

    @Override
    public String toString() {
        return email + " "
                + userId + " "
                + encryptedPassword;
    }
}
