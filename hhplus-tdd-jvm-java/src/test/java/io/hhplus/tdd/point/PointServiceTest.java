package io.hhplus.tdd.point;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * PointServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */

@SpringBootTest
class PointServiceTest {

    @Autowired
    private PointService sut;
    @Autowired
    private UserPointTable userPointTable;
    @Autowired
    private PointHistoryTable pointHistoryTable;

    @Test
    void charge_point_test() throws Exception{
        sut.charge(1L, 100L);

        UserPoint result = userPointTable.selectById(1L);

        assertThat(result.point()).isEqualTo(100L);
    }

    @Test
    void point_charge_concurrent_test() throws Exception{
        int threadCount = 11;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount ; i++) {
            int chargeAmount = (i+1) * 100;
            executorService.submit(() -> {
                try {
                    sut.charge(1L, chargeAmount );
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Thread.sleep(3000);

        UserPoint userPoint = userPointTable.selectById(1L);
        assertThat(userPoint.point()).isEqualTo(IntStream.range(1, threadCount + 1).sum() * 100L);
        List<PointHistory> pointHistories = pointHistoryTable.selectAllByUserId(1L);
        assertThat(pointHistories).hasSize(threadCount);
    }



}