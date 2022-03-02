# Sujet d'ADR potentiel

- Communcation interne
  - Par event
  - 0 partage entre les features
- Service vs Command / query Handler
- Nommage des repositories
- séparation des commandes et des request
- Ne pas avoir utiliser les anotations de verifications de quarkus
  - Juste dire on s'en apperçu trop tard et il y a des soucis avec la nullité
- Utilisation des records
- command et query bus par feature vs globale
- communication extenrn
  - Au lieu d'avoir une dépendance entre les usecases on passe par une récuprération de donnée chez le client qui les renvoies au deuxième usecase
