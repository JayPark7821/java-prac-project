package kr.jay.pilotproject.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.jay.pilotproject.common.config.multidatasource.DataSourceContextHolder;
import kr.jay.pilotproject.common.config.multidatasource.EdcDataSource;
import kr.jay.pilotproject.common.utils.BufferedRequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Integer.MIN_VALUE)
@Component
public class DataSourceSelectFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		DataSourceContextHolder.setDataSource(EdcDataSource.valueOf(request.getHeader("datasource")));

		filterChain.doFilter(request, response);

		DataSourceContextHolder.clear();
	}
}
