## java动态获取WebService的两种方式（复杂参数类型)

第一种：
```java
@Override
public OrderSearchListRes searchOrderList(Order_FlightOrderSearchRequest request) {
    Object myAllMessage;
    OrderSearchListRes response = null;
    try {
        String endpoint = carGlobalSetting.getEndpoint();

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf
                .createClient(endpoint);
        QName name = new QName(carGlobalSetting.getNamespaceURI(), "searchOrderList");
        Object person = Thread.currentThread().getContextClassLoader().loadClass("com.uni.webservice.service.neworder.inter.OrderSearchReq").newInstance();

        Method m1 = person.getClass().getMethod("setSalesChannel", String.class);
        Method m2 = person.getClass().getMethod("setPassportId", Long.class);
        Method m3 = person.getClass().getMethod("setBeginDate", Integer.class);
        Method m4 = person.getClass().getMethod("setEndDate", Integer.class);
        Method m5 = person.getClass().getMethod("setOrderStatus", String.class);
        Method m6 = person.getClass().getMethod("setPage", Integer.class);
        Method m7 = person.getClass().getMethod("setPageSize", Integer.class);

        m1.invoke(person, request.getSalesChannel());
        m2.invoke(person, request.getPassportId());
        m3.invoke(person, request.getBeginDate());
        m4.invoke(person, request.getEndDate());
        m5.invoke(person, request.getOrderStatus());
        m6.invoke(person, request.getPage());
        m7.invoke(person, request.getPageSize());

        try {
            myAllMessage = client.invoke(name, person);
            LogHelper.debug(myAllMessage.toString());
            String s = JSON.toJSONString(myAllMessage);
            JSONArray jsonArray = JSON.parseArray(s);
            /**
                * 将Json转为具体对象
                */
            for (Object o :
                    jsonArray) {
                JSONObject j = (JSONObject) o;
                response = JSON.parseObject(j.toJSONString(), new TypeReference<OrderSearchListRes>() {
                });
            }

        } catch (Exception e) {
            LogHelper.error("Json转化异常"+e.getMessage()+e.getStackTrace(),
                    "searchOrderList","searchOrderList");
        }
    } catch (Exception e) {
        LogHelper.error("获取WebService异常"+e.getMessage()+e.getStackTrace(),
                "searchOrderList","searchOrderList");
    }
    return response;
}
```
第二种:
```java
private static String wsdlUrl = "http://172.20.29.51:8180/uniplatform/service/UniNewOrderDataService?wsdl";

public static void main(String[] args) throws Exception {
    // 创建动态客户端
    JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
    Client client = factory
            .createClient(wsdlUrl);
    /**endpoint据说为http://172.20.29.51:8180/uniplatform/service/UniNewOrderDataService
        * 不过toString方法打印的为{},有点奇怪,不过getEndpointInfo打印的为BindingQName，ServiceQName，QName*/
    Endpoint endpoint = client.getEndpoint();
    /**获取Service*/
    ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
    /**创建Service*/
    Collection<BindingInfo> bindings = serviceInfo.getBindings();
    BindingInfo binding = null;
    for (BindingInfo b :
            bindings) {
            binding = b;
    }

    /**创建Service下的方法*/
    QName opName = null;

    for (BindingOperationInfo bindingOperationInfo:
            binding.getOperations()) {
        if ("searchOrderList".equals(bindingOperationInfo.getName().getLocalPart())){
            opName = bindingOperationInfo.getName();
        }
    }
    BindingOperationInfo operation2 = binding.getOperation(opName);
    BindingMessageInfo input = null;

    if (operation2.isUnwrapped()){
        input = operation2.getUnwrappedOperation().getInput();
    } else {
        input = operation2.getWrappedOperation().getInput();
    }

    List<MessagePartInfo> messageParts = input.getMessageParts();

    MessagePartInfo messagePartInfo = messageParts.get(0);
    Class<?> partClass = messagePartInfo.getTypeClass();
    Object inputObject = partClass.newInstance();


    PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("salesChannel", partClass);
    partPropertyDescriptor.getWriteMethod().invoke(inputObject, "712");

    PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor("passportId", partClass);
    partPropertyDescriptor2.getWriteMethod().invoke(inputObject, Long.valueOf("31498882"));

    PropertyDescriptor partPropertyDescriptor3 = new PropertyDescriptor("beginDate", partClass);
    partPropertyDescriptor3.getWriteMethod().invoke(inputObject, 20181230);

    PropertyDescriptor partPropertyDescriptor4 = new PropertyDescriptor("endDate", partClass);
    partPropertyDescriptor4.getWriteMethod().invoke(inputObject, 20190109);

    PropertyDescriptor partPropertyDescriptor5 = new PropertyDescriptor("orderStatus", partClass);
    partPropertyDescriptor5.getWriteMethod().invoke(inputObject, "10054");

    Object[] result = client.invoke(opName, inputObject);
}
```

最后，返回的Object类型数据还是只能先将其转为Json，再将Json转化为对象，没办法直接拿到。

需要的两个依赖：
```xml
<dependency>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-rt-frontend-jaxws</artifactId>
    <version>3.2.6</version>
</dependency>

<dependency>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-rt-transports-http</artifactId>
    <version>3.2.7</version>
</dependency>
```















2.definitions

definitions 是 WSDL 的根节点，它包括两个重要的属性：
* name：WS 名称，默认为“WS 实现类 + Service”，例如：HelloServiceImplService
* targetNamespace：WS 目标命名空间，默认为“WS 实现类对应包名倒排后构成的地址”，例如：Target namespace: http://impl.newffp.service.webservice.uni.com/
