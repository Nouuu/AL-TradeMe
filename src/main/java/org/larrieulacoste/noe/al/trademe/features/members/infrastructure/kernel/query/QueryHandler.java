package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.query;

@FunctionalInterface
public interface QueryHandler<Q extends Query, R> {
    R handle(Q command);
}
