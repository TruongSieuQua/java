package com.example.webflux.service;

import com.example.webflux.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response findSquare(int input){
        return new Response(input*input);
    }

    public List<Response> multiplicationTable(int input){
        return IntStream.rangeClosed(1, 10)
                //peek được sử dụng để thực hiện một hành động nhất định trên mỗi phần tử trong stream mà không ảnh hưởng đến các phần tử trong stream đó.
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service processing : " + i))
                .mapToObj(i -> new Response(i * input))
                .toList();
    }
}
