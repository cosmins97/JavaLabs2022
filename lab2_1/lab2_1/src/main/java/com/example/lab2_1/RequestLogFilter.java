package com.example.lab2_1;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "RequestLogFilter", servletNames = "inputServlet")
public class RequestLogFilter implements Filter {
    // This filter implements Filter so it has to provide implementations
    // for Filter interface

    private FilterConfig filterConfig = null;

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig fConfig) {
        this.filterConfig = fConfig;
    }

    // 3rd implementation of Filter interface
    public void destroy() {
        this.filterConfig = null;
    }

    // 2nd implementation of Filter interface
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // extracting the ServletContext from filterConfig
        ServletContext context = filterConfig.getServletContext();
        // Taking incoming time of request
        double incomingTime = System.currentTimeMillis();

        // printing the information in the ServletContext's log
        context.log("Incoming request time : "+ incomingTime/1000.0);

        // Pre processing of filter before doFilter() method
        chain.doFilter(request, response); // here request goes to server
        // Post processing of filter before doFilter() method

        // Taking outgoing time of response
        double outgoingTime = System.currentTimeMillis();

        // printing the information in the ServletContext's log
        context.log("Outgoing response time : "+ outgoingTime/1000.0);


        double diffInTime = outgoingTime - incomingTime ;
        // printing the information in the ServletContext's log
        context.log("Difference in the request and response : "
                + diffInTime/1000.0);
    }

    // 3rd implementation of Filter interface
    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }
}
