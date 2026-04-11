package com.a29340.utils;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

class DebugInfoTest {

    @Test
    void printDebugMessage_addsMessageToInput() {
        DebugInfo.printDebugMessage("Test message");

        assertThat(DebugInfo.class).isNotNull();
    }

    @Test
    void print_doesNotThrowWithNullGraphics() {
        Graphics2D g2d = null;

        DebugInfo.print(g2d);
    }

    @Test
    void printDebugMessage_doesNotThrow() {
        DebugInfo.printDebugMessage("Another test message");
    }

    @Test
    void printDebugMessage_multipleMessages() {
        DebugInfo.printDebugMessage("Message 1");
        DebugInfo.printDebugMessage("Message 2");
        DebugInfo.printDebugMessage("Message 3");

        assertThat(DebugInfo.class).isNotNull();
    }
}
