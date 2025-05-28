package manager.hbm;


import common.CommonFunctions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address = CommonFunctions.randomString(10);;
    public String home = CommonFunctions.randomString(10);
    public String mobile = CommonFunctions.randomString(10);
    public String work = CommonFunctions.randomString(10);
    public String fax = CommonFunctions.randomString(10);
    @Column(name = "email")
    public String email1 = CommonFunctions.randomString(10);
    public String email2 = CommonFunctions.randomString(10);
    public String email3 = CommonFunctions.randomString(10);

    public ContactRecord(){
    }

    public ContactRecord(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}