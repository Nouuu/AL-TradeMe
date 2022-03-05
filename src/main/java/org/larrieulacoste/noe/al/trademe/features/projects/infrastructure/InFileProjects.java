package org.larrieulacoste.noe.al.trademe.features.projects.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.SerializationEngine;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class InFileProjects implements Projects {
    private final InMemoryProjects inMemoryProjects;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final SerializationEngine serializer;
    private final DeserializationEngine deserializer;
    private final Reader reader;
    private final Writer writer;

    public InFileProjects(InMemoryProjects inMemoryProjects,
                          SerializationEngine serializer,
                          DeserializationEngine deserializer,
                          Reader reader,
                          Writer writer) {
        this.inMemoryProjects = inMemoryProjects;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.reader = reader;
        this.writer = writer;
        this.read();
    }

    @Override
    public void save(Project project) {
        inMemoryProjects.save(project);
        this.write();
    }

    @Override
    public Project byId(EntityId entityId) throws UserNotFoundException {
        return inMemoryProjects.byId(entityId);
    }

    @Override
    public List<Project> findAll() {
        return inMemoryProjects.findAll();
    }

    @Override
    public void remove(Project item) {
        inMemoryProjects.remove(item);
        this.write();
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }

    @Override
    public List<Project> getTradesmanProjects(EntityId tradesmanId) {
        return inMemoryProjects.getTradesmanProjects(tradesmanId);
    }

    @Override
    public List<Project> getContractorProjects(EntityId contractorId) {
        return inMemoryProjects.getContractorProjects(contractorId);
    }


    private void write() {
        var data = serializer.apply(inMemoryProjects.findAll());
        writer.write(data);
    }

    private void read() {
        var data = deserializer.apply(reader.read(), Project[].class);
        if (data != null) {
            for (Project project : data) {
                inMemoryProjects.save(project);
                this.counter.set(Integer.parseInt(project.projectId().value()));
            }
        }
    }
}
