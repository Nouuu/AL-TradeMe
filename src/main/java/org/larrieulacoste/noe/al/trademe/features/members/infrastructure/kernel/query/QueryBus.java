package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.query;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
