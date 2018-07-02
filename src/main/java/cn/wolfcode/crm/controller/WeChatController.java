package cn.wolfcode.crm.controller;

import cn.wolfcode.crm.domain.ArticleItem;
import cn.wolfcode.crm.domain.InMsgEntity;
import cn.wolfcode.crm.domain.OutMsgEntity;
import cn.wolfcode.crm.util.SecurityUtil;
import cn.wolfcode.crm.util.WeChatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;

@Controller
public class WeChatController {

    /**
     * URL接入校验
     * @return
     */
    @RequestMapping(value="weChat",method = RequestMethod.GET)
    @ResponseBody
    public String validate(String signature,String timestamp,String nonce,String echostr){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {WeChatUtil.TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        //自己加密的签名
        String mySignature = SecurityUtil.SHA1(sb.toString());
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(mySignature.equals(signature)){
            //请原样返回echostr参数内容，则接入生效，成为开发者成功
            System.out.println("接入成功");
            return echostr;
        }
        //否则接入失败
        System.out.println("接入失败");
        return null;
    }

    /**
     * 消息处理
     * @return
     */
    @RequestMapping(value="weChat",method = RequestMethod.POST)
    @ResponseBody
    public Object handleMessage(@RequestBody InMsgEntity inMsg) {
        OutMsgEntity outMsg = new OutMsgEntity();
        //发送方
        outMsg.setFromUserName(inMsg.getToUserName());
        //接收方
        outMsg.setToUserName(inMsg.getFromUserName());
        //消息创建时间
        outMsg.setCreateTime(new Date().getTime());
        //判断inMsg中的消息类型是文本还是图片
        String msgType = inMsg.getMsgType();
        //回复的信息
        String outContent = null;
        if (msgType.equals("text")) {
            //消息类型
            outMsg.setMsgType("text");
            //用户发送的信息
            String content = inMsg.getContent();
            if (content.contains("开班")) {
                outContent = "上海Java基础班第05期于2018/05/10开班\n" +
                        "广州Java基础班第24期于2018/04/02开班";
            } else if (content.contains("地址")) {
                outContent = "北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                        "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                        "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
            } else if (content.contains("我要学习")){
                //回复图文消息
                outMsg.setMsgType("news");
                //设置图文个数
                outMsg.setArticleCount(1);
                //设置图文明细列表
                ArticleItem item = new ArticleItem();
                item.setTitle("Java大神之路（第九季 SpringMVC）");
                item.setPicUrl("http://www.wolfcode.cn/upload/wheel_show_pictures/ee94e8d9-fd36-46c1-ab69-1cca9ccf0384.jpg");
                item.setDescription("Java大神之路在线课程，是叩丁狼教育推出的完整Java全套高级课程。");
                item.setUrl("https://ke.qq.com/course/275730#tuin=b30493d5");
                outMsg.setItem(new ArticleItem[]{item});
             }else {
                //设置消息内容
                outContent = inMsg.getContent();
            }
            outMsg.setContent(outContent);
        }else if(msgType.equals("image")){
            //消息类型
            outMsg.setMsgType("image");
            //设置图片信息
            outMsg.setMediaId(new String[]{inMsg.getMediaId()});
        }else if(msgType.equals("event")){
            //判断是否是关注事件
            if(inMsg.getEvent().equals("subscribe")){
                //回复普通文本消息
                outMsg.setContent("感谢关注叩丁狼教育！[亲亲]\n" +
                        "\n" +
                        "现在回复【我要学习】" +
                        "\n" +
                        "马上获取课程资料！[奸笑]");
                outMsg.setMsgType("text");
            }else if(inMsg.getEvent().equals("CLICK")){
                String eventKey = inMsg.getEventKey();
                //判断按钮的key值
                if ("classinfo".equals(eventKey)){
                    outContent = "上海Java基础班第05期于2018/05/10开班\n" +
                            "广州Java基础班第24期于2018/04/02开班";
                }else if("address".equals(eventKey)){
                    outContent = "北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                            "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                            "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
                }
                outMsg.setContent(outContent);
                outMsg.setMsgType("text");
            }
        }

        return outMsg;
    }
}
