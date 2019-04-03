1.logback.xml如下
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<configuration>

    <!-- custom coloring conversion -->
    <conversionRule conversionWord="beauty" converterClass="com.bihang.seaya.log.LogBeauty" />

    <appender name="consoleLog" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
              %beauty(%msg) %n
            </pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="consoleLog"></appender-ref>
    </root>
</configuration>
```
2.实现类如下:
```java
public class LogBeauty extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
        Level level = iLoggingEvent.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.BOLD + ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.RED_FG;
            case Level.INFO_INT:
                return ANSIConstants.BOLD + ANSIConstants.GREEN_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}
```