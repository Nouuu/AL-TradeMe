package org.larrieulacoste.noe.al.trademe.kernel.command;

public interface CommandBus {
    <C extends Command, R> R send(C command);
}
