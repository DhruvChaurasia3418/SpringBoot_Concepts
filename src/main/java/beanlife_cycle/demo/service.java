package beanlife_cycle.demo;

import org.springframework.stereotype.Component;

@Component
public class service {

    public service(){
        System.out.println(" service class constructor is called");
    }
}
