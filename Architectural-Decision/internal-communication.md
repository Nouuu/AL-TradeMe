# Commnucation interne

- Status: accepted
- Date: 2022-03-02

Technical Story: {description | ticket/issue URL} <!-- optional -->

## UseCases couplage

On vent que nos usecases communique sans qu'il tissent un lien trop important

## Considered Options

- couplerer les dépendances
- Envoyer des appels HTTP en interne
- Passer des evenements dans un bus pour prévenir les autres usecases

## Decision Outcome

Chosen option: "option 3", because elle réduit le couplage et semble plus facilement transposable en microservice.

### Positive Consequences

- Elle n'influ pas sur notre gestions des données
- Couplage innexistant entre les usecases
- Les usecase sont indépendants et modulaire

### Negative Consequences

- Performance (parfois on reconstruit une grappe objet)
- Beaucoup de résponsabilité sur le bus d'evenement
- Le package contenant les evenements est assez conséquent

## Pros and Cons of the Options

### option 1

On couple et on admet une forte dépendance des objets entre eux.

Au vu de l'envergure du projet cette solution semble mauvaise.

### option 2

Utilisation d'appel HTTP.

- Good, aucun couplage
- Good, scalable
- Bad, nécéssite une redéfinition des données
- Bad, nécéssite un maillage plus complexe
