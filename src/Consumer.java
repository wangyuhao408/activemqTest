import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

  public void acceptMessage() {
    //connectionFactory 连接工厂，JMS用它创建连接
    ConnectionFactory connectionFactory;
    //connection JMS客户端到JMS provider 的连接
    Connection connection = null;
    //session一个发送或者接收的线程
    Session session;
    //destination 消息目的地，发送给谁接收
    Destination destination;
    //消费者消息接收者
    MessageConsumer consumer;

    connectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnection.DEFAULT_USER,
        ActiveMQConnection.DEFAULT_PASSWORD,
        "tcp://localhost:61616");
    try {
      connection = connectionFactory.createConnection();
      connection.start();
      session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
      destination = session.createQueue("wangyuhao");
      consumer = session.createConsumer(destination);
      TextMessage message = (TextMessage) consumer.receive(100);
      if (message != null) {
        System.out.println(message.getText());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != connection) {
          connection.close();
        }
      } catch (Throwable ignore) {
      }
    }
  }
}
