package com.webproject.Filter;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.webproject.entity.User;

public class XSSReponseInterceptor implements HandlerInterceptor {
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession(false);
        if (session != null) {
        	User user = (User) session.getAttribute("user");
            if (user != null) {
            	escapeObjectFields(user);
            }
        }
		
        if (modelAndView != null && !modelAndView.isEmpty()) {
            modelAndView.getModelMap().forEach((key, value) -> {
                if (value instanceof String) {
                    String escapeValue = HtmlUtils.htmlEscape((String) value); 
                    modelAndView.addObject(key, escapeValue);
                } else if (value instanceof List) {
                    List<?> listValue = (List<?>) value;
                    for (int i = 0; i < listValue.size(); i++) {
                        Object listItem = listValue.get(i);
                        if (listItem != null) {
                        	escapeObjectFields(listItem);
                        }
                    }
                } else if (value != null) {
                	escapeObjectFields(value);
                }
            });
        }
    }

    private void escapeObjectFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().equals(String.class)) {
                    String fieldValue = (String) field.get(object);
                    if (fieldValue != null) {
                        fieldValue = HtmlUtils.htmlEscape(fieldValue);
                        field.set(object, fieldValue);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
