package com.eugenes.functional.util;

import javax.inject.Inject;

import org.sikuli.script.Screen;
import org.springframework.stereotype.Component;

/**
 * Facade over Sikuli to abstract interaction with the screen
 * 
 * @author eugene.shragovich
 *
 */

public class SikuliSupport {

    @Inject
    private Screen screen;

    /**
     * This method is to be used when a boolean needed instead of a Match object that exists()
     * normally returns.
     * 
     * @return boolean
     */
    public boolean doesExist(final String pattern) {

        if (screen.exists(pattern) != null) {
            return true;
        }

        return false;

    }

}
