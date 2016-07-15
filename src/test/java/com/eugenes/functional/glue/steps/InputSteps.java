package com.eugenes.functional.glue.steps;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eugenes.functional.util.BufferSupport;
import com.eugenes.functional.util.KeyboardControlSupport;
import com.eugenes.functional.util.MouseControlSupport;

import cucumber.api.java.en.When;

/**
 * @author eugene.shragovich
 */

@Slf4j
@Component
public class InputSteps extends AbstractSteps {

    @Autowired
    private Screen screen;

    @Inject
    private KeyboardControlSupport keyboard;

    @Inject
    private MouseControlSupport mouse;
    
    @Inject
    private BufferSupport buffer;

    @Setter
    private Region region;

    @When("^I click in the middle of the screen$")
    public void i_click_in_the_middle_of_the_screen() throws FindFailed, InterruptedException {

        mouse.clickMiddleScreen();

    }

    @When("^I doubleclick in the middle of the screen$")
    public void i_doubleclick_in_the_middle_of_the_screen() throws FindFailed, InterruptedException {

        mouse.doubleClickMiddleScreen();

    }

    @When("^I type text: \"(.*?)\"$")
    public void i_type_text(final String text) throws Throwable {

        keyboard.typeText(text);

    }
    
    @When("^I paste text: \"(.*?)\"$")
    public void i_paste_text(final String text) throws Throwable {

        keyboard.pasteText(text);

    }
    
    @When("^buffer contains text: \"(.*?)\"$")
    public void buffer_contains_text(final String text) throws Throwable {
        
        assertThat(buffer.getBufferContents()).isEqualTo(text);

    }
    
    @When("^I select all$")
    public void i_select_all() throws Throwable {

        keyboard.selectAll();

    }

    @When("^I copy to buffer$")
    public void i_copy_to_buffer() throws Throwable {
        
        keyboard.copyToBuffer();

    }
    

}
