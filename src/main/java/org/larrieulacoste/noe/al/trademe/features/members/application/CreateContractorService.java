package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.command.CommandHandler;

import java.util.Objects;

public class CreateContractorService implements CommandHandler<CreateContractor, EntityId> {
    private final Contractors contractors;

    public CreateContractorService(Contractors contractors) {
        this.contractors = Objects.requireNonNull(contractors);
    }

    @Override
    public EntityId handle(CreateContractor createContractor) {
        final EntityId userId = contractors.nextId();
        Contractor contractor = Contractor.of(
                userId,
                NotEmptyString.of(createContractor.lastname),
                NotEmptyString.of(createContractor.firstname),
                EmailAddress.of(createContractor.email),
                Password.of(createContractor.password)
        );
        contractors.save(contractor);
        return userId;
    }
}
