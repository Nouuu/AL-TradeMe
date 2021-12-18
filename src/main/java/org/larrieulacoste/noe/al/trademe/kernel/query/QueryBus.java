package org.larrieulacoste.noe.al.trademe.kernel.query;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
