package kr.jay.migration.internal.api.exception;

import jakarta.persistence.EntityNotFoundException;
import kr.jay.migration.application.user.AlreadyAgreedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ErrorAdvice
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
@RestControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlreadyAgreedException.class)
    public ResponseEntity<?> handleAlreadyAgreedException(AlreadyAgreedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
