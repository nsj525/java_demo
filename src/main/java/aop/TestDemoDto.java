package aop;

import org.springframework.stereotype.Component;

/**
 * !!!注意
 *
 * @author nijiejie
 */
@Component
public class TestDemoDto {

    @TryImport(key = "ceshi",value = "123")
    public String tryImport() {
        return "返回值是我";
    }

}
