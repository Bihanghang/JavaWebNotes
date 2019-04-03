## SpringBoot版本:1.5.10 && 测试类放在启动类同级目录下
```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private TrainOrderBookingresultMapper trainOrderBookingresultMapper;

    @org.junit.Test
    public void test(){
        TrainOrderBookingresult trainOrderBookingresult = new TrainOrderBookingresult();
        trainOrderBookingresult.setOrderId(123456789l);
        trainOrderBookingresult.setMessage("hello");
        trainOrderBookingresult.setTradeNo("123456789");
        trainOrderBookingresult.setTotalAmount(1234.5);
        trainOrderBookingresult.setTickets(new Gson().toJson("hhwlo"));
        int insert = trainOrderBookingresultMapper.insert(trainOrderBookingresult);
        System.out.println(insert);

    }
}
```