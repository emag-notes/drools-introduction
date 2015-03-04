package drools.decisiontable;

public class Policy {

  private String type;
  private boolean approved;
  private int discountPercent;
  private int basePrice;

  public Policy(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public int getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(int discountPercent) {
    this.discountPercent = discountPercent;
  }

  public void applyDiscount(int discount) {
    discountPercent += discount;
  }

  public int getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(int basePrice) {
    this.basePrice = basePrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Policy policy = (Policy) o;

    if (approved != policy.approved) return false;
    if (basePrice != policy.basePrice) return false;
    if (discountPercent != policy.discountPercent) return false;
    if (type != null ? !type.equals(policy.type) : policy.type != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (approved ? 1 : 0);
    result = 31 * result + discountPercent;
    result = 31 * result + basePrice;
    return result;
  }

  @Override
  public String toString() {
    return "Policy [type=" + type + ", approved=" + approved
        + ", discountPercent=" + discountPercent + ", basePrice=" + basePrice
        + "]";
  }

}
