package pri.yqx.vo;

import lombok.Data;

import java.time.*;
import java.util.List;

@Data
public class GoodCommentVo {
//    private Long goodId;
    private Long commentId;
    private Long userId;
    private String profilePicUrl;
    private String userName;
//    private Integer sonCommentNum;
    private String content;
    private Long fatherId;
    private Long replyUserId;
    private String replyName;
    private Integer goodJobNum;
    private Boolean isGoodJob;//是否被当前用户点赞
//    private
    private LocalDateTime createTime;
    public String getCreateTime(){
        LocalDate localDate = this.createTime.toLocalDate();
        LocalTime localTime = this.createTime.toLocalTime();
        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        Period between = Period.between(localDate, now);
        Duration between1 = Duration.between(localTime, now1);
        if(between.getYears()>0) return between.getYears()+"年前";
        else if(between.getMonths()>0) return between.getMonths()+"个月前";
        else if(between.getDays()>0) return between.getDays()+"天前";
        else if(between1.toHours()>0) return between1.toHours()+"小时前";
        else if(between1.toMinutes()>0) return between1.toMinutes()+"分钟前";
        else return "1分钟前";
    }
    public LocalDateTime getTrueCreateTime(){
        return this.createTime;
    }

    private List<GoodCommentVo> sonComments;
    private Integer sonCommentNum;

}
