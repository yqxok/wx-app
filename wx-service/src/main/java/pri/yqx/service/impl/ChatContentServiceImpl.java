package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pri.yqx.entity.Category;
import pri.yqx.entity.ChatContent;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.ChatContentMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.ChatContentService;

@Service
public class ChatContentServiceImpl extends ServiceImpl<ChatContentMapper, ChatContent> implements ChatContentService {
}
