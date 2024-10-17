package pri.yqx.util;



import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class MyBeanUtils {
    public static<M> M copyProperties(Object source,M target){
        BeanUtils.copyProperties(source,target);
        return target;
    }

}
