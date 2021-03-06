package org.larrieulacoste.noe.al.trademe.features.invoices.web;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.query.*;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("invoice")
@Produces(MediaType.APPLICATION_JSON)
public final class InvoiceController {
    private final QueryBus queryBus;

    InvoiceController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GET
    @Path("{invoiceId}")
    @Operation(summary = "Retrieve invoice by ID", description = "Retrieve invoice giving invoice's ID")
    public InvoiceResponse getById(@PathParam("invoiceId") String invoiceId) {
        Invoice invoice = queryBus.send(new RetrieveInvoiceById(EntityId.of(invoiceId)));
        return getInvoiceResponse(invoice);
    }

    @GET
    @Path("contractor/{contractorId}")
    @Operation(summary = "Retrieve contractor invoices", description = "Retrieve contractor invoices giving contractor's ID")
    public InvoicesResponse getByContractorId(@PathParam("contractorId") String contractorId) {
        List<Invoice> invoices = queryBus.send(new RetrieveContractorInvoices(EntityId.of(contractorId)));

        return getInvoicesResponse(invoices);
    }

    @GET
    @Path("tradesman/{tradesmanId}")
    @Operation(summary = "Retrieve tradesman invoices", description = "Retrieve tradesman invoices giving tradesman's ID")
    public InvoicesResponse getByTradesmanId(@PathParam("tradesmanId") String tradesmanId) {
        List<Invoice> invoices = queryBus.send(new RetrieveTradesmanInvoices(EntityId.of(tradesmanId)));

        return getInvoicesResponse(invoices);
    }

    @GET
    @Path("contractor")
    @Operation(summary = "Retrieve contractor invoices", description = "Retrieve contractor invoices giving contractor's ID")
    public InvoicesResponse getContractorsInvoices() {
        List<Invoice> invoices = queryBus.send(new RetrieveContractorsInvoices());

        return getInvoicesResponse(invoices);
    }

    @GET
    @Path("tradesman")
    @Operation(summary = "Retrieve tradesman invoices", description = "Retrieve tradesman invoices giving tradesman's ID")
    public InvoicesResponse getTradesmenInvoices() {
        List<Invoice> invoices = queryBus.send(new RetrieveTradesmenInvoices());

        return getInvoicesResponse(invoices);
    }

    @GET
    @Operation(summary = "Retrieve invoices", description = "Retrieve all invoices")
    public InvoicesResponse getAll() {
        List<Invoice> invoices = queryBus.send(new RetrieveAllInvoices());

        return getInvoicesResponse(invoices);
    }

    private InvoicesResponse getInvoicesResponse(List<Invoice> invoices) {
        return new InvoicesResponse(
                invoices.stream().map(this::getInvoiceResponse).toList(),
                invoices.size());
    }

    private InvoiceResponse getInvoiceResponse(Invoice invoice) {
        return new InvoiceResponse(
                invoice.invoiceId().value(),
                invoice.memberType().value,
                invoice.memberId().value(),
                invoice.occurredDate(),
                invoice.paymentMethodType().value,
                invoice.amount().value()
        );
    }
}
