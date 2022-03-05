package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethodType;

record ContractorRequest(
        String userId,
        @Schema(defaultValue = "firstname") String firstname,
        @Schema(defaultValue = "lastname") String lastname,
        @Schema(defaultValue = "email@email.com") String email,
        @Schema(defaultValue = "passworD@123") String password,
        @Schema(anyOf = PaymentMethodType.class) String paymentMethodType,
        String paymentMethodRessource
) {
}
