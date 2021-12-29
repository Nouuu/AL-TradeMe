package org.larrieulacoste.noe.al.trademe.kernel.query;

public class TestQueryHandler implements QueryHandler<TestQuery, String> {
    @Override
    public String handle(TestQuery query) {
        return "ok";
    }
}
