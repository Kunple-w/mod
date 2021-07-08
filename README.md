# mod

#### 介绍
分布式和单体式自由组装框架，基于dubbo的简单实现，技术可行性研究。

#### 软件架构
软件架构说明

#####
目录结构:
```
mod
├── mod-api    mod相关的API
├── mod-autoconfiguration    // 基于spring的mod自动配置
├── mod-distribute    // 分布式实现
├── mod-monolith    // 单体式实现
└── mod-sample    // 使用示例，包括单体式和分布式
    ├── application-distribute    // 分布式示例，包含启动类、生产者service和消费者service
    │   ├── application-distribute-consumer    // 消费者启动应用service
    │   └── application-distribute-provider    // 生产者启动应用service
    ├── application-monolith    // 单体式应用
    ├── mod-sample-consumer    // 业务模块 消费者
    ├── mod-sample-provider    // 业务模块 生产者
    └── mod-sample-provider-api    // 业务模块 生产者api 
```

`mod-api`,`mod-distribute`, `mod-monolith`, `mod-autoconfiguration`是mod相关的实现和配置,

`mod-sample`是使用示例，包括分布式和单体式的示例.

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
