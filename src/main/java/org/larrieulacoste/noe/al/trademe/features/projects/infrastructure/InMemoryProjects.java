package org.larrieulacoste.noe.al.trademe.features.projects.infrastructure;

import org.larrieulacoste.noe.al.trademe.domain.exception.NotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.exception.ProjectNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryProjects implements Projects {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Project> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryProjects(Logger logger) {
        this.logger = logger;
    }

    @Override
    public List<Project> getTradesmanProjects(EntityId tradesmanId) {
        return List.copyOf(
                data.values().stream()
                        .filter(project -> project.tradesmenIds()
                                .stream()
                                .anyMatch(entityId -> entityId.equals(tradesmanId)))
                        .toList()
        );
    }

    @Override
    public List<Project> getContractorProjects(EntityId contractorId) {
        return List.copyOf(
                data.values().stream()
                        .filter(project -> project.contractorId().equals(contractorId))
                        .toList()
        );
    }

    @Override
    public void save(Project item) {
        logger.log("Saving project in memory repository : " + item);

        data.put(Objects.requireNonNull(item).projectId(), item);

    }

    @Override
    public Project byId(EntityId projectId) throws NotFoundException {
        logger.log("Retrieving invoice by ID from memory repository : " + projectId);

        final Project project = data.get(Objects.requireNonNull(projectId));
        if (project == null) {
            throw new ProjectNotFoundException("No Project for " + projectId.value());
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public void remove(Project item) {
        data.remove(item.projectId());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}
