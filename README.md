| [![github](https://img.shields.io/badge/repository-github-blue)](https://github.com/Nouuu/AL-CC1) | [![wakatime](https://wakatime.com/badge/user/3106fbc8-c1fe-4d47-b9ce-b0514ce3fb3d/project/19997bda-72d3-4311-b5b1-f9552652206a.svg)](https://wakatime.com/@Nou/projects/wfgcsnffzh?start=2021-11-14&end=2021-11-20) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

# Architecture choisie



## Domain-Driven Design

Cette application a été conçu en utilisant une approche DDD (Domain-Driven Design). Cela fit référence à une conception piloté par le métier. De cette manière, l'application est séparée en 2 package principaux :

- **Domain :** Contient l'ensemble des informations relatives au métier.
- **Infrastructure :** Contient les implémentations des différentes interfaces, se comporte comme une bibliothèque de support au domaine

## Implémentation

### API

Ce package est utilisé pour pouvoir avoir recours à des services externes en utilisant le **pattern strategy**.

L'interface est présente dans le package **domaine** et son implémentation dans **l'infrastructure**.

Pour le moment, une seule API est présente, celle du **paiement** qui renvoie un Booléen pour indiquer si la transaction s'est bien éffectué. elle est actuellement implémentée par un **stub** qui revoie toujours **vrai**

### Entités

Ce package contient toute les entités utilisées dans l'application, elles suivent le **pattern value object** ainsi qu'**entity**. L'objet est donc immutable et possède un identifiant pour son utilisation à travers un repository.

La seule classe présente est celle de l'utilisateur **User** ainsi que celle de son identifiant unique **UserId**.

### Événements

Afin de pouvoir prendre en compte **différents traitements** lors de l'enregistrement d'un utilisateur, sans avoir à modifier le service et que ce dernier n'ai qu'**une seule responsabilité**, ce dernier utilisera le **pattern event, observable** afin de lancer un événement lors de l'enregistrement de l'utilisateur. 

Les différentes tâches à éxécuter suite à cet enregistrement n'auront alors qu'à **s'inscrire à cet événement** pour lancer leur propre traitement.

### Exception

Contient pour le moment des exceptions d'éxecution du domaine métiers tel que **PaymentException** si le paiement a échoué, **UserInvalidException** si l'utilisateur n'est pas valide et **UserNotFoundException** si l'utilisateur n'est pas présent dans le repository implémenté.

### Logger

En utilisant le **pattern strategy** ainsi que **factory**, ce package permet à une classe d'obtenir un logger qui lui est propre grace au **LoggerFactory**. Les interfaces font parties du **domaine** et leurs implémentation de **l'infrastructure**. Actuellement l'implémentation présente réutilise le la classe **Logger** **de Java**.

### Repositories

Ce package permet d'avoir une **persistance des données** entités de l'application. 

Pour cela on utilise le **pattern repository et strategy** afin de **séparer son interface**, qui restera dans le **domaine**, de son implémentation dans **l'infrastructure**. 

Actuellement l'implémentation stock les entités en mémoire et se vide quand l'application s'arrête. 

### Services

Les services contiennent le **traitement dit métier** de notre application. Ce sont eux qui vont utiliser les différentes resourcess de notre application pour **éxécuter les traitements de leurs propres domaines**. 

Notre application contient pour le moment **3 services** :

- **PaymentService** : Pour les traitements relatifs aux paiements.
- **UserApplicationService** : Pour les traitements relatifs aux enregistrements des utilisateurs
- **UserValidationService** : Pour les traitments servant à valider un utilisateur.

Les services se basent principalement sur les **interfaces** de nos autres classes afin de ne pas être dépendants d'une implémentation en particulier. On peut faire ça grâce au **polymorphisme**, la **programmation par interfaces** et le **pattern dependency injection**.
