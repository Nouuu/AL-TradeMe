package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
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
import java.util.List;

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
    SerializationEngine<List<Contractor>> contractorsSerializer;

    @Inject
    DeserializationEngine<List<Contractor>> contractorsDeserializer;

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
    @Singleton
    Contractors contractors() {
        var inMemory = new InMemoryContractors(contractorsLogger);
        return new InFileContractors(
                inMemory,
                contractorsSerializer,
                contractorsDeserializer,
                contractorsReader,
                contractorsWriter);
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
