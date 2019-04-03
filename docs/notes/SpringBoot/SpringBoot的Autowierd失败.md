通常是以下几种可能：

1.没有加@Service注解，或者是这个bean没有放在标注了@Configuration这个注解的类下。

2.SpringBoot启动类没有开启扫描 **@ComponentScan(value = {"com.bihang"})**