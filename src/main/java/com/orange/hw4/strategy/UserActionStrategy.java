package com.orange.hw4.strategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserActionStrategy {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}