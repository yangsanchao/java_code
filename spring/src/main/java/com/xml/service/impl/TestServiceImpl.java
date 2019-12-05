package com.xml.service.impl;

import com.xml.service.TestService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * <p>Description: TestServiceImpl</p>
 *
 * <p>Copyright: © 2018-至今 .All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2019-09-24 11:31:31
 **/
@Log4j2
public class TestServiceImpl implements TestService {
    @Override
    public void print() {
        log.info("success");
    }
}