create table wx_address
(
    address_id   bigint        not null comment '地址id'
        primary key,
    user_id      bigint        not null comment '用户id,联合id',
    dormi_id     bigint        not null comment '宿舍id，联合id',
    dormi_num    int           not null comment '宿舍号',
    phone_number char(15)      not null comment '手机号',
    create_time  datetime      not null comment '创建时间',
    update_time  datetime      null comment '更新时间',
    version      int default 0 null comment '版本号',
    is_delete    int default 0 null
)
    comment '地址表';

create table wx_category
(
    category_id   bigint               not null comment '分类id'
        primary key,
    category_name varchar(255)         not null comment '分类名称,唯一且不为空',
    pk_id         bigint               null comment '自关联的key',
    create_user   bigint               not null comment '创建人',
    create_time   datetime             not null comment '创建时间',
    is_deleted    tinyint(1) default 0 null comment '逻辑删除,0未删除，1删除',
    update_time   datetime             null,
    version       int        default 0 null,
    is_delete     int        default 0 null,
    constraint category_name
        unique (category_name)
)
    comment '分类表';

create table wx_chat_content
(
    chat_id         bigint        not null comment '消息id'
        primary key,
    send_user_id    bigint        null comment '发送人id',
    receive_user_id bigint        null comment '接收人id',
    content         text          not null comment '聊天内容',
    create_time     datetime      null,
    update_time     datetime      null,
    version         int default 0 null,
    is_deleted      int default 0 null
)
    comment '聊天记录';

create index ink_chat
    on wx_chat_content (send_user_id, receive_user_id);

create table wx_collect
(
    collect_id  bigint        not null comment '收藏id'
        primary key,
    user_id     bigint        not null comment '用户id',
    good_id     bigint        not null comment '商品id',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null,
    version     int default 0 null,
    is_deleted  int default 0 null
)
    comment '收藏表';

create table wx_dormitory
(
    dormitory_id bigint        not null comment '宿舍楼id'
        primary key,
    dormi_name   varchar(255)  not null comment '宿舍楼名字',
    school       char(255)     null comment '学校',
    zone         varchar(255)  null comment '校区',
    create_time  datetime      null,
    update_time  datetime      null,
    version      int default 0 null,
    is_deleted   int default 0 null
)
    comment '宿舍表';

create table wx_good
(
    good_id       bigint               not null comment '商品id'
        primary key,
    html          text                 not null comment '商品描述,h5',
    pic_urls      varchar(255)         not null comment '商品图片链接,url数组',
    price         decimal(10, 2)       not null comment '商品价格',
    status        tinyint    default 0 null comment '商品状态，0未出售，1已出售',
    browser_times int        default 1 null comment '被浏览次数,每个用户对应一次',
    user_id       bigint               not null comment '关联user_id',
    create_time   datetime             not null comment '创建时间',
    update_time   datetime             not null comment '更新时间',
    is_deleted    tinyint(1) default 0 null comment '逻辑删除,0未删除，1删除',
    version       int        default 0 null
)
    comment '商品表';

create table wx_good_category
(
    good_category_id bigint        not null comment '主键'
        primary key,
    good_id          bigint        not null comment '关联good_id',
    category_id      bigint        not null comment '关联category_id',
    category_name    varchar(255)  null comment '冗余字段',
    create_time      datetime      null,
    update_time      datetime      null,
    version          int default 0 null,
    is_deleted       int default 0 null
)
    comment '商品分类联合表';

create table wx_good_comment
(
    comment_id    bigint        not null comment 'id'
        primary key,
    good_id       bigint        not null comment '商品id',
    user_id       bigint        not null comment '留言人id',
    content       text          not null comment '留言内容',
    father_id     bigint        null comment '父评论',
    create_time   datetime      not null comment '创建时间',
    reply_user_id bigint        null comment '回复某人评论的id',
    reply_name    varchar(255)  null,
    user_name     varchar(255)  null comment '回复人昵称，不遵守第三范式',
    update_time   datetime      null,
    version       int default 0 null,
    is_deleted    int default 0 null
)
    comment '商品留言表';

create table wx_good_order
(
    order_id    bigint               not null comment '订单id'
        primary key,
    number      int        default 1 null comment '购买商品数量',
    total_price decimal(10, 2)       not null comment '总价',
    s_address   varchar(255)         not null comment '发货地址',
    r_address   varchar(255)         not null comment '收货地址',
    status      tinyint    default 0 null comment '订单状态，0未完成，1完成',
    good_id     bigint               not null comment '关联的商品id',
    user_id     bigint               not null comment '关联的user_id',
    create_time datetime             not null comment '创建时间',
    is_deleted  tinyint(1) default 0 null comment '逻辑删除,0未删除，1删除',
    update_time datetime             null,
    version     int        default 0 null
)
    comment '商品订单表';

create table wx_user
(
    user_id         bigint                         not null comment '用户id,雪花算法'
        primary key,
    user_name       varchar(255) default '莞工er'  null comment '用户名',
    password        varchar(255)                   null comment '密码，不可为空',
    phone_number    varchar(20)                    null comment '电话号码，唯一',
    birthday        date                           null comment '出生日期',
    gender          enum ('MALE', 'FEMALE')        null comment '性别',
    bio             text                           null comment '个人简介',
    profile_pic_url varchar(255)                   null comment '个人头像链接',
    create_time     datetime                       not null comment '注册日期',
    last_login      datetime                       null comment '最后登录时间',
    account_type    char(10)     default 'Regular' null comment '账户类型，默认为普通用户，REGULAR, SELLER, ADMIN',
    is_deleted      tinyint(1)   default 0         not null comment '逻辑删除',
    open_id         varchar(255)                   not null,
    session_key     varchar(255)                   not null,
    update_time     datetime                       null,
    version         int          default 0         null,
    constraint phone_number
        unique (phone_number),
    constraint chk_password
        check (length(`password`) >= 8),
    constraint chk_username
        check (length(`user_name`) >= 3)
)
    comment '用户表';

