package kr.jay.pilotprojcet.common.filter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.jay.pilotprojcet.common.utils.HibernateQueryCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * HibernateQueryCounterFilter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Slf4j
@RequiredArgsConstructor
public class HibernateQueryCounterFilter extends OncePerRequestFilter {

	private final HibernateQueryCounter hibernateQueryCounter;

	@Override
	protected void doFilterInternal(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final FilterChain filterChain
	) throws ServletException, IOException {
		hibernateQueryCounter.start();
		filterChain.doFilter(request, response);
		final HibernateQueryCounter.Counter counter = hibernateQueryCounter.getCounter();
		final long count = counter.totalQueryCount().get();
		final ConcurrentHashMap<String, AtomicLong> queryMap = counter.occurredQuery();

		log.info("count : {} , url : {}", count, request.getRequestURI());
		if (count >= 10) {
			log.error("한 request 에 쿼리가 10번 이상 발생.  발생 횟수 : {} ", count);
		}
		if(!queryMap.isEmpty()){
			queryMap.forEach((sql, counts) -> {
				if (counts.longValue() > 3) {
					log.error("한 request 에 동일 쿼리가 3번 이상 발생.  발생 횟수 : {} , 쿼리 : {}", counts, sql);
				}
			});
		}
		hibernateQueryCounter.clear();
	}
}
