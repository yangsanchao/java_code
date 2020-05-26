package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

/**
 * <p>Description: VolatileDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/25 21:56
 **/
@Log4j2
public class VolatileTest {

    private static volatile Integer flag = 0;
    private static Integer flag2 = 0;

    public static void main(String[] args) {
        flag++;
        flag2++;
    }
}
