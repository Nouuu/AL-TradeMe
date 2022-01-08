package org.larrieulacoste.noe.al.trademe.features.members.web;

final class TradesmanResponse {
    public final String userId;
    public final String firstname;
    public final String lastname;
    public final String email;

    TradesmanResponse(String userId, String firstname, String lastname, String email) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tradesman{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
