package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqua.pft.adressbook.model.GroupData;
import pl.stqua.pft.adressbook.model.Groups;

import java.util.List;

public class GroupHelpers extends Helperbase {

  public GroupHelpers(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData, boolean creation) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
    attach(By.name("photo"), groupData.getPhoto());

    if (creation) {
      if (groupData.getContacts().size() > 0) {
        Assert.assertTrue(groupData.getContacts().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupData.getContacts().iterator().next().getFirstname());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void initAddingContactToGroup() {
    click(By.name("add"));
  }

  public void initDeleteContactFromGroup() {
    click(By.name("remove"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group, true);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group, true);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnToGroupPage();
  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public void addContact(GroupData addedContactToGroup) {
    selectGroupById(addedContactToGroup.getId());
    initAddingContactToGroup();
    groupCache = null;
  }

  public void deleteContact(GroupData deletedContactFromGroup) {
    selectGroupById(deletedContactFromGroup.getId());
    initDeleteContactFromGroup();
    groupCache = null;
  }

}
