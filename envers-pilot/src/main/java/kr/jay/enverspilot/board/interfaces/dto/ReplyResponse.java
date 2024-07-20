package kr.jay.enverspilot.board.interfaces.dto;

import kr.jay.enverspilot.board.domain.reply.Reply;
import lombok.Builder;

@Builder
public record ReplyResponse(
    Long id,
    String comment
) {

    public static ReplyResponse from(Reply reply) {
        return ReplyResponse.builder()
            .id(reply.getId())
            .comment(reply.getComment())
            .build();
    }
}
