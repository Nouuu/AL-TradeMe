package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.command.CommandHandler;

public class CreateTradesmanService implements CommandHandler<CreateTradesman, EntityId> {
    private final Tradesmen tradesmen;

    public CreateTradesmanService(Tradesmen tradesmen) {
        this.tradesmen = tradesmen;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        final EntityId userId = tradesmen.nextId();
        Tradesman tradesman = Tradesman.of(
                userId,
                NotEmptyString.of(createTradesman.lastname),
                NotEmptyString.of(createTradesman.firstname),
                EmailAddress.of(createTradesman.email),
                Password.of(createTradesman.password)
        );
        tradesmen.save(tradesman);
        return userId;
    }
}