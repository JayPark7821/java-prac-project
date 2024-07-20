package kr.jay.enverspilot.board.interfaces.dto;

import kr.jay.enverspilot.board.domain.Board;

public record BoardRequest(
    String title,
    String content,
    String sub
) {

    public Board toEntity() {
        return Board.builder()
            .title(title)
            .content(content)
            .sub(sub)
            .build();
    }
}
