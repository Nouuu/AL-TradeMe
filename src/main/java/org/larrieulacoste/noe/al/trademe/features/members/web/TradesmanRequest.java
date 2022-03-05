package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethodType;

record TradesmanRequest(
        String userId,
        @Schema(defaultValue = "firstname") String firstname,
        @Schema(defaultValue = "lastname") String lastname,
        @Schema(defaultValue = "mail@mail.com") String email,
        @Schema(defaultValue = "passworD@123") String password,
        @Schema(anyOf = PaymentMethodType.class) String paymentMethodType,
        String paymentMethodRessource,
        @Schema(defaultValue = "Plumber") String profession,
        @Schema(defaultValue = "2") Double longitude,
        @Schema(defaultValue = "48") Double latitude,
        @Schema(defaultValue = "10") Double activityRadius,
        @Schema(defaultValue = "50") Double dailyRate,
        @Schema(defaultValue = "Paris") String locationName) {
}
