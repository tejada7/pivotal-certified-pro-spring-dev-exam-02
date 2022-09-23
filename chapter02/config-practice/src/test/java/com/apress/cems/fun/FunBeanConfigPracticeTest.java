/*
Freeware License, some rights reserved

Copyright (c) 2019 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.cems.fun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing bean stages creation for a bean that is initialized using all three techniques
 *
 * @author Iuliana Cosmina
 * @since 1.0
 */
class FunBeanConfigPracticeTest {

    @Test
    void testBeanLifecyclePractice() {
        final var logConsole = initLogConsole();
        var ctx = new AnnotationConfigApplicationContext(FunBeanConfig.class);
        ctx.registerShutdownHook();

        var funBean = ctx.getBean(FunBean.class);
        then(funBean).isNotNull();

        ctx.close();
        then(logConsole.toString()).contains("Stage 1: Calling the constructor",
                                             "Stage 2: Calling the setter",
                                             "Stage 3: Calling the post-construct",
                                             "Stage 4: Calling the afterPropertiesSet",
                                             "Stage 5: Calling the initBeanMethod",
                                             "Stage 6: Calling the pre-destroyer",
                                             "Stage 7: Calling the destroyer",
                                             "Stage 8: Calling the destroyer"
        );
    }

    private static ByteArrayOutputStream initLogConsole() {
        final ByteArrayOutputStream logConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(logConsole));
        return logConsole;
    }
}
