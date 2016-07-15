package com.eugenes.functional.util;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.support.ui.Duration;
import org.sikuli.script.Screen;

import com.eugenes.functional.util.Sleeper.SleeperImpl;

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

    @Inject
    private SleeperImpl sleeper;

    public void clickMiddleScreen() throws InterruptedException {

        screen.getCenter().click();
        sleeper.sleep(new Duration(1, TimeUnit.SECONDS));

    }

    public void doubleClickMiddleScreen() throws InterruptedException {

        screen.getCenter().doubleClick();
        sleeper.sleep(new Duration(1, TimeUnit.SECONDS));

    }
}
