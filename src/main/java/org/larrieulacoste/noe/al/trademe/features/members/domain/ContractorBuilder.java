package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

public class ContractorBuilder {
  private final StringValidators stringValidator;

  public ContractorBuilder(StringValidators stringValidations) {
    this.stringValidator = stringValidations;
  }

  private EntityId id;
  private NotEmptyString lastname;
  private NotEmptyString firstname;
  private EmailAddress email;
  private Password password;
  private SubscriptionStatus subscriptionStatus;
  private PaymentMethod paymentMethod;

  public ContractorBuilder withLastname(String lastname) {
    this.lastname = NotEmptyString.of(lastname, stringValidator);
    return this;
  }

  public ContractorBuilder withLastname(NotEmptyString lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContractorBuilder withFirstname(String firstname) {
    this.firstname = NotEmptyString.of(firstname, stringValidator);
    return this;
  }

  public ContractorBuilder withFirstname(NotEmptyString firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContractorBuilder withEmail(String emailAddress) {
    this.email = EmailAddress.of(emailAddress, stringValidator);
    return this;
  }

  public ContractorBuilder withEmail(EmailAddress emailAddress) {
    this.email = emailAddress;
    return this;
  }

  public ContractorBuilder withPassword(String password) {
    this.password = Password.of(password, stringValidator);
    return this;
  }

  public ContractorBuilder withPassword(Password password) {
    this.password = password;
    return this;
  }

  public ContractorBuilder withSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
    this.subscriptionStatus = subscriptionStatus;
    return this;
  }

  public ContractorBuilder withPaymentMethod(String paymentMethod, String paymentInfo) {
    this.paymentMethod = PaymentMethod.of(paymentMethod, paymentInfo);
    return this;
  }

  public ContractorBuilder withPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public Contractor build(EntityId id) {
    this.id = id;
    return Contractor.of(id, lastname, firstname, email, password, subscriptionStatus, paymentMethod);
  }

  public ContractorEventEntity buildEventEntity() {
    return ContractorEventEntity.of(id, firstname.value, lastname.value, email.value, password.value, paymentMethod);
  }

  public ContractorEventEntity buildEventEntityWithoutPassword() {
    return ContractorEventEntity.withoutPassword(id, firstname.value, lastname.value, email.value, paymentMethod);
  }

  public void clear() {
    id = null;
    lastname = null;
    firstname = null;
    email = null;
    password = null;
    subscriptionStatus = null;
    paymentMethod = null;
  }
}
