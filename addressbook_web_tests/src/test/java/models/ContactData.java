package models;

public record ContactData(String id, String firstName, String lastName) {
    public ContactData() {
        this("", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName);
    }

}
