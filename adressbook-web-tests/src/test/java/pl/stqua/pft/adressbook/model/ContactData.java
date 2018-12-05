package pl.stqua.pft.adressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String firstname;
  private final String lastname;
  private final String adress;
  private final String home;
  private final String email;
  private String group;

  public ContactData(String id, String firstname, String lastname, String adress, String home, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.adress = adress;
    this.home = home;
    this.email = email;
    this.group = group;
  }


  public ContactData(String firstname, String lastname, String adress, String home, String email, String group) {
    this.id = null;
    this.firstname = firstname;
    this.lastname = lastname;
    this.adress = adress;
    this.home = home;
    this.email = email;
    this.group = group;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
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

  public String getHome() {
    return home;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
