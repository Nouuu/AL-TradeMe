package org.larrieulacoste.noe.al.trademe.kernel.event;

public class TestEventSubscriber implements EventSubscriber{
    @Override
    public void accept(Event event) {
        throw new RuntimeException("Success !");
    }
}
