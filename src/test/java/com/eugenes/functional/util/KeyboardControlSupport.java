package com.eugenes.functional.util;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sikuli.script.FindFailed;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;

/**
 * @author eugene.shragovich
 */

@Slf4j
public class KeyboardControlSupport {

    @Inject
    private Screen screen;

    public void typeText(String text) {

        screen.type(text);

    }

    public void typeTextToTarget(String target, String text) {

        screen.type(target, text);

    }

    public void selectAll() {

        log.info("Selecting all");
        screen.type("a", KeyModifier.CTRL);

    }

    public void copyToBuffer() {

        log.info("Copying to buffer");
        screen.type("c", KeyModifier.CTRL);

    }

    public void pasteText(String text) {

        screen.paste(text);

    }

    public void pasteTextToTarget(String target, String text) throws FindFailed {

        screen.paste(target, text);

    }

}
