package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
//@WebAppConfiguration
public class AdvConstructionTest {

    @Test
    public void simpleTest() {
        assertEquals(5, 5);
    }

}
