package com.wtw.config;

import com.wtw.domain.Product;
import com.wtw.domain.Role;
import com.wtw.domain.User;
import com.wtw.repository.CartItemRepository;
import com.wtw.repository.CartRepository;
import com.wtw.repository.ProductRepository;
import com.wtw.repository.UserRepository;
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

    @PostConstruct
    public void populateDatabase() {
        logger.info("ładowanie bazy testowej");

        repository.save(new User("Adam", "Małysz", "aaa1@o2.pl", new BCryptPasswordEncoder().encode("aaa")));
        User user = new User("Admin", "admin", "aaa2@o2.pl", new BCryptPasswordEncoder().encode("1"));
        user.setRole(Role.ADMIN);
        repository.save(user);
        user = new User("SuperAdmin", "superadmin", "aaa3@o2.pl", new BCryptPasswordEncoder().encode("a"));
        repository.save(user);

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

        productRepository.save(iphone);
        productRepository.save(laptop_dell);
        productRepository.save(tablet_Nexus);
        productRepository.save(komputerIEM);
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

        productRepository.save(iphone2);
        productRepository.save(laptop_dell2);
        productRepository.save(tablet_Nexus2);
        komputerIEM2 = productRepository.save(komputerIEM2);

        /*Cart cart = new Cart();
        cartRepository.save(cart);
        CartItem cartItem = new CartItem(komputerIEM2);
        cartItem.setCart(cart);
        cartItem = cartItemRepository.save(cartItem);
        cart.addCartItem(cartItem);
        cartRepository.updateCart(cart.getCartItems(), cart.getGrandTotal(), cart.getId());*/


    }

}
