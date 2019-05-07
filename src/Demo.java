public class Demo {
  public static void main(String[] args) {
    Consumer consumer = new Consumer();
    Producer producer = new Producer();
    producer.sendMessasge();
    consumer.acceptMessage();
  }
}
