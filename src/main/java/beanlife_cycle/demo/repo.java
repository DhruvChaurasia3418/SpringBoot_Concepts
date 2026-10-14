package beanlife_cycle.demo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class repo {

    public repo(){
        System.out.println("This is constructor of repo class and its bean i scretaed");
    }
}
