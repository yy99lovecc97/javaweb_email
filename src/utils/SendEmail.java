package utils;

import me.lovecc.domain.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail implements Runnable {
    private String from = "1178819076@qq.com";
    private String username = "1178819076@qq.com";
    private String password = "cvhshadjelcgjece";
    //发送邮件的服务器地址
    private String host = "smtp.qq.com";
    private User user;
    public SendEmail(User user){
        this.user = user;
    }
    @Override
    public void run() {
        try{
            Properties properties = new Properties();
            //指定SMTP服务器
            properties.setProperty("mail.host",host);
            properties.setProperty("mail.transport.protocol","smtp");
            //指定客户端是否向邮件服务器提交认证
            properties.setProperty("mail.smtp.auth", "true");
            // 开启ssl
            properties.setProperty("mail.smtp.ssl.enable", "true");
            //1、创建session
            Session session = Session.getInstance(properties);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport transport = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            transport.connect(host,username,password);
            //4、创建邮件
            Message message = creatEmail(session,user);
            //5、发送邮件
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Message creatEmail(Session session, User user) throws Exception {
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //指明邮件的发件人
        mimeMessage.setFrom(new InternetAddress(from));
        //指明邮件的收件人   Message.RecipientType.TO//发送
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
        //邮件的标题
        mimeMessage.setSubject("用户注册邮件");
        //saveChange() 方法能够保证报头域同会话内容保持一致
        mimeMessage.saveChanges();
        String info = "恭喜您注册成功，您的用户名：" + user.getUsername() + ",您的密码：" + user.getPassword() + "，请妥善保管，如有问题请联系网站客服!!";
        //邮件的文本内容
        mimeMessage.setContent("你好啊","text/html;charset = UTF-8");
        return mimeMessage;
    }
}
