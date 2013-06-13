package com.epam.xsl.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OldRequestFilter implements Filter {
	private static final String PREVIOUS_REQUEST_HASH = "previousRequestHash";

	private static final String REQUEST_AGE = "requestAge";
	private static final String OLD = "old";
	
	private static final String DETECTED_OLD_REQUEST = "detectedOldRequest";
	private static final String DETECTED = "detected";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain next) throws IOException, ServletException {
		/*HttpSession session = ((HttpServletRequest) req).getSession(true);
		Integer previousReqHash = (Integer) session
				.getAttribute(PREVIOUS_REQUEST_HASH);
		System.out.println("hash from session: " + previousReqHash);
		checkForOldRequest(req, session, previousReqHash);*/
		next.doFilter(req, resp);
	}

	private static void checkForOldRequest(ServletRequest req,
			HttpSession session, Integer previousReqHash) {
		String oldRequest = (String) session.getAttribute(DETECTED_OLD_REQUEST);
		if (previousReqHash == null && !DETECTED.equals(oldRequest)) {
			System.out.println("hash from req: " + req.hashCode());
			session.setAttribute(PREVIOUS_REQUEST_HASH, req.hashCode());
			session.setAttribute(DETECTED_OLD_REQUEST, null);
		} else if (req.hashCode() == previousReqHash) {
			req.setAttribute(REQUEST_AGE, OLD);
			session.setAttribute(PREVIOUS_REQUEST_HASH, null);
			session.setAttribute(DETECTED_OLD_REQUEST, DETECTED);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}
