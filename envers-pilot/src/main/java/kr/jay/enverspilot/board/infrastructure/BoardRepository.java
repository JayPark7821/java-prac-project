package kr.jay.enverspilot.board.infrastructure;

import java.util.List;
import kr.jay.enverspilot.board.domain.Board;
import kr.jay.enverspilot.common.config.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepository extends MyRepository<Board, Long, Long> {

    List<Board> findAll();

    Page<Board> findAll(Pageable pageable);
}
