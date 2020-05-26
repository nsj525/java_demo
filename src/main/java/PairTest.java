import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class PairTest {

    /**
     * 用的apache common包下的pair试验，Pair类似于map，也是键值对的方式存储，但是一个pair中只能存在一个元素，用于做方法内核方法间的参数传递较为合适
     * @param args
     */
    public static void main(String[] args) {
        Queue<Pair<Integer,String>> queue = new LinkedList<Pair<Integer, String>>();
        MutablePair<Integer,String> pair = new MutablePair<Integer,String>(0,"小蘑菇");

        //验证一个pair相当于一个对象，区别于map，只能有一个值
        pair.setLeft(1);
        pair.setRight("大蘑菇");
        queue.add(pair);
        System.out.println("queue结构为："+ JSONObject.toJSONString(queue));

        while(!queue.isEmpty()){
            Pair<Integer,String> pop = queue.poll();
            System.out.println("poll方法弹出的值："+JSONObject.toJSONString(pop));
            System.out.println("poll后queue的值："+JSONObject.toJSONString(queue));
            System.out.println("left方法获取的值："+pop.getLeft());

            //getValue()内部实现就是调用了getRight()方法
            System.out.println("right方法获取的值："+pop.getRight());
            System.out.println("value方法获取的值："+pop.getValue());
        }
    }

}
