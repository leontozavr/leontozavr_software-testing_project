package models;

public record ContactData(String id,
                          String firstName,
                          String lastName,
                          String address,
                          String home,
                          String mobile,
                          String work,
                          String fax,
                          String email1,
                          String email2,
                          String email3
                          ) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.home, this.mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.home, this.mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.home, this.mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.home, this.mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, home, this.mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, mobile, this.work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, this.mobile, work, this.fax, this.email1, this.email2, this.email3);
    }

    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, this.mobile, this.work, fax, this.email1, this.email2, this.email3);
    }

    public ContactData withEmail1(String email1) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, this.mobile, this.work, this.fax, email1, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, this.mobile, this.work, this.fax, this.email1, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.home, this.mobile, this.work, this.fax, this.email1, this.email2, email3);
    }

}
