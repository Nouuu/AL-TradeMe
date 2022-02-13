package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

record TradesmanRequest(
        String userId,
        String firstname,
        String lastname,
        String email,
        String password,
        @Schema(anyOf = PaymentMethodType.class) String paymentMethodType,
        String paymentMethodRessource
) {
}
