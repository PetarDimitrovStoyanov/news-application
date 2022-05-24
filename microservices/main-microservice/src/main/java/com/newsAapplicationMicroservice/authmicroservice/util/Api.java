package com.newsAapplicationMicroservice.authmicroservice.util;

public class Api {
    public static final String PREFIX = "/**";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String ALL = "/all";
    public static final String AUTH = "/auth";
    public static final String USERS = "/users";
    public static final String LOGIN = "/login";
    public static final String CHANGE_USER_ROLE = "/{userId}/change-user-role";
    public static final String USER_BY_ID = "/{userId}";
    public static final String NEWS = "/news";
    public static final String GET_A_NEW_BY_ID = "/{newId}";
    public static final String GET_ALL_NEWS = "/get-all";
    public static final String GET_ALL_BY_CATEGORY = "/get-all-by-category/{categoryId}";
    public static final String GET_ALL_NEWS_MANAGEMENT = "/get-all-management";
    public static final String CREATE_A_NEW = "/create-a-new";
}
