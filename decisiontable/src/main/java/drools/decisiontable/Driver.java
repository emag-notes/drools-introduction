package drools.decisiontable;

public class Driver {

  private String name;
  private int age;
  private int priorClaims;
  private String locationRiskProfile;

  public Driver(String name, int age, int priorClaims, String locationRiskProfile) {
    this.name = name;
    this.age = age;
    this.priorClaims = priorClaims;
    this.locationRiskProfile = locationRiskProfile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getLocationRiskProfile() {
    return locationRiskProfile;
  }

  public void setLocationRiskProfile(String locationRiskProfile) {
    this.locationRiskProfile = locationRiskProfile;
  }

  public int getPriorClaims() {
    return priorClaims;
  }

  public void setPriorClaims(int priorClaims) {
    this.priorClaims = priorClaims;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Driver driver = (Driver) o;

    if (age != driver.age) return false;
    if (priorClaims != driver.priorClaims) return false;
    if (locationRiskProfile != null ? !locationRiskProfile.equals(driver.locationRiskProfile) : driver.locationRiskProfile != null)
      return false;
    if (name != null ? !name.equals(driver.name) : driver.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + age;
    result = 31 * result + priorClaims;
    result = 31 * result + (locationRiskProfile != null ? locationRiskProfile.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Driver [name=" + name + ", age=" + age + ", priorClaims="
        + priorClaims + ", locationRiskProfile=" + locationRiskProfile + "]";
  }
}
