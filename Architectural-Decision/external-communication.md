# Transfer data between use cases

- Status: accepted
- Date: 2022-03-03

Technical Story: [Ticket](https://github.com/Nouuu/AL-TradeMe/issues/44)

## Context and Problem Statement

Afin d'effectuer un filtrage sur une liste des informations qui devaient être récupéré depuis un autre use case

## Considered Options

- Communication via requête HTTP
- Effectuer le transfert de donnée via le client


## Decision Outcome

Chosen option: "option 2", Afin de rester cohérent avec la décision prise pour la communication interne

### Positive Consequences

- Pas d'impact sur la définition des données transférer
- Aucun couplage entre les use cases
- Donne au client la possibilité de redéfinir des informations si besoin

### Negative Consequences

- Alourdis les transferts réseaux du client
- Requête plus conséquente
