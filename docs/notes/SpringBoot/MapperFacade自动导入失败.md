添加以下代码并且保证项目可以扫描到:
```java
@Configuration
public class OrikaConfig {
    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }
}
```
## 映射
```java
/**映射枚举值*/
MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
mapperFactory.getConverterFactory().registerConverter("TrainTicketStatusConverter",new BidirectionalConverter<Enum_TrainTicketStatus, Integer>(){

    @Override
    public Integer convertTo(Enum_TrainTicketStatus source, Type<Integer> destinationType, MappingContext mappingContext) {
        return source.getId();
    }

    @Override
    public Enum_TrainTicketStatus convertFrom(Integer source, Type<Enum_TrainTicketStatus> destinationType, MappingContext mappingContext) {
        return null;
    }
});
Train_OrderBookingTicketDB train_orderBookingTicketDB = new Train_OrderBookingTicketDB();
train_orderBookingTicketDB.setBookingID(123l);
train_orderBookingTicketDB.setOrderID(112222l);
train_orderBookingTicketDB.setTicketId("2222");
train_orderBookingTicketDB.setStatusName("nihao");
train_orderBookingTicketDB.setID(22233l);
train_orderBookingTicketDB.setStatus(Enum_TrainTicketStatus.Abolish);
mapperFactory.classMap(TrainOrderBookingticket.class,Train_OrderBookingTicketDB.class)
        .field("id","ID")
        .fieldMap("status","status").converter("TrainTicketStatusConverter").add()
        .byDefault()
        .register();

System.out.println(mapperFactory.getMapperFacade().map(train_orderBookingTicketDB,TrainOrderBookingticket.class).toString());
```
Orika默认是没有开头字母大写的，所以如果想要找到那个属性，需要将开头字母大写的改为小写。