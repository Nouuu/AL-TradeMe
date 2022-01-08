package org.larrieulacoste.noe.al.trademe.features.members.web;

final class ContractorResponse {
    public final String userId;
    public final String firstname;
    public final String lastname;
    public final String email;

    ContractorResponse(String userId, String firstname, String lastname, String email) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
