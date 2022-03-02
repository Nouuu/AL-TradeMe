# Sujet d'ADR potentiel

- Nommage des repositories
- séparation des commandes et des request
- Ne pas avoir utiliser les anotations de verifications de quarkus
  - Juste dire on s'en apperçu trop tard et il y a des soucis avec la nullité
- command et query bus par feature vs globale
- communication extenrn
  - Au lieu d'avoir une dépendance entre les usecases on passe par une récuprération de donnée chez le client qui les renvoies au deuxième usecase
