package com.fallsea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月26日 20:38
 * 4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {
    @Test
    public void name() {
        System.out.println("执行了自动ack模式");
    }

    @Test
    public void name2() {
        System.out.println("执行了手动ack模式");
    }

    @Test
    public void name3() {
        System.out.println("执行了手动异常ack模式");
        while (true){

        }
    }
}
