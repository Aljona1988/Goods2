import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {

    Product book1 = new Book(1, "Война и мир", 100, "Лев Толстой");
    Product smartphone1 = new Smartphone(2, "Iphone 11", 10_000, "Apple");
    Product book2 = new Book(3, "Мастер и Маргарита", 200, "Михаил Булгаков");
    Product smartphone2 = new Smartphone(4, "Samsung A41", 8_000, "Samsung");

    @Test
    public void shouldSaveNewProduct() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        repo.deleteById(book1.getId());

        Product[] expected = {smartphone1, book2, smartphone2};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void shouldSetId() {
        Product product = new Product();
        product.setId(2);

        int expected = 2;
        int actual = product.getId();

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void shouldSetName() {
        Product product = new Product();
        product.setName("Принц и нищий");

        String expected = "Принц и нищий";
        String actual = product.getName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSetPrice() {
        Product product = new Product();
        product.setPrice(300);

        int expected = 300;
        int actual = product.getPrice();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSetAuthor() {
        Book book = new Book();
        book.setAuthor("Экзюпери");

        String expected = "Экзюпери";
        String actual = book.getAuthor();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldSetProducer() {
        Smartphone smartphone = new Smartphone();
        smartphone.setProducer("Samsung");

        String expected = "Samsung";
        String actual = smartphone.getProducer();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldAddNewProduct() {
        ProductRepository myRepo = new ProductRepository();
        ProductManager productManager = new ProductManager(myRepo);
        Product product = new Product();
        int countBefore = myRepo.getProducts().length;
        productManager.add(product);
        int countAfter = myRepo.getProducts().length;

        int expected = 1;
        int actual = countAfter - countBefore;

        Assertions.assertEquals(expected, actual);


    }

    @Test
    void shouldSearchBy() {
        ProductRepository myRepo = new ProductRepository();
        ProductManager productManager = new ProductManager(myRepo);
        Product product1 = new Product("Яблоко");
        Product product2 = new Product("Вуча");
        productManager.add(product1);
        productManager.add(product2);
        int expected = 1;
        int actual = productManager.searchBy("Яблоко").length;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSNotSearchBy() {
        ProductRepository myRepo = new ProductRepository();
        ProductManager productManager = new ProductManager(myRepo);
        Product product1 = new Product("Яблоко");
        Product product2 = new Product("Вуча");
        productManager.add(product1);
        productManager.add(product2);
        int expected = 0;
        int actual = productManager.searchBy("Груша").length;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSearchBy2() {
        ProductRepository myRepo = new ProductRepository();
        ProductManager productManager = new ProductManager(myRepo);
        Product product1 = new Product("Яблоко");
        Product product2 = new Product("Яблоко");
        Product product3 = new Product("Вуча");
        productManager.add(product1);
        productManager.add(product2);
        productManager.add(product3);
        int expected = 2;
        int actual = productManager.searchBy("Яблоко").length;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSearchByEmpty() {
        ProductRepository myRepo = new ProductRepository();
        ProductManager productManager = new ProductManager(myRepo);

        int expected = 0;
        int actual = productManager.searchBy("").length;

        Assertions.assertEquals(expected, actual);
    }


}


