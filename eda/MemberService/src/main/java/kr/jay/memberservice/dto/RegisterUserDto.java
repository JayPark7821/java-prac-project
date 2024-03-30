package kr.jay.memberservice.dto;

/**
 * RegisterUserDto
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
public class RegisterUserDto {
    private String loginId;
    private String userName;

    public String getLoginId() {
        return loginId;
    }

    public String getUserName() {
        return userName;
    }
}
