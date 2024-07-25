package com.foodorderingsystem.orderservice.domain.applicationservice;


import com.foodorderingsystem.orderservice.domain.core.OrderDomainService;
import com.foodorderingsystem.orderservice.domain.core.OrderDomainServiceImpl;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMsgPublisher;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMsgPublisher;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.restaurant.OrderPaidRestaurantRequestMsgPublisher;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.CustomerRepository;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.OrderRepository;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.RestaurantRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = "com.foodorderingsystem")
public class OrderTestConfiguration {
    @Bean
    public OrderCreatedPaymentRequestMsgPublisher orderCreatedPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCreatedPaymentRequestMsgPublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMsgPublisher orderCancelledPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCancelledPaymentRequestMsgPublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMsgPublisher orderPaidRestaurantRequestMessagePublisher(){
        return Mockito.mock(OrderPaidRestaurantRequestMsgPublisher.class);
    }

    @Bean
    public OrderRepository orderResponsitory(){
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository(){
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }
}
