package kr.jay.enverspilot.board.interfaces.dto;

import kr.jay.enverspilot.board.domain.reply.Reply;

public record ReplyRequest(
    String comment
) {

    public Reply toEntity() {
        return Reply.builder()
            .comment(comment)
            .build();
    }
}
