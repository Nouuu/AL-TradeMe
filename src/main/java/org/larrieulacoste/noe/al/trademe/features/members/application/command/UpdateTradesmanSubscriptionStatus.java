package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record UpdateTradesmanSubscriptionStatus(
        EntityId tradesmanId,
        SubscriptionStatus subscriptionStatus
) implements Command {

}
