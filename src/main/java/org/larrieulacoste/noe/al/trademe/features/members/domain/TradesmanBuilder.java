package org.larrieulacoste.noe.al.trademe.features.members.domain;

import java.util.List;

import org.larrieulacoste.noe.al.trademe.domain.model.ActivityPerimeter;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.DailyRate;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.domain.model.Project;
import org.larrieulacoste.noe.al.trademe.domain.model.Skill;

public final class TradesmanBuilder {
  private final StringValidators stringValidators;

  private NotEmptyString lastname;
  private NotEmptyString firstname;
  private EmailAddress email;
  private Password password;
  private SubscriptionStatus subscriptionStatus;
  private PaymentMethod paymentMethod;
  private Profession profession;
  private double longitude;
  private double latitude;
  private double activityRadius;
  private double dailyRate;
  private List<Skill> skills;
  private List<Project> projects;
  private Location location;

  public TradesmanBuilder(StringValidators stringValidators) {
    this.stringValidators = stringValidators;
  }

  public TradesmanBuilder withLastname(String lastname) {
    this.lastname = NotEmptyString.of(lastname, stringValidators);
    return this;
  }

  public TradesmanBuilder withFirstname(String firstname) {
    this.firstname = NotEmptyString.of(firstname, stringValidators);
    return this;
  }

  public TradesmanBuilder withEmail(String email) {
    this.email = EmailAddress.of(email, stringValidators);
    return this;
  }

  public TradesmanBuilder withPassword(String password) {
    this.password = Password.of(password, stringValidators);
    return this;
  }

  public TradesmanBuilder withSubscribtionStatus(SubscriptionStatus subscriptionStatus) {
    this.subscriptionStatus = subscriptionStatus;
    return this;
  }

  public Tradesman build(EntityId entityId) {
    ActivityPerimeter activityPerimeter = new ActivityPerimeter(longitude, latitude, activityRadius);
    DailyRate localDailyRate = new DailyRate(Amount.of(dailyRate));
    TradesmanProfessionalAbilities professionalAbilities = new TradesmanProfessionalAbilities(profession, location, skills, activityPerimeter, localDailyRate);
    return Tradesman.of(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod, professionalAbilities, projects);
  }

}
