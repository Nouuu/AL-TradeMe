package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.*;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TradesmanBuilder {
  private final StringValidators stringValidators;
  private final DateValidators dateValidators;

  private NotEmptyString lastname;
  private NotEmptyString firstname;
  private EmailAddress email;
  private Password password;
  private SubscriptionStatus subscriptionStatus;
  private PaymentMethod paymentMethod;
  private Profession profession;
  private double longitude;
  private double latitude;
  private ActivityRadius activityRadius;
  private DailyRate dailyRate;
  private List<Skill> skills = new ArrayList<>();
  private List<Period> unavailability = new ArrayList<>();
  private NotEmptyString locationName;

  public TradesmanBuilder(StringValidators stringValidators, DateValidators dateValidators) {
    this.stringValidators = stringValidators;
    this.dateValidators = dateValidators;
  }

  public TradesmanBuilder withLastname(String lastname) {
    this.lastname = NotEmptyString.of(lastname, stringValidators);
    return this;
  }

  public TradesmanBuilder withLastname(NotEmptyString lastname) {
    this.lastname = lastname;
    return this;
  }

  public TradesmanBuilder withFirstname(String firstname) {
    this.firstname = NotEmptyString.of(firstname, stringValidators);
    return this;
  }

  public TradesmanBuilder withFirstname(NotEmptyString firstname) {
    this.firstname = firstname;
    return this;
  }

  public TradesmanBuilder withEmail(String email) {
    this.email = EmailAddress.of(email, stringValidators);
    return this;
  }

  public TradesmanBuilder withEmail(EmailAddress email) {
    this.email = email;
    return this;
  }

  public TradesmanBuilder withPassword(String password) {
    this.password = Password.of(password, stringValidators);
    return this;
  }

  public TradesmanBuilder withPassword(Password password) {
    this.password = password;
    return this;
  }

  public TradesmanBuilder withSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
    this.subscriptionStatus = subscriptionStatus;
    return this;
  }

  public TradesmanBuilder withPaymentMethod(String paymentMethod, String paymentInfo) {
    this.paymentMethod = PaymentMethod.of(paymentMethod, paymentInfo);
    return this;
  }

  public TradesmanBuilder withPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public TradesmanBuilder withProfession(String professionName) {
    this.profession = Profession.of(NotEmptyString.of(professionName, stringValidators));
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

  public TradesmanBuilder withActivityRadius(double activityRadius) {
    this.activityRadius = ActivityRadius.of(activityRadius);
    return this;
  }

  public TradesmanBuilder withActivityRadius(ActivityRadius activityRadius) {
    this.activityRadius = activityRadius;
    return this;
  }

  public TradesmanBuilder withDailyRate(double dailyRate) {
    this.dailyRate = DailyRate.of(Amount.of(dailyRate));
    return this;
  }

  public TradesmanBuilder withDailyRate(DailyRate dailyRate) {
    this.dailyRate = dailyRate;
    return this;
  }

  public TradesmanBuilder withSkills(List<Skill> skills) {
    this.skills.addAll(skills);
    return this;
  }

  public TradesmanBuilder withSkillsRequest(List<SkillRequest> skills) {
    List<Skill> newSkills = Objects.requireNonNull(skills).stream().map(skill -> Skill.of(
        NotEmptyString.of(skill.skillName(), stringValidators),
        skill.requiredLevel())).toList();
    this.skills = newSkills;
    return this;
  }

  public TradesmanBuilder addSkill(Skill skill) {
    this.skills.add(skill);
    return this;
  }

  public TradesmanBuilder withUnavailability(List<Period> unavailability) {
    this.unavailability.addAll(unavailability);
    return this;
  }

  public TradesmanBuilder addUnavailability(Period unavailability) {
    this.unavailability.add(unavailability);
    return this;
  }

  public TradesmanBuilder withLocationName(String locationName) {
    this.locationName = NotEmptyString.of(locationName, stringValidators);
    return this;
  }

  public TradesmanBuilder withLocation(Location location) {
    this.locationName = location.locationName();
    this.latitude = location.coordinate().latitude();
    this.longitude = location.coordinate().longitude();
    return this;
  }

  public TradesmanBuilder withTradesmanProfessionalAbilities(TradesmanProfessionalAbilities abilities) {
    return this.withSkills(abilities.skills())
        .withActivityRadius(abilities.activityRadius())
        .withDailyRate(abilities.dailyRate())
        .withProfession(abilities.profession());
  }

  public TradesmanBuilder withTradesman(Tradesman tradesman) {
    return this.withLastname(tradesman.lastname())
        .withFirstname(tradesman.firstname())
        .withEmail(tradesman.email())
        .withPassword(tradesman.password())
        .withSubscriptionStatus(tradesman.subscriptionStatus())
        .withPaymentMethod(tradesman.paymentMethod())
        .withLocation(tradesman.address())
        .withTradesmanProfessionalAbilities(tradesman.professionalAbilities());
  }

  public TradesmanBuilder withUnavailabilityPeriods(List<PeriodRequest> periodsRequest) {
    this.unavailability = periodsRequest.stream()
        .map(req -> Period.of(req.startDate(), req.endDate(), dateValidators))
        .toList();
    return this;
  }

  public Tradesman build(EntityId entityId) {
    Location location = Location.of(Coordinate.of(longitude, latitude), locationName);
    TradesmanProfessionalAbilities professionalAbilities = TradesmanProfessionalAbilities.of(profession,
        skills, activityRadius, dailyRate, unavailability);
    return Tradesman.of(entityId, lastname, firstname, email, password, location, subscriptionStatus, paymentMethod,
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
    activityRadius = null;
    dailyRate = DailyRate.of(Amount.of(0));
    skills = new ArrayList<>();
    locationName = null;
    unavailability = new ArrayList<>();
  }

}
