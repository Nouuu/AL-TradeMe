package org.larrieulacoste.noe.al.trademe.configuration;

import io.quarkus.arc.properties.IfBuildProperty;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InFileContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.features.projects.infrastructure.InMemoryProjects;
import org.larrieulacoste.noe.al.trademe.kernel.io.FileQualifier;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Dependent
final class RepositoryConfiguration {

    @Inject
    @LoggerQualifier(Tradesmen.class)

    Logger tradesmenLogger;
    @Inject
    @LoggerQualifier(Contractors.class)
    Logger contractorsLogger;

    @Inject
    @LoggerQualifier(Invoices.class)
    Logger invoicesLogger;

    @Inject
    @LoggerQualifier(Projects.class)
    Logger projectsLogger;

    @Inject
    SerializationEngine serializerEngine;

    @Inject
    DeserializationEngine deserializerEngine;

    @Inject
    @FileQualifier("contractors")
    Reader contractorsReader;

    @Inject
    @FileQualifier("contractors")
    Writer contractorsWriter;

    @Produces
    @Singleton
    Tradesmen tradesmen() {
        return new InMemoryTradesmen(tradesmenLogger);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "false")
    @Singleton
    Contractors jsonContractors() {
        var inMemory = new InMemoryContractors(contractorsLogger);
        return new InFileContractors(
                inMemory,
                serializerEngine,
                deserializerEngine,
                contractorsReader,
                contractorsWriter);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "true")
    @Singleton
    Contractors inMemoryContractors() {
        return new InMemoryContractors(contractorsLogger);
    }

    @Produces
    @Singleton
    Invoices invoices() {
        return new InMemoryInvoices(invoicesLogger);
    }

    @Produces
    @Singleton
    Projects projects() {
        return new InMemoryProjects(projectsLogger);
    }
}
