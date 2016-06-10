package com.eugenes.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Duration;
import org.springframework.stereotype.Component;


public interface Sleeper {

    void sleep(final Duration duration);

    @Component
    public static class SleeperImpl implements Sleeper {

        @Override
        public void sleep(final Duration duration) {
            long millis = duration.in(TimeUnit.MILLISECONDS);

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
            }

        }

    }
}
