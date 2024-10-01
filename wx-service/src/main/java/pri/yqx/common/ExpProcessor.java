package pri.yqx.common;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;

public class ExpProcessor {
    private Function<String,String> processor;
    public ExpProcessor add(boolean isHandle, String expMsg){
        if(isHandle&&processor==null)
            processor=o -> {throw new RuntimeException(expMsg);};
        return this;
    }
    public void execute(){
        if(processor!=null)
            processor.apply(null);

    }
}
