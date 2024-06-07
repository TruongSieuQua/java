package com.tjn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void test1(){
        var x = 1 + 1;
        Assertions.assertEquals(2, x);
    }
}
