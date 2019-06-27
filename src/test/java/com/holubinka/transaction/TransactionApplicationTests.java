package com.holubinka.transaction;

import com.holubinka.transaction.dao.UserDao;
import com.holubinka.transaction.model.User;
import com.holubinka.transaction.services.UserServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = TransactionApplication.class)
public class TransactionApplicationTests {

    @Autowired
    UserServices userServices;
    @Before
    public void init(){
        User user = new User(null, "Vitalik", "Holubinka", "example@example.com");
        userServices.save(user);
    }
    @Test
    public void contextLoads() throws InterruptedException {
        /*User user = new User(null, "Vitalik", "Holubinka", "example@example.com");
        CompletableFuture.runAsync(() -> userServices.save(user));*/
        User expected = userServices.getByEmail("example@example.com");
        expected.setLastName("Holubinka1");

        User userTransaction = userServices.getByEmail("example@example.com");
        userTransaction.setLastName("Example");
        CompletableFuture.runAsync(() -> {
            User u = userServices.save(expected);
            System.out.println();
        });
        //Thread.sleep(5000);

        CompletableFuture.runAsync(() -> {
            User u1 = userServices.save(userTransaction);
            System.out.println();
        });
        User actual = userServices.getByEmail("example@example.com");

        Thread.sleep(5000);

        Assert.assertTrue(userTransaction.equals(actual));
    }

}
