package Generic;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nijiejie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicObj<T> {

    private String attr;

    private List<T> list;

}
