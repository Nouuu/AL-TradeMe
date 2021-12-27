package org.larrieulacoste.noe.al.trademe.kernel.command;

public class TestCommandHandler implements CommandHandler<TestCommand, String> {
    @Override
    public String handle(TestCommand command) {
        return "ok";
    }
}
