package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import java.util.List;

public final class JdbcContractors implements Contractors {
    private static final String NOT_YET_IMPLEMENTED_MESSAGE = "Not yet implemented";
    private final Logger logger;

    public JdbcContractors() {
        this.logger = LoggerFactory.getLoggerStatic(this);
        this.logger.error("Warning !!! Your are instancing a not yet implemented repository");
    }

    @Override
    public void save(Contractor user) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public Contractor byId(EntityId entityId) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public List<Contractor> findAll() {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public void remove(Contractor item) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public EntityId nextId() {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }
}
