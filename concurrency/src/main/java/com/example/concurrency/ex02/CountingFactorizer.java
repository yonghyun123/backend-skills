package com.example.concurrency.ex02;

import lombok.Getter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class CountingFactorizer extends HttpServlet {
    //원자적 문제 해결
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = (BigInteger) request.getAttribute("number");
        count.incrementAndGet();
    }
}
