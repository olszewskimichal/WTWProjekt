package com.wtw.config;

import com.wtw.domain.*;
import com.wtw.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Created by Admin on 2016-03-01.
 */
@Configuration
@Profile("!test")
public class DevDBConfig {
    private static final Logger logger = LoggerFactory.getLogger(DevDBConfig.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void populateDatabase() {
        logger.info("ładowanie bazy testowej");

        repository.save(new User("Adam", "Małysz", "aaa1@o2.pl", new BCryptPasswordEncoder().encode("aaa")));
        User user = new User("Admin", "admin", "aaa2@o2.pl", new BCryptPasswordEncoder().encode("1"));
        user.setRole(Role.ADMIN);
        repository.save(user);
        user = new User("SuperAdmin", "superadmin", "aaa3@o2.pl", new BCryptPasswordEncoder().encode("a"));
        repository.save(user);

        Category category = new Category("Smartfony");
        Category category1 = new Category("Komputery");
        Category category2 = new Category("Tablety");

        Product iphone = new Product("iPhone 5s", "Apple iPhone 5s, " +
                "smartfon z 4-calowym ekranem o rozdzielczości 640×1136 i 8-megapikselowym aparatem",
                new BigDecimal(500), 1000, "http://pdadb.net/img/gallery/big/apple_iphone_5_ios7_main.png");
        Product laptop_dell = new Product("Dell Inspiron",
                "Dell Inspiron, 14-calowy laptop (czarny) z procesorami Intel Core 3. generacji", new BigDecimal(700), 1000,
                "http://ecx.images-amazon.com/images/I/81LcrgMpIcL._SL1500_.jpg");

        Product tablet_Nexus = new Product("Nexus 7",
                "Google Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro",
                new BigDecimal(300), 1000,
                "http://images.morele.net/full/706266_2_f.png");
        Product komputerIEM = new Product("Infinity IEM 2015",
                "Limitowana seria komputerów Komputronik Infinity Intel Extreme Masters powstała z myślą o finałach Mistrzostw Intel Extreme Masters 2015 które odbyły się w Katowicach.",
                new BigDecimal(3900), 100,
                "http://www.power-cy.com/wp-content/uploads/2014/07/pc.png");
        Product iphone2 = new Product("iPhone 5sS", "Apple iPhone 5s, " +
                "smartfon z 4-calowym ekranem o rozdzielczości 640×1136 i 8-megapikselowym aparatem",
                new BigDecimal(500), 1000, "http://pdadb.net/img/gallery/big/apple_iphone_5_ios7_main.png");

        Product laptop_dell2 = new Product("Dell InspironS",
                "Dell Inspiron, 14-calowy laptop (czarny) z procesorami Intel Core 3. generacji", new BigDecimal(700), 1000,
                "http://ecx.images-amazon.com/images/I/81LcrgMpIcL._SL1500_.jpg");

        Product tablet_Nexus2 = new Product("Nexus 7S",
                "Google Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro",
                new BigDecimal(300), 1000,
                "http://images.morele.net/full/706266_2_f.png");
        Product komputerIEM2 = new Product("Infinity IEM 2015S",
                "Limitowana seria komputerów Komputronik Infinity Intel Extreme Masters powstała z myślą o finałach Mistrzostw Intel Extreme Masters 2015 które odbyły się w Katowicach.",
                new BigDecimal(3900), 100,
                "http://www.purepc.pl/files/Image/news/2015/03/iem_2015_pc/purepc_iem_2015_pc_4.jpg");
        Product iphone3 = new Product("iPhone 5s3", "Apple iPhone 5s, " +
                "smartfon z 4-calowym ekranem o rozdzielczości 640×1136 i 8-megapikselowym aparatem",
                new BigDecimal(500), 1000, "http://pdadb.net/img/gallery/big/apple_iphone_5_ios7_main.png");

        Product laptop_dell3 = new Product("Dell Inspiron3",
                "Dell Inspiron, 14-calowy laptop (czarny) z procesorami Intel Core 3. generacji", new BigDecimal(700), 1000,
                "http://ecx.images-amazon.com/images/I/81LcrgMpIcL._SL1500_.jpg");

        Product tablet_Nexus3 = new Product("Nexus 73",
                "Google Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro",
                new BigDecimal(300), 1000,
                "http://images.morele.net/full/706266_2_f.png");
        Product komputerIEM3 = new Product("Infinity IEM 20153",
                "Limitowana seria komputerów Komputronik Infinity Intel Extreme Masters powstała z myślą o finałach Mistrzostw Intel Extreme Masters 2015 które odbyły się w Katowicach.",
                new BigDecimal(3900), 100,
                "http://www.power-cy.com/wp-content/uploads/2014/07/pc.png");
        iphone.setCategory(category);
        iphone2.setCategory(category);
        iphone3.setCategory(category);
        category.addProduct(iphone3);
        category.addProduct(iphone2);
        category.addProduct(iphone);

        laptop_dell.setCategory(category1);
        laptop_dell3.setCategory(category1);
        laptop_dell2.setCategory(category1);
        komputerIEM.setCategory(category1);
        komputerIEM3.setCategory(category1);
        komputerIEM2.setCategory(category1);
        category1.addProduct(laptop_dell);
        category1.addProduct(laptop_dell2);
        category1.addProduct(laptop_dell3);
        category1.addProduct(komputerIEM);
        category1.addProduct(komputerIEM2);
        category1.addProduct(komputerIEM3);

        tablet_Nexus.setCategory(category2);
        tablet_Nexus2.setCategory(category2);
        tablet_Nexus3.setCategory(category2);
        category2.addProduct(tablet_Nexus);
        category2.addProduct(tablet_Nexus2);
        category2.addProduct(tablet_Nexus3);

        category = categoryRepository.save(category);
        category1 = categoryRepository.save(category1);
        category2 = categoryRepository.save(category2);
        logger.info(category.toString());
        logger.info(category1.toString());
        logger.info(category2.toString());
        logger.info(tablet_Nexus.getCategory().toString());
        Cart cart = new Cart();
        cartRepository.save(cart);
        CartItem cartItem = new CartItem(komputerIEM2);
        logger.info(cart.toString());
        logger.info(cartItem.toString());
        cart.addCartItem(cartItem);
        cart = cartRepository.updateCart(cart.getCartItems(), cart.getGrandTotal(), cart.getId());
        logger.info(cart.toString());
        logger.info(cartItem.toString());

       /* cartItemRepository.save(cartItem);
        cartItemRepository.save(new CartItem(komputerIEM2));
        logger.info("TU wchodzi?");*/


        /*Cart cart1 = new Cart();
        cartRepository.save(cart1);
        logger.info(cart1.toString());
        cart1.addCartItem(new CartItem(komputerIEM2));
        logger.info(cart1.toString());
        cart1=cartRepository.updateCart(cart1.getCartItems(),cart1.getGrandTotal(),cart1.getId());
        logger.info(cart1.toString());*/




    }

}
