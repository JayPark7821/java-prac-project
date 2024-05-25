package kr.jay.migration.internal.api.migration;

import kr.jay.migration.application.dispatcher.MigrationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MigrationController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/25/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1")
public class MigrationController {

    private final MigrationDispatcher dispatcher;

    @PutMapping("/retry")
    public MigrationRetryResponse retry(@RequestBody MigrationRetryRequest req){
        boolean result = dispatcher.dispatch(req.userId(), req.aggregateId(), req.aggregateType());
        return new MigrationRetryResponse(result);
    }
}
