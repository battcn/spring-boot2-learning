package com.battcn;

import com.battcn.properties.MyProperties1;
import com.battcn.properties.MyProperties2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Chapter2ApplicationTests {


    @LocalServerPort
    private int port;

    private URL propertiesUrl1;
    private URL propertiesUrl2;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.propertiesUrl1 = new URL("http://localhost:" + port + "/properties/1");
        this.propertiesUrl2 = new URL("http://localhost:" + port + "/properties/2");
    }

    @Test
    public void testProperties1() throws Exception {
        template.getForEntity(propertiesUrl1.toString(), MyProperties1.class);
    }

    @Test
    public void testProperties2() throws Exception {
        template.getForEntity(propertiesUrl2.toString(), MyProperties2.class);
    }
}
