public interface Cart {

    String getShoppingContent();

    void getNonExistingContent();

    void printShoppingContent();

    String getShoppingContentByYear(Integer year);

    String getShoppingContentWithNewValue(Integer value);

    String getShoppingContent(Integer year, String month);
}
