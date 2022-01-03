package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class DeleteTradesman implements Command {
    public final String tradesmanId;

    public DeleteTradesman(String tradesmanId) {
        this.tradesmanId = tradesmanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteTradesman that = (DeleteTradesman) o;

        return tradesmanId.equals(that.tradesmanId);
    }

    @Override
    public int hashCode() {
        return tradesmanId.hashCode();
    }

    @Override
    public String toString() {
        return "DeleteTradesman{" +
                "tradesmanId='" + tradesmanId + '\'' +
                '}';
    }
}
