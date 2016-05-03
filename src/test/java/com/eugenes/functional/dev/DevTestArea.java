package com.eugenes.functional.dev;

import java.util.Iterator;

import org.junit.Test;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class DevTestArea {

    @Test
    public void highlightTest() throws FindFailed {

        ImagePath.setBundlePath("C:/temp/");
        Settings.MinSimilarity = 0.50;
        String startBtn = "win7StartBtn.png";
        String calcIcon = "calcIconSmall.png";
        String football = "footballImg.png";

        Screen s = new Screen(1);
        // Match m = s.find(calcIcon);
        // m.highlight(3);
        // Match m1 = s.wait(imageName);

        for (Iterator<Match> list = s.findAll(football); list.hasNext();) {
            Match m = list.next();
            m.highlight(5);
        }

        // for (Match m : s.findAll(football)) {
        // m.highlight(1);
        // }

    }
}
