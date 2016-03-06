package com.wtw.api;


import com.wtw.ProjektWtwApplicationTests;
import com.wtw.domain.Product;
import com.wtw.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductsApiTest extends ProjektWtwApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ProductsApiTest.class);

    private static final int NUMBER_OF_SAVED_PRODUCTS = 6;

    @Autowired
    ProductRepository realProductRepository;

    @Test
    public void should_get_empty_list_of_products() {
        givenProduct()
                .buildNumberOfProductsAndSave(0);
        logger.info("test" + givenProduct()
                .buildNumberOfProductsAndSave(0).toString());

        List<Product> products = thenGetProductsFromApi();
        System.out.println("testujemy" + products);
        logger.info(products.toString());

        assertThat(products).isEmpty();
    }

    @Test
    public void should_get_3_products() {
        givenProduct()
                .buildNumberOfProductsAndSave(3);

        List<Product> products = thenGetProductsFromApi();

        assertThat(products).hasSize(3);
    }

    @Test
    public void should_get_one_product() {
        logger.info("Wchodzi tu?");
        List<Product> givenProducts = givenProduct()
                .buildNumberOfProductsAndSave(1);
        logger.info(givenProducts.toString());

        Product product = thenGetOneProductFromApi();
        logger.info(product.toString());

        assertThat(givenProducts.get(0)).isEqualToComparingFieldByField(product);
    }

    @Test
    public void should_get_three_newest_products() {
        List<Product> givenProduct = givenProduct()
                .buildNumberOfProductsAndSave(6);

        List<Product> products = thenGetNumberOfNewestProductsFromApi(3);

        ProductListAssert.assertThat(products)
                .isSuccessful()
                .hasNumberOfItems(3)
                .newestOf(givenProduct);
    }

    @Test
    public void should_create_a_product() {
        //given

        //when
        thenCreateProductByApi();

        //then
        assertThat(realProductRepository.findProductByName("product_0")).isNotNull();
    }

    @Test
    public void should_update_existing_product() {
        //given
        givenProduct()
                .buildNumberOfProductsAndSave(1);

        //when
        thenUpdateProductByApi();

        //then
        assertThat(realProductRepository.findProductByName("product_0"))
                .isNotNull();
        // .hasFieldOrPropertyWithValue("price", BigDecimal.ONE.setScale(2));
    }

    @Test
    public void should_delete_existing_product() {
        //given
        givenProduct()
                .buildNumberOfProductsAndSave(1);
        //when
        thenDeleteOneProductFromApi();

        //then
        assertThat(realProductRepository.findProductById(1L)).isNull();
    }

    @Before
    public void setup() {
        realProductRepository.deleteAll();
    }

    private ProductListFactory givenProduct() {
        return new ProductListFactory(realProductRepository);
    }

    private List<Product> thenGetProductsFromApi() {
        logger.info(template.getForObject("http://localhost:8181/api/allProducts", Product.class).toString());
        logger.info(template.getForEntity("http://localhost:8181/api/allProducts", Product[].class).toString());
        return Lists.newArrayList(template.getForEntity("http://localhost:8181/api/products", Product.class).getBody());
    }

    private Product thenGetOneProductFromApi() {
        logger.info(template.getForObject("http://localhost:8080/api/products/product_0", Product.class).toString());
        logger.info(template.getForEntity("http://localhost:8080/api/products/product_0", Product.class).toString());
        return template.getForEntity("http://localhost:8080/api/products/product_0", Product.class).getBody();
    }

    private List<Product> thenGetNumberOfNewestProductsFromApi(int number) {
        return Lists.newArrayList(template.getForEntity(String.format("http://localhost:8080/api/products?order=desc&limit=%s", number), Product[].class).getBody());
    }

    private void thenCreateProductByApi() {
        template.put("http://localhost:8080/api/products", new ProductBuilder("product_0").build());
    }

    private void thenUpdateProductByApi() {
        template.put("http://localhost:8080/api/products/1", new ProductBuilder("product_0").withPrice(BigDecimal.ONE).build());
    }

    private void thenDeleteOneProductFromApi() {
        template.delete("http://localhost:8080/api/products/1");
    }
}
