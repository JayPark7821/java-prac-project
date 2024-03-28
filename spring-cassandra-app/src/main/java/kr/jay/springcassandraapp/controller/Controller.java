package kr.jay.springcassandraapp.controller;

import kr.jay.springcassandraapp.service.CassandraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/28/24
 */
@RestController
public class Controller {

    private final CassandraService cassandraService;

    public Controller(CassandraService cassandraService) {
        this.cassandraService = cassandraService;
    }

    @GetMapping("/test")
    void cassandraTest(){
        cassandraService.test();
    }
}
