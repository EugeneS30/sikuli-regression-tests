package com.eugenes.functional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.eugenes.functional.util.BufferSupport;
import com.eugenes.functional.util.KeyboardControlSupport;
import com.eugenes.functional.util.MouseControlSupport;

/**
 * @author eugene.shragovich
 */

@DependsOn("screen")
@Configuration
public class SupportClassesConfiguration {

    @Bean()
    public KeyboardControlSupport keyboardControlSupport() {
        return new KeyboardControlSupport();
    }

    @Bean
    public MouseControlSupport mouseControlSupport() {
        return new MouseControlSupport();
    }
    
    @Bean()
    public BufferSupport bufferSupport() {
        return new BufferSupport();
    }

}
