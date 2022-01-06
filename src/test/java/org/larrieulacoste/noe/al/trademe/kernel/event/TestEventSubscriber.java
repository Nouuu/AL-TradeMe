package org.larrieulacoste.noe.al.trademe.kernel.event;

public class TestEventSubscriber implements EventSubscriber<Event>{
    @Override
    public void accept(Event event) {
        throw new RuntimeException("Success !");
    }
}
