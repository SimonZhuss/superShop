package com.zss;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.zss.superShop.constants.*;

import java.util.Collections;

/**
 * Mock http client request
 * <p>
 * Created by zhushanshan on 2018/7/16 下午6:46.
 */
@EnableAutoConfiguration
public abstract class AbstractClientSideTest extends BaseTest {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractClientSideTest.class);

    @Before
    public void setHeader() {
        testRestTemplate.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders().add(WebConstants.HEADER_PLATFORM, "web");
            request.getHeaders().add(WebConstants.HEADER_TIMESTAMP, "1970-01-01 00:00:00");
            request.getHeaders().add(WebConstants.HEADER_TOKEN, "5D4A34E3427B2BE91D6DD3063EF4ABB68CF27186C99AF6D77E807F16BFED4A7B");
            return execution.execute(request, body);
        }));
    }

    @Autowired
    protected TestRestTemplate testRestTemplate;
}