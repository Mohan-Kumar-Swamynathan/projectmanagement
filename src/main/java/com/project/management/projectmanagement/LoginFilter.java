package com.project.management.projectmanagement;

import com.project.management.projectmanagement.constant.UserRoleTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        log.info("Remote Host:" + request.getRemoteHost());
        log.info("Remote Address:" + request.getRemoteAddr());
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                log.info("header" + httpServletRequest.getHeader(name));
                if (name.equals("user_id")) {
                    UserRoleTypeEnum role = UserRoleTypeEnum.findByCode(Integer.valueOf(httpServletRequest.getHeader(name)));
                    UserThreadLocale.setUserRole(role);
                    log.info("user role " + role.toString());
                }
            }
        }
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }

}
