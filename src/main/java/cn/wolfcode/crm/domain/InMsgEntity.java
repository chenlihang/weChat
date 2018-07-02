package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InMsgEntity {
    private String ToUserName;   //  开发者微信号
    private String FromUserName;   // 发送方帐号（一个OpenID）
    private Long CreateTime;   // 消息创建时间 （整型）
    private String MsgType;   // text/image/event/...
    private Long MsgId;   // 消息id，64位整型

    private String Content;   // 文本消息内容

    private String PicUrl;	//图片链接（由系统生成）
    private String MediaId;	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

    private String Event; //事件类型 subscribe(订阅)、unsubscribe(取消订阅)、CLICK(点击菜单)
    private String EventKey; //菜单的key值
}
