package models;

public record ContactData(String id, String firstName, String middleName, String lastName, String nickName) {
    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstName, this.middleName, this.lastName, this.nickName);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.nickName);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.nickName);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.nickName);
    }

    public ContactData withNickName(String nickName) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, nickName);
    }
}
