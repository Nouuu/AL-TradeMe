package org.larrieulacoste.noe.al.trademe.features.members.domain;

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

  public Contractor build(EntityId id) {
    this.id = id;
    return Contractor.of(id, lastname, firstname, email, password, subscriptionStatus, paymentMethod);
  }

  // todo SubscriptionStatus
  // todo paymentMethod

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
