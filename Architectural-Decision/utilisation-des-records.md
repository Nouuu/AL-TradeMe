# Utilisation des records

- Status: accepted
- Date: 2022-03-02

Technical Story: [card associé](https://github.com/Nouuu/AL-TradeMe/issues/40)

## Il y a une nouvelle syntaxe dans java

```java

public final class ContractorDeleted implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private ContractorDeleted(EventId eventId, ZonedDateTime occurredDate) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
    }

    public static ContractorDeleted withContractor() {
        return new ContractorDeleted(EventId.create(), ZonedDateTime.now());
    }
    /**
     * GETTER
     */

    @Override
    public String toString() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractorDeleted)) return false;

        ContractorDeleted that = (ContractorDeleted) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return contractor.equals(that.contractor);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractor.hashCode();
        return result;
    }
}
```

---

```java
public record ContractorDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId contractorId
) implements ApplicationEvent {

    public ContractorDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorId);
    }

    public static ContractorDeleted of(EntityId contractorId) {
        return new ContractorDeleted(EventId.create(), ZonedDateTime.now(), contractorId);
    }
}

```

Il existe une nouvelle syntaxe plus courte pour définir des data class.

## Considered Options

- Rester sur des class final avec static factory method
- Passer les objets de transfert en record
- Passer les objets de transfert et les value objects et les entities en record

## Decision Outcome

Option 3

### Positive Consequences

- Shorter
- immutable
- gestion automatique de "equals", hashcode et toString
- Plus de génération via l'IDE

### Negative Consequences

- Le constructeur par défaut ne peut pas avoir un scope plus petit que le record lui-même

## Guideline

On a décidé en interne dans l'équipe dans nos conventions de code de faire usage des constructeurs static plutôt que du constructeur par défaut.
