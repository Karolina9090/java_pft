package pl.stqua.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String adress;
  @Expose
  private String homePhone;
  @Expose
  private String email;
  @Expose
  private String group;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  @Expose
  private String allPhones;
  @Expose
  private String allHomeAdress;
  @Expose
  private String allEmailAdress;
  @Expose
  private String homeAdress;
  @Expose
  private String emailAdress;
  @Expose
  private String emailAdress2;
  @Expose
  private String emailAdress3;
  @Expose
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withAllEmailAdress(String allEmailAdress) {
    this.allEmailAdress = allEmailAdress;
    return this;
  }

  public String getAllEmailAdress() {
    return allEmailAdress;
  }

  public ContactData withAllHomeAdress(String allHomeAdress) {
    this.allHomeAdress = allHomeAdress;
    return this;
  }

  public String getAllHomeAdress() {
    return allHomeAdress;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAdress(String adress) {
    this.adress = adress;
    return this;
  }

  public ContactData withHome(String home) {
    this.homePhone = home;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.workPhone = work;
    return this;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAdress() {
    return adress;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getHomeAdress() {
    return homeAdress;
  }

  public ContactData withEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
    return this;
  }

  public ContactData withEmailAdress2(String emailAdress2) {
    this.emailAdress2 = emailAdress2;
    return this;
  }

  public ContactData withEmailAdress3(String emailAdress3) {
    this.emailAdress3 = emailAdress3;
    return this;
  }

  public String getEmailAdress() {
    return emailAdress;
  }

  public String getEmailAdress2() {
    return emailAdress2;
  }

  public String getEmailAdress3() {
    return emailAdress3;
  }


  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

}
