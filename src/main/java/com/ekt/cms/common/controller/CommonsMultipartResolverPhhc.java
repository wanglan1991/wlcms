package com.ekt.cms.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultipartResolverPhhc  extends CommonsMultipartResolver{
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url!=null && url.contains("/cms/uploads/img")) {
            return false;
        } else {
            return super.isMultipart(request);
        }
    }
}