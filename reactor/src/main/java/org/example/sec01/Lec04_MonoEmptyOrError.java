package org.example.sec01;

import com.github.javafaker.Faker;
import org.example.utils.Util;
import reactor.core.publisher.Mono;

public class Lec04_MonoEmptyOrError {
    public static void main(String[] args) {

        //hey pulisher do you have this user
        userRepository(3).subscribe(
                Util.onNext(), //If find
                Util.onError(), //If not in range
                Util.onComplete() //If empty
        );
    }

    private static Mono<String> userRepository(int userId){
        if(userId == 1){
            return Mono.just(Faker.instance().name().fullName());
        }else if(userId == 2){
            return Mono.empty(); // Do not return null
        }else{
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }
    }
}
