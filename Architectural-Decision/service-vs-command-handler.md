# Service vs command / query handler

- Status: proposed
- Date: 2022-03-02

Technical Story: rename the service by command / query handler

## Namming of service

A l'origine a la sortie du CC2 les services était nommé service. Par la suite on a remis en question ce terme pour essayer de coller le plus possible au CQS.

On trouve aussi que le mot service est trop largement utiliser. (micro-service, service-spring ...)

Les commandes et les query sont déjà sépraré dans des packages différent au seins des packages applications

## Considered Options

- Lasser service
- Changer pour command Handler / query Handler
- Chnager par handler

## Decision Outcome

Pour l'insant on ne change pas le nom, ce n'est pas très embêtatnt de le faire, le nom actuel ne pose pas de problème même si nous envisagons la 3ème options.

### Positive Consequences

- {e.g., improvement of quality attribute satisfaction, follow-up decisions required, …}

### Negative Consequences

- Aucune action requise
