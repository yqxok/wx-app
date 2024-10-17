package pri.yqx.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventPublisher {
    private static final Map<String, List<EventListner>> LISTNERS=new ConcurrentHashMap<>();
    public static void on(String eventName, EventListner listner){
        List<EventListner> eventListners = LISTNERS.get(eventName);
        if(eventListners==null){
            eventListners=new ArrayList<>();
            eventListners.add(listner);
            LISTNERS.put(eventName,eventListners);
        }else {
            eventListners.add(listner);
        }

    }
    public static void emit(String eventName,Object data){
        List<EventListner> eventListners = LISTNERS.get(eventName);
        if(eventListners==null) return;
        eventListners.forEach(i->i.handle(data));
    }
    public static void off(String eventName,EventListner listner){
        List<EventListner> eventListners = LISTNERS.get(eventName);
        if(eventListners==null) return;
        eventListners.remove(listner);
        if(eventListners.isEmpty())
            LISTNERS.remove(eventName);
    }
}
