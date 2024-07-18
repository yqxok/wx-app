package pri.yqx.util;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public static <T, R> Page<R> copyPage(Page<T> sourcePage, Supplier<R> targetSupplier) {
        Page<R> targetPage = new Page<>(sourcePage.getCurrent(), sourcePage.getSize(), sourcePage.getTotal());

        List<R> collect = sourcePage.getRecords().stream()
                .map(item -> copyProperties(item, targetSupplier.get())).collect(Collectors.toList());
        targetPage.setRecords(collect);

        return targetPage;
    }
}
