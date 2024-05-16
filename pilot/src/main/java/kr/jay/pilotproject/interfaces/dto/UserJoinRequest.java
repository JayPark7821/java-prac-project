package kr.jay.pilotproject.interfaces.dto;

import kr.jay.pilotproject.domain.users.command.UserJoinCommand;

/**
 * UserJoinRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public record UserJoinRequest(
    String name
) {

    public UserJoinCommand toCommand() {
        return new UserJoinCommand(name);
    }
}
