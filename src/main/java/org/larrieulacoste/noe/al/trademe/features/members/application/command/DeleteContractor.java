package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class DeleteContractor implements Command {
    public final String contractorId;

    public DeleteContractor(String contractorId) {
        this.contractorId = contractorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteContractor that = (DeleteContractor) o;

        return contractorId.equals(that.contractorId);
    }

    @Override
    public int hashCode() {
        return contractorId.hashCode();
    }

    @Override
    public String toString() {
        return "DeleteContractor{" +
                "contractorId='" + contractorId + '\'' +
                '}';
    }
}
