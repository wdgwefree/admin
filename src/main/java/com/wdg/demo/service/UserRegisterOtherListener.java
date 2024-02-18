package com.wdg.demo.service;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterOtherListener {

    @EventListener
    @Order(2)
    public void other(UserRegisterEvent event){
        System.out.println("-------------------------");
        System.out.println("SMS操作");
    }


    @EventListener
    @Order(1)
    public void temp(UserRegisterEvent event){
        System.out.println("-------------------------");
        System.out.println("其他操作");
    }
}