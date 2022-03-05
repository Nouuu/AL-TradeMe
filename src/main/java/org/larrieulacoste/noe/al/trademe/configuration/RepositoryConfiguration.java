package org.larrieulacoste.noe.al.trademe.configuration;

import io.quarkus.arc.properties.IfBuildProperty;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InFileInvoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InFileContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InFileTradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.features.projects.infrastructure.InFileProjects;
import org.larrieulacoste.noe.al.trademe.features.projects.infrastructure.InMemoryProjects;
import org.larrieulacoste.noe.al.trademe.kernel.io.FileQualifier;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.SerializationEngine;

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

    @Inject
    @FileQualifier("tradesmen")
    Reader tradesmenReader;

    @Inject
    @FileQualifier("tradesmen")
    Writer tradesmenWriter;

    @Inject
    @FileQualifier("invoices")
    Reader invoicesReader;

    @Inject
    @FileQualifier("invoices")
    Writer invoicesWriter;

    @Inject
    @FileQualifier("projects")
    Reader projectsReader;

    @Inject
    @FileQualifier("projects")
    Writer projectsWriter;

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "false")
    @Singleton
    Tradesmen jsonTradesmen() {
        var inMemory = new InMemoryTradesmen(tradesmenLogger);
        return new InFileTradesmen(
                inMemory,
                serializerEngine,
                deserializerEngine,
                tradesmenReader,
                tradesmenWriter);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "true")
    @Singleton
    Tradesmen inMemoryTradesmen() {
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
    @IfBuildProperty(name = "repository.in-memory", stringValue = "false")
    @Singleton
    Invoices jsonInvoices() {
        var inMemory = new InMemoryInvoices(invoicesLogger);
        return new InFileInvoices(
                inMemory,
                serializerEngine,
                deserializerEngine,
                invoicesReader,
                invoicesWriter);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "true")
    @Singleton
    Invoices inMemoryInvoices() {
        return new InMemoryInvoices(invoicesLogger);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "false")
    @Singleton
    Projects jsonProjects() {
        var inMemory = new InMemoryProjects(projectsLogger);
        return new InFileProjects(
                inMemory,
                serializerEngine,
                deserializerEngine,
                projectsReader,
                projectsWriter);
    }

    @Produces
    @IfBuildProperty(name = "repository.in-memory", stringValue = "true")
    @Singleton
    Projects inMemoryProjects() {
        return new InMemoryProjects(projectsLogger);
    }
}
