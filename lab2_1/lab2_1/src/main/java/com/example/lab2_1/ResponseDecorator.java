package com.example.lab2_1;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ResponseDecorator", servletNames = "resultServlet")
public class ResponseDecorator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, wrapper);

        String content = wrapper.toString();

        content = "<h3>Decorator prelude</h3>" + content + "<h3>Decorator coda</h3>";

        PrintWriter out = response.getWriter();
        out.write(content);
    }
}
