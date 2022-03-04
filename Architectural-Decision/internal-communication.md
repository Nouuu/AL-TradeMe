# Commnucation interne

- Status: accepted
- Date: 2022-03-02

## UseCases couplage

On veut que nos use cases communique sans qu'ils tissent un lien trop important

## Considered Options

- Coupler les dépendances
- Envoyer des appels HTTP en interne
- Passer des événements dans un bus pour prévenir les autres use cases

## Decision Outcome

Chosen option: "option 3", Parce qu'elle réduit le couplage et semble plus facilement transposable en microservice.

### Positive Consequences

- Elle n'influe pas sur nos gestions des données
- Couplage inexistant entre les use cases
- Les use cases sont indépendants et modulaire

### Negative Consequences

- Performance (parfois on reconstruit une grappe objet)
- Beaucoup de responsabilité sur le bus d'événement
- Le package contenant les événements est assez conséquent

## Pros and Cons of the Options

### Option 1

On couple et on admet une forte dépendance des objets entre eux.

Au vu de l'envergure du projet cette solution semble mauvaise.

### Option 2

Utilisation d'appel HTTP.

- Good, aucun couplage
- Good, scalable
- Bad, nécessite une redéfinition des données
- Bad, nécessite un maillage plus complexe
