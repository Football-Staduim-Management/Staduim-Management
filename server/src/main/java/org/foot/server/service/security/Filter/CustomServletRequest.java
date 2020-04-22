package org.foot.server.service.security.Filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomServletRequest extends HttpServletRequestWrapper {
    private final Cookie cookie;

    public CustomServletRequest(HttpServletRequest request, String cookie) {
        super(request);
        this.cookie = new Cookie("JSESSIONID", cookie);
        this.cookie.setPath("/");
    }
    @Override
    public Cookie[] getCookies() {
        //This is a example, get all cookies here and put your with a new Array
        return new Cookie[] {cookie};
    }
}
