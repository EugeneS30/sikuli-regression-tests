package com.eugenes.functional.util;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sikuli.script.Screen;

/**
 * Facade over Mouse to abstract interaction with the screen
 * 
 * @author eugene.shragovich
 *
 */

@Slf4j
public class MouseControlSupport {

    @Inject
    private Screen screen;

    public void clickMiddleScreen() {

        screen.getCenter().click();

    }

}
