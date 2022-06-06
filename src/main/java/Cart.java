public abstract class Cart {

    public abstract String getShoppingContent();

    public abstract void getNonExistingContent();

    public abstract void printShoppingContent();

    public abstract String getShoppingContentByYear(Integer year);

    public abstract String getShoppingContentWithNewValue(Integer value);

    public abstract String getShoppingContent(Integer year, String month);
}
