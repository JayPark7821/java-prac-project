package kr.jay.tobyspring.api;

import java.io.IOException;
import java.net.URI;

/**
 * ApiExecutor
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/4/24
 */
public interface ApiExecutor {

    String execute(URI uri) throws IOException;

}
