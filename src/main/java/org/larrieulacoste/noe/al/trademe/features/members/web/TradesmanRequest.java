package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

final class TradesmanRequest {
    final String userId;
    final String firstname;
    final String lastname;
    final String email;
    final String password;
    @Schema(anyOf = PaymentMethodType.class)
    final String paymentMethodType;
    final String paymentMethodRessource;

    TradesmanRequest(String userId, String firstname, String lastname, String email, String password, String paymentMethodType, String paymentMethodRessource) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.paymentMethodType = paymentMethodType;
        this.paymentMethodRessource = paymentMethodRessource;
    }
}
