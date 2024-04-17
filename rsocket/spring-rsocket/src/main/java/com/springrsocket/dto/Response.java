package com.springrsocket.dto;

import com.springrsocket.dto.error.ErrorEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
public class Response<T> {
    ErrorEvent errorResponse;
    T successResponse;

    public Response(T successResponse) {
        this.successResponse = successResponse;
    }

    public Response(ErrorEvent errorResposne) {
        this.errorResponse = errorResposne;
    }

    public boolean hasError(){
        return Objects.nonNull(this.errorResponse);
    }

    // Ham khoi tao
    public static <T> Response<T> with(T t){
        return new Response<T>(t);
    }

    // Ham khoi tao
    public static <T> Response<T> with(ErrorEvent errorResposne){
        return  new Response<T>(errorResposne);
    }
}
