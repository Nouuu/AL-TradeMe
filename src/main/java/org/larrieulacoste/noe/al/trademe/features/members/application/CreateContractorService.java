package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class CreateContractorService implements CommandHandler<CreateContractor, EntityId> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;

    public CreateContractorService(Contractors contractors, MemberValidationService memberValidationService) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
    }

    @Override
    public EntityId handle(CreateContractor createContractor) {
        if (!memberValidationService.isContractorValid(createContractor)) {
            throw new InvalidUserException("Invalid contractor creation");
        }
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
