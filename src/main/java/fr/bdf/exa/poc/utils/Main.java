package fr.bdf.exa.poc.utils;

import javax.inject.Inject;

import fr.bdf.exa.poc.service.ServiceBean;

public class Main {

    @Inject
    private static ServiceBean serviceBean;
    
    public static void main(String[] args) {
        //System.out.println(new ServiceBean().doFallback());
    }

}
