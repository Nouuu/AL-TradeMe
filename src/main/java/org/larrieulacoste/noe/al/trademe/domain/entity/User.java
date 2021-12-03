package org.larrieulacoste.noe.al.trademe.domain.entity;

import java.util.Objects;

public final class User {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private String email;
    private String password;
    private String bankAccount;

    private User(UserId userId, String lastname, String firstname, String email, String password, String bankAccount) {
        this.userId = Objects.requireNonNull(userId);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.bankAccount = Objects.requireNonNull(bankAccount);
    }

    public static User of(UserId userId, String lastname, String firstname, String email, String password, String bankAccount) {
        return new User(userId, lastname, firstname, email, password, bankAccount);
    }

    void changeEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail);
    }

    void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    void changeBankAccount(String newBankAccount) {
        this.bankAccount = Objects.requireNonNull(newBankAccount);
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }
}
