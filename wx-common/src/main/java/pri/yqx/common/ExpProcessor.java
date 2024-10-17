package pri.yqx.common;


import pri.yqx.exceptions.BusinessException;

import java.util.function.Function;
//链式处理异常
public class ExpProcessor {
    private Function<String,String> processor;
    public ExpProcessor add(boolean isHandle, String expMsg){
        if(isHandle&&processor==null)
            processor=o -> {throw new BusinessException(expMsg);};
        return this;
    }
    public void execute(){
        if(processor!=null)
            processor.apply(null);
    }
}
