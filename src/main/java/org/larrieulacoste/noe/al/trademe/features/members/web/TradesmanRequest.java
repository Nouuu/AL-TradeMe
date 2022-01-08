package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

final class TradesmanRequest {
    public final String userId;
    public final String firstname;
    public final String lastname;
    public final String email;
    public final String password;
    @Schema(anyOf = PaymentMethodType.class)
    public final String paymentMethodType;
    public final String paymentMethodRessource;

    public TradesmanRequest(String userId, String firstname, String lastname, String email, String password, String paymentMethodType, String paymentMethodRessource) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.paymentMethodType = paymentMethodType;
        this.paymentMethodRessource = paymentMethodRessource;
    }
}
