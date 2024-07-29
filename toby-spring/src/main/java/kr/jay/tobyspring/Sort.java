package kr.jay.tobyspring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/29/24
 */
public class Sort {

    public List<String> sortByLength(List<String> list){
        list.sort( (o1,o2)-> o1.length() - o2.length());
        return list;
    }
}
