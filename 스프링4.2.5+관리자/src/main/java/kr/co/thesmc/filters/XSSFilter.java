package kr.co.thesmc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.co.thesmc.util.CommonXSSRequestFilter;


public class XSSFilter implements Filter {

	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		if(this.filterConfig != null) this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(new CommonXSSRequestFilter((HttpServletRequest)request), response);
	}
}
