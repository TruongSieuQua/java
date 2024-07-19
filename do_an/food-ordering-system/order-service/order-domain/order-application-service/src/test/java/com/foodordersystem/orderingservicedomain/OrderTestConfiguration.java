package com.foodordersystem.orderingservicedomain;


import com.foodordersystem.orderdomaincore.OrderDomainService;
import com.foodordersystem.orderdomaincore.OrderDomainServiceImpl;
import com.foodordersystem.orderingservicedomain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.foodordersystem.orderingservicedomain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.foodordersystem.orderingservicedomain.ports.output.message.publisher.restaurant.OrderPaidRestaurantRequestMessagePublisher;
import com.foodordersystem.orderingservicedomain.ports.output.repository.CustomerRepository;
import com.foodordersystem.orderingservicedomain.ports.output.repository.OrderRepository;
import com.foodordersystem.orderingservicedomain.ports.output.repository.RestaurantRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.foodordersystem")
public class OrderTestConfiguration {
    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher(){
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
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
