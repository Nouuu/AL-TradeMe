package org.larrieulacoste.noe.al.trademe.features.projects.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

record ProjectProfessionRequest(
        @Schema(enumeration = {"Plumber", "Electrician"}) String professionName
) {
}
