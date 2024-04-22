package com.tjn.product.controller;

import com.tjn.product.service.GreetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class GreetingsController {

    private final GreetingsService greetingsService;

    Map<String, String> hello(){
        return this.greetingsService.greet();
    }

}
