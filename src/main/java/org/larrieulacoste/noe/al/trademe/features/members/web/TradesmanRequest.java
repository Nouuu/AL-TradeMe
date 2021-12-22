package org.larrieulacoste.noe.al.trademe.features.members.web;

final class TradesmanRequest {
    public final String userId;
    public final String firstname;
    public final String lastname;
    public final String email;
    public final String password;

    public TradesmanRequest(String userId, String firstname, String lastname, String email, String password) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
}
