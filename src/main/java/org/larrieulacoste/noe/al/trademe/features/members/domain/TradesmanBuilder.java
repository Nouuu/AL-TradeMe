package org.larrieulacoste.noe.al.trademe.features.members.domain;

import java.util.ArrayList;
import java.util.List;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.ActivityPerimeter;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.Coordinate;
import org.larrieulacoste.noe.al.trademe.domain.model.DailyRate;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
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
  private DailyRate dailyRate;
  private List<Skill> skills = new ArrayList<>();
  private NotEmptyString locationName;
  private EntityId id;

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

  public TradesmanBuilder withPaymentMethod(String paymentMethod, String paymentInfo) {
    this.paymentMethod = PaymentMethod.of(paymentMethod, paymentInfo);
    return this;
  }

  public TradesmanBuilder withProfession(Profession profession) {
    this.profession = profession;
    return this;
  }

  public TradesmanBuilder withLongitude(double longitude) {
    this.longitude = longitude;
    return this;
  }

  public TradesmanBuilder withLatitude(double latitude) {
    this.latitude = latitude;
    return this;
  }

  public TradesmanBuilder withActivityRaidus(double activityRaidus) {
    this.activityRadius = activityRaidus;
    return this;
  }

  public TradesmanBuilder withDailyRate(double dailyRate) {
    this.dailyRate = DailyRate.of(Amount.of(dailyRate));
    return this;
  }

  public TradesmanBuilder withSkills(List<Skill> skills) {
    this.skills.addAll(skills);
    return this;
  }

  public TradesmanBuilder addSkill(Skill skill) {
    this.skills.add(skill);
    return this;
  }

  public TradesmanBuilder withLocationName(String locationName) {
    this.locationName = NotEmptyString.of(locationName, stringValidators);
    return this;
  }

  public Tradesman build(EntityId entityId) {
    this.id = entityId;
    Location location = Location.of(Coordinate.of(longitude, latitude), locationName);
    ActivityPerimeter activityPerimeter = new ActivityPerimeter(longitude, latitude, activityRadius);
    TradesmanProfessionalAbilities professionalAbilities = new TradesmanProfessionalAbilities(profession, location,
        skills, activityPerimeter, dailyRate);
    return Tradesman.of(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod,
        professionalAbilities);
  }

  public TradesmanEventEntity buildTradesmanEventEntity() {
    Location location = Location.of(Coordinate.of(longitude, latitude), locationName);
    ActivityPerimeter activityPerimeter = new ActivityPerimeter(longitude, latitude, activityRadius);
    TradesmanProfessionalAbilities professionalAbilities = new TradesmanProfessionalAbilities(profession, location,
        skills, activityPerimeter, dailyRate);
    return TradesmanEventEntity.of(id, firstname.value, lastname.value, email.value, password.value, paymentMethod,
        professionalAbilities);
  }

  public void clear() {
    lastname = null;
    firstname = null;
    email = null;
    password = null;
    subscriptionStatus = null;
    paymentMethod = null;
    profession = null;
    longitude = 0;
    latitude = 0;
    activityRadius = 0;
    dailyRate = DailyRate.of(Amount.of(0));
    skills = null;
    locationName = null;
  }

}
