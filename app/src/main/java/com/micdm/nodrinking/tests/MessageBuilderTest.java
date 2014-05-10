package com.micdm.nodrinking.tests;

import android.test.InstrumentationTestCase;

import com.micdm.nodrinking.MessageBuilder;

public class MessageBuilderTest extends InstrumentationTestCase {

    public void testBuildIfDaysEqual1() {
        assertEquals("Один день", MessageBuilder.build(getInstrumentation().getTargetContext(), 1));
    }

    public void testBuildIfDaysEqual2() {
        assertEquals("2 дня", MessageBuilder.build(getInstrumentation().getTargetContext(), 2));
    }

    public void testBuildIfDaysEqual5() {
        assertEquals("5 дней", MessageBuilder.build(getInstrumentation().getTargetContext(), 5));
    }

    public void testBuildIfDaysEqual7() {
        assertEquals("Одна неделя", MessageBuilder.build(getInstrumentation().getTargetContext(), 7));
    }
}
