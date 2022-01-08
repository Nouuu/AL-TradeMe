| [![github](https://img.shields.io/badge/repository-github-blue)](https://github.com/Nouuu/AL-TradeMe) | [![wakatime](https://wakatime.com/badge/user/3106fbc8-c1fe-4d47-b9ce-b0514ce3fb3d/project/945bfe6c-4742-4cbe-896d-b9b5692afeca.svg)](https://wakatime.com/@Nou/projects/aqygjxcins) |     |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- |

| Dev status | [![Coverage](https://sonar.nospy.fr/api/project_badges/measure?project=Nouuu_AL-TradeMe&metric=coverage&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe) | [![Maintainability Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=sqale_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Quality Gate Status](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=alert_status&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Reliability Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=reliability_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Security Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=security_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |

| Main status | [![Coverage](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=coverage&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Maintainability Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=sqale_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Quality Gate Status](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=alert_status&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Reliability Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=reliability_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) | [![Security Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=feature%2F15-create-all-entities-and-models&project=Nouuu_AL-TradeMe&metric=security_rating&token=edc93fd166b059d5befe7e2fe22d2e0d10d9b853)](https://sonar.nospy.fr/dashboard?id=Nouuu_AL-TradeMe&branch=feature%2F15-create-all-entities-and-models) |
| ----------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |

# Architecture choisie

## Hexagonal Architecture

## Domain-Driven Design

Cette application a été conçu en utilisant une approche DDD (Domain-Driven Design). Cela fit référence à une conception piloté par le métier. De cette manière, l'application est séparée en 2 package principaux :

- **Domain :** Contient l'ensemble des informations relatives au métier.
- **Infrastructure :** Contient les implémentations des différentes interfaces, se comporte comme une bibliothèque de support au domaine

## ## Staged event-driven architecture

# Implémentation

## Dependency Inversion Principle

## Command Query Separation

## Packages

### API

Ce package est utilisé pour pouvoir avoir recours à des services externes en utilisant le **pattern strategy**.

L'interface est présente dans le package **domaine** et son implémentation dans **l'infrastructure**.

Pour le moment, une seule API est présente, celle du **paiement** qui renvoie un Booléen pour indiquer si la transaction s'est bien effectué. elle est actuellement implémentée par un **stub** qui revoie toujours **vrai**

### Entités

Ce package contient toute les entités utilisées dans l'application, elles suivent le **pattern value object** ainsi qu'**entity**. L'objet est donc immutable et possède un identifiant pour son utilisation à travers un repository.

La seule classe présente est celle de l'utilisateur **User** ainsi que celle de son identifiant unique **UserId**.

### Kernel

#### Logger

En utilisant le **pattern strategy** ainsi que **factory**, ce package permet à une classe d'obtenir un logger qui lui est propre grâce au **LoggerFactory**. Les interfaces font parties du **domaine** et leurs implémentation de **l'infrastructure**. Actuellement l'implémentation présente réutilise le la classe **Logger** **de Java**.

#### Repositories

Ce package permet d'avoir une **persistance des données** entités de l'application. 

Pour cela on utilise le **pattern repository et strategy** afin de **séparer son interface**, qui restera dans le **domaine**, de son implémentation dans **l'infrastructure**. 

Actuellement l'implémentation stock les entités en mémoire et se vide quand l'application s'arrête. 

### Application

Les services contiennent le **traitement dit métier** de notre application. Ce sont eux qui vont utiliser les différentes ressources de notre application pour **exécuter les traitements de leurs propres domaines**. 

Notre application contient pour le moment **3 services** :

- **PaymentService** : Pour les traitements relatifs aux paiements.
- **UserApplicationService** : Pour les traitements relatifs aux enregistrements des utilisateurs
- **UserValidationService** : Pour les traitements servant à valider un utilisateur.

Les services se basent principalement sur les **interfaces** de nos autres classes afin de ne pas être dépendants d'une implémentation en particulier. On peut faire ça grâce au **polymorphisme**, la **programmation par interfaces** et le **pattern dependency injection**.

#### Exception

Contient pour le moment des exceptions d'exécution du domaine métiers tel que **PaymentException** si le paiement a échoué, **UserInvalidException** si l'utilisateur n'est pas valide et **UserNotFoundException** si l'utilisateur n'est pas présent dans le repository implémenté.

#### Événements

Afin de pouvoir prendre en compte **différents traitements** lors de l'enregistrement d'un utilisateur, sans avoir à modifier le service et que ce dernier n'ai qu'**une seule responsabilité**, ce dernier utilisera le **pattern event, observable** afin de lancer un événement lors de l'enregistrement de l'utilisateur.

Les différentes tâches à exécuter suite à cet enregistrement n'auront alors qu'à **s'inscrire à cet événement** pour lancer leur propre traitement.

# Exception Handler

# Schémas & Diagrammes

## Command / Query Bus

![DefaultCommandBus_send.png](D:\Projets\AL-TradeMe\assets\b03d3454f6c7f3dbeb0336faaf94ee45eb074b77.png)

<img title="" src="file:///D:/Projets/AL-TradeMe/assets/226848c6adb9c7b64e310525ca2fe3cc8b8c5654.png" alt="DefaultQueryBus_send.png" width="386" data-align="inline">

## Event Bus

![DefaultEventBus_publish.png](D:\Projets\AL-TradeMe\assets\a9f6a79984efe1a9d27836c83b31105338d0cd3e.png)

## Dependency Injection

# Quarkus

![ContractorController_getById.png](D:\Projets\AL-TradeMe\assets\e23c3babbd0586bfdf24e174c4f82d1e0029fef1.png)

![ContractorController_register.png](D:\Projets\AL-TradeMe\assets\7cf3bd38e9da2f6b5c0c5d1c72b917839a3501ac.png)

![CreateContractorService_handle.png](D:\Projets\AL-TradeMe\assets\34fcc6dc2f0d90667dd400094cd2bbe96e08e807.png)

![RetrieveContractorByIdService_handle.png](D:\Projets\AL-TradeMe\assets\2482b9a4365d47fb4ffdfbe2a6c0c3671ac18c40.png)
