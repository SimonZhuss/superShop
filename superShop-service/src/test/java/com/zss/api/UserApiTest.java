package com.zss.api;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.zss.AbstractClientSideTest;
import com.zss.superShop.api.req.UserInfoReq;

@EnableAutoConfiguration
public class UserApiTest extends AbstractClientSideTest{

	@Test
    public void testQueryUserInfo() {
        UserInfoReq req = new UserInfoReq();
        req.setName("aa");
        String body = testRestTemplate.postForEntity("/user/queryUserInfo", req, String.class).getBody();
        Assert.assertNotNull("Result body is not null.", body);
    }
}
