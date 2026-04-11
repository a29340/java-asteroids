package com.a29340.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationsTest {

    @Test
    void debugMode_returnsFalseWhenEnvNotSet() {
        Boolean result = Configurations.debugMode();

        assertThat(result).isFalse();
    }

    @Test
    void runMode_constantExists() {
        String runMode = Configurations.RUN_MODE;

        assertThat(runMode).isNull();
    }

    @Test
    void debugMode_handlesNullEnvironment() {
        if (Configurations.RUN_MODE == null) {
            assertThat(Configurations.debugMode()).isFalse();
        }
    }
}
