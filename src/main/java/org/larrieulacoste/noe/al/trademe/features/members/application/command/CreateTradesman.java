package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class CreateTradesman implements Command {
    public final String firstname;
    public final String lastname;
    public final String email;
    public final String password;
    public final String paymentMethodType;
    public final String paymentMethodRessource;

    public CreateTradesman(String firstname, String lastname, String email, String password, String paymentMethodType, String paymentMethodRessource) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.paymentMethodType = paymentMethodType;
        this.paymentMethodRessource = paymentMethodRessource;
    }

    @Override
    public String toString() {
        return "CreateTradesman{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", paymentMethodType='" + paymentMethodType + '\'' +
                ", paymentMethodRessource='" + paymentMethodRessource + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateTradesman that = (CreateTradesman) o;

        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        if (!paymentMethodType.equals(that.paymentMethodType)) return false;
        return paymentMethodRessource.equals(that.paymentMethodRessource);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + paymentMethodType.hashCode();
        result = 31 * result + paymentMethodRessource.hashCode();
        return result;
    }
}
