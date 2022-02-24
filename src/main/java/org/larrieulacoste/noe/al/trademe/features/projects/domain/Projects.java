package org.larrieulacoste.noe.al.trademe.features.projects.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.Repository;

import java.util.List;

public interface Projects extends Repository<Project> {
    List<Project> getTradesmanProjects(EntityId tradesmanId);

    List<Project> getContractorProjects(EntityId tradesmanId);

}
