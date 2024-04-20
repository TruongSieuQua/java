package com.tjn.user.service;

import com.tjn.user.dto.TransactionRequest;
import com.tjn.user.dto.TransactionResponse;
import com.tjn.user.dto.TransactionStatus;
import com.tjn.user.entity.User;
import com.tjn.user.repository.UserRepository;
import com.tjn.user.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service
public class UserTransactionService {

    @Autowired
    private UserRepository userRepository;

    public Mono<TransactionResponse> doTransaction(TransactionRequest req){
        UnaryOperator<Mono<User>> operation = null;

        switch (req.getType()){
            case CREDIT -> operation = credit(req);
            case DEBIT -> operation = debit(req);
        }
        if(operation==null){
            return Mono.error(new RuntimeException("Invalid " + req.getType()));
        }
        return this.userRepository.findById(req.getUserId())
                // map T to K
                // flatMap T to Mono<K>
                // transform Mono<T> to Mono<K>
                .transform(operation)
                .flatMap(this.userRepository::save)
                .map(s -> EntityDtoUtil.toResponse(req, TransactionStatus.COMPLETED))
                .defaultIfEmpty(EntityDtoUtil.toResponse(req, TransactionStatus.FAILED));
    }

//    private Function<Mono<User>, Mono<User>> receive Mono<User> and return Mono<User>
    // same as above
    private UnaryOperator<Mono<User>> credit(TransactionRequest req){
        return userMono -> userMono
                .doOnNext(u -> u.setBalance(u.getBalance() + req.getAmount()));
    }

    private UnaryOperator<Mono<User>> debit(TransactionRequest req){
        return userMono -> userMono
                .filter(u -> u.getBalance() >= req.getAmount())
                .doOnNext(u -> u.setBalance(u.getBalance() - req.getAmount()));
    }

}
