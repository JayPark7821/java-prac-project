package kr.jay.pilotprojcet.common.utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.stereotype.Component;
import org.w3c.dom.css.Counter;

/**
 * HibernateQueryCounter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
@Component
public class HibernateQueryCounter implements StatementInspector {

	private static final ThreadLocal<Counter> queryCounter = new ThreadLocal<>();

	public void start() {
		queryCounter.set(new Counter(new AtomicLong(0), new ConcurrentHashMap<>()));
	}

	public Counter getCounter(){
		return queryCounter.get();
	}

	public void clear() {
		queryCounter.remove();
	}

	public Counter getCount(){
		return queryCounter.get();
	}

	@Override
	public String inspect(final String sql) {
		final Counter counter = queryCounter.get();
		if (counter != null) {
			final AtomicLong count = counter.totalQueryCount();
			count.incrementAndGet();
			final ConcurrentHashMap<String, AtomicLong> queryList = counter.occurredQuery();
			if (queryList.containsKey(sql)) {
				queryList.get(sql).incrementAndGet();
			} else {
				queryList.put(sql, new AtomicLong(1));
			}
		}
		return sql;
	}

	public record Counter(
		AtomicLong totalQueryCount,
		ConcurrentHashMap<String, AtomicLong> occurredQuery
	) {
	}
}
