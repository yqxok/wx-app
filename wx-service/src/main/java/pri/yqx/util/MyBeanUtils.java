package pri.yqx.util;


import org.springframework.beans.BeanUtils;

public class MyBeanUtils {
    public static<M> M copyProperties(Object source,M target){
        BeanUtils.copyProperties(source,target);
        return target;
    }
}
