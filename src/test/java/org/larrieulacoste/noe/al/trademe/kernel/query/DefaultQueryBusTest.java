package org.larrieulacoste.noe.al.trademe.kernel.query;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DefaultQueryBusTest {

    private Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap;
    private DefaultQueryBus defaultQueryBus;

    @BeforeEach
    void setUp() {
        TestQueryHandler testQueryHandler = new TestQueryHandler();
        queryMap = new HashMap<>();
        queryMap.put(TestQuery.class, testQueryHandler);
        defaultQueryBus = new DefaultQueryBus(queryMap);
    }


    @Test
    void send() {
        String queryReturn = defaultQueryBus.send(new TestQuery());
        Assertions.assertThat(queryReturn).isEqualTo("ok");
    }

    @Test
    void sendException() {
        queryMap = new HashMap<>();
        defaultQueryBus = new DefaultQueryBus(queryMap);
        TestQuery testQuery = new TestQuery();

        Assertions.assertThatThrownBy(() -> defaultQueryBus.send(testQuery))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No such query handler for org.larrieulacoste.noe.al.trademe.kernel.query.TestQuery");
    }
}