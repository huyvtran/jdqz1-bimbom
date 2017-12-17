package com.isacademy.jjdd1.czterystrony.filters;

import com.isacademy.jjdd1.czterystrony.users.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/4analysis/analiza/*", "/4analysis/notowania/*"})
public class LoginFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Inject
    SessionData sessionData;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!sessionData.isLogged()) {
            log.info("User not logged, redirecting...");
            httpServletResponse.sendRedirect("/api/google/signin");
            return;
        }

        log.debug("User {} already logged  in", sessionData.getUser().getEmail());
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
