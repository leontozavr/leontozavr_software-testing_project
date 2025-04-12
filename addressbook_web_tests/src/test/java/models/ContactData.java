package models;

public record ContactData(String firstName, String middleName, String lastName, String nickName) {
    public ContactData() {this("", "", "", "");}

    public ContactData withFirstName(String firstName) {return new ContactData(firstName, this.middleName, this.lastName, this.nickName);}

    public ContactData withMiddleName(String middleName) {return new ContactData(this.firstName, middleName, this.lastName, this.nickName);}

    public ContactData withLastName(String lastName) {return new ContactData(this.firstName, this.middleName, lastName, this.nickName);}

    public ContactData withNickName(String nickName) {return new ContactData(this.firstName, this.middleName, this.lastName, nickName);}
}
