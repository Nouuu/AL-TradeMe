package org.larrieulacoste.noe.al.trademe.kernel.command;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
