package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;

public class CreateMemberService {
    private final Repository<Contractor> contractorRepository;
    private final Repository<Tradesman> tradesmanRepository;

    public CreateMemberService(Repository<Contractor> contractorRepository, Repository<Tradesman> tradesmanRepository) {
        this.contractorRepository = contractorRepository;
        this.tradesmanRepository = tradesmanRepository;
    }

    public void save(Contractor contractor) {
        contractorRepository.save(contractor);
    }
    public void save(Tradesman tradesman) {
        tradesmanRepository.save(tradesman);
    }

    public void handle(CreateTradesman createTradesman) {
        final EntityId userId = contractorRepository.nextId();
        Tradesman tradesman = Tradesman.of(
                userId,
                NotEmptyString.of(createTradesman.lastname),
                NotEmptyString.of(createTradesman.firstname),
                EmailAddress.of(createTradesman.email),
                Password.of(createTradesman.password)
        );
        save(tradesman);
    }

    public void handle(CreateContractor createContractor) {
        final EntityId userId = contractorRepository.nextId();
        Contractor contractor = Contractor.of(
                userId,
                NotEmptyString.of(createContractor.lastname),
                NotEmptyString.of(createContractor.firstname),
                EmailAddress.of(createContractor.email),
                Password.of(createContractor.password)
        );
        save(contractor);
    }
}
