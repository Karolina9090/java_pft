package pl.stqua.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Column(name = "adress")
  private String adress;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column(name = "email")
  private String email;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Transient
  transient private String allPhones;
  @Expose
  @Transient
  transient private String allHomeAdress;
  @Expose
  @Transient
  transient private String allEmailAdress;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String homeAdress;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String emailAdress;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String emailAdress2;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String emailAdress3;
  @Expose
  @Column
  @Type(type = "text")
  private String photo;

  @Type(type = "text")
  public File getPhoto() {
    return new File(photo);
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups,",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private static Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
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
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(adress, that.adress) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(allPhones, that.allPhones) &&
            Objects.equals(allHomeAdress, that.allHomeAdress) &&
            Objects.equals(allEmailAdress, that.allEmailAdress) &&
            Objects.equals(homeAdress, that.homeAdress) &&
            Objects.equals(emailAdress, that.emailAdress) &&
            Objects.equals(emailAdress2, that.emailAdress2) &&
            Objects.equals(emailAdress3, that.emailAdress3) &&
            Objects.equals(photo, that.photo);
  }

  public static Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
