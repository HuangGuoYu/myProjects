package lambda.test;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Administrator on 2018/6/3.
 */
public class TestLambda {

    @Test
    public void test1() {
        //一参数无返回
        Consumer<String> consumer = (x) -> System.out.println(x);
//        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("hello world");
    }

    @Test
    public void test2() {

    }
}
