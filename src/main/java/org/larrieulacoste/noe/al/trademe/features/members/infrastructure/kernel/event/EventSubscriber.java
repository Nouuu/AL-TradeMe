package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.event;

public interface EventSubscriber<E extends Event> {
    void accept(E event);
}
