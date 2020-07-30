import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LamdaTest {

    public static void main(String[] args) {
        List<Pair<Integer,String>> list = addData();
        Map<Integer,List<Pair<Integer,String>>> map = list.stream().collect(Collectors.groupingBy(Pair::getLeft));
        System.out.println("转换的结果为："+ JSONObject.toJSONString(map));
    }

    public static List<Pair<Integer,String>> addData(){
        List<Pair<Integer,String>> list = new ArrayList<Pair<Integer, String>>();
        int i = 0;
        while(i<100){
            Pair<Integer,String> pair = new MutablePair<Integer, String>(i/10,"蘑菇"+i+"号");
            list.add(pair);
            i+=1;
        }
        return list;
    }

}
