package org.larrieulacoste.noe.al.trademe.kernel.command;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DefaultCommandBusTest {
    private Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap;
    private DefaultCommandBus defaultCommandBus;

    @BeforeEach
    void setUp() {
        TestCommandHandler testCommandHandler = new TestCommandHandler();
        commandMap = new HashMap<>();
        commandMap.put(TestCommand.class, testCommandHandler);
        defaultCommandBus = new DefaultCommandBus(commandMap);
    }

    @Test
    void send() {
        String commandReturn = defaultCommandBus.send(new TestCommand());
        Assertions.assertThat(commandReturn).isEqualTo("ok");
    }

    @Test
    void sendException() {
        commandMap = new HashMap<>();
        defaultCommandBus = new DefaultCommandBus(commandMap);
        TestCommand testCommand = new TestCommand();

        Assertions.assertThatThrownBy(() -> defaultCommandBus.send(testCommand))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No such command handler for org.larrieulacoste.noe.al.trademe.kernel.command.TestCommand");
    }
}