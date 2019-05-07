import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

  public void sendMessasge(){
    ConnectionFactory connectionFactory;
    //连接工厂  JMS用它创建连接
    Connection connection = null;
    //JMS客户端到JMS  provider的连接
    Session session;
    //一个发送或者接受消息的线程
    Destination destination;
    //消息发送目的地， 消息谁接受
    MessageProducer messageProducer;
    //消息发送者

    connectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnection.DEFAULT_USER,
        ActiveMQConnection.DEFAULT_PASSWORD,
        "tcp://localhost:61616");
    try {
      //构造工厂得到连接对象
      connection = connectionFactory.createConnection();
      //启动
      connection.start();
      //获取操作连接
      session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
      //创建一个Queue，名称为SongLiGuo_FirstQueue
      destination = session.createQueue("wangyuhao");
      //得到消息生产者【发送者】
      messageProducer = session.createProducer(destination);
      //设置不持久化，根据实际情况而定
      messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

      TextMessage textMessage = session.createTextMessage("hello world");
      messageProducer.send(textMessage);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (Throwable ignore) {
      }
    }
  }
}
