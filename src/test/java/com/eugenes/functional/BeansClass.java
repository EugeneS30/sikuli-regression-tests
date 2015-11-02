package com.eugenes.functional;

import org.springframework.stereotype.Component;

@Component
public class BeansClass implements InterfaceStub{
    
    public void play() {
        System.out.println("playing...");
    }

}
