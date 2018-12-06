## 简介
[详情点击](http://elim.iteye.com/blog/2396274)
基本语法:
```
<aop:config>
	<aop:advisor advice-ref="" pointcut-ref=""/>
</aop:config>
```
如果在一个config元素下既定义了aspect，又定义了advisor，那advisor必须定义在aspect之前。
## before Advice
```java
public class LogBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("===============before advice start==============");
		System.out.println("method: " + method);
		System.out.println("args: " + args);
		System.out.println("target: " + target);
		System.out.println("===============before advice end================");
	}

}
```
```xml
<aop:config>
	<aop:pointcut expression="bean(userService)" id="userServicePointcut"/>	
	<aop:advisor advice-ref="logBeforeAdvice" order="1" pointcut-ref="userServicePointcut"/>
</aop:config>

<bean id="logBeforeAdvice" class="com.elim.spring.aop.advice.LogBeforeAdvice" />
```
## around Advice
```java
public class LogAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("=============================方法调用开始===" + invocation.getMethod());
		try {
			Object result = invocation.proceed();
			System.out.println("=============================方法调用正常结束===" + invocation.getMethod());
			return result;
		} catch (Exception e) {
			System.out.println("=============================方法调用异常===" + invocation.getMethod());
			throw e;
		}
	}

}
```
获取参数:
```java
public class LogAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("=============================方法调用开始===" + invocation.getMethod());
		try {
			Object result = invocation.proceed();
			System.out.println("=============================方法调用正常结束===" + invocation.getMethod());
			return result;
		} catch (Exception e) {
			System.out.println("=============================方法调用异常===" + invocation.getMethod());
			throw e;
		}
	}

}
```

```xml
<aop:config>
	<aop:pointcut expression="bean(userService)" id="userServicePointcut"/>	
	<aop:advisor advice-ref="logAroundAdvice" pointcut-ref="userServicePointcut"/>
</aop:config>

<bean id="logAroundAdvice" class="com.elim.spring.aop.advice.LogAroundAdvice"/>
```

