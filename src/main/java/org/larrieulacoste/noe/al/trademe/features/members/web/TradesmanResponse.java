package org.larrieulacoste.noe.al.trademe.features.members.web;

final class TradesmanResponse {
    final String userId;
    final String firstname;
    final String lastname;
    final String email;

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
