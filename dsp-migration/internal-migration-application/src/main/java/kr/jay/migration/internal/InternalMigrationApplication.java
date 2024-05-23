package kr.jay.migration.internal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * InternalMigrationApplication
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/17/24
 */
@SpringBootApplication(scanBasePackages = "kr.jay.migration")
public class InternalMigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternalMigrationApplication.class, args);
    }

}
