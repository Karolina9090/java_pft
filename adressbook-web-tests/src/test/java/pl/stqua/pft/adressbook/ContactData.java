package pl.stqua.pft.adressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String adress;
  private final String home;
  private final String email;
  private final String new_group;

  public ContactData(String firstname, String lastname, String adress, String home, String email, String new_group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.adress = adress;
    this.home = home;
    this.email = email;
    this.new_group = new_group;
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

  public String getNew_group() {
    return new_group;
  }
}
