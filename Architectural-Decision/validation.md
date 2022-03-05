# Utilisation de validateurs fournis par Quarkus

- Status: proposed
- Date: 03/03/2022


## Context and Problem Statement

Utilisation d'annotations disponible avec quarkus permettant de valider une instance selon ses propriétés

## Considered Options

- Utilisation des annotations
- Garder le système actuel → validation lors de l'instanciation

## Decision Outcome

Chosen option: "option 1", Car on a découvert trop tard l'autre option

### Positive Consequences

- Validation plus précise des valeurs
- Retourne des erreurs personnalisées selon les champs
- Gain de temps, car on n'a pas à se former sur une nouvelle librairie tardivement 

### Negative Consequences

- Système de validation plus complexe
- Nécessite l'instanciation et l'injection de validateurs
