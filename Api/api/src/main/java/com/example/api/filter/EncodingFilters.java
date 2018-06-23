package com.example.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/5/3, 9:12
 */
@Slf4j
@Configuration
public class EncodingFilters {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new EncodingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("name", "value");
        filterRegistrationBean.setName("EncodingFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


    public class EncodingFilter implements Filter {
        public EncodingFilter() {
            log.info("过滤器构造");
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("过滤器初始化");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletRequest.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("text/html;charset=utf-8");
            System.out.println("this is MyFilter,url :" + httpServletRequest.getRequestURI());
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            log.info("过滤器销毁");
        }
    }

}
