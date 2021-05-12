package Log;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
	@After("@annotation(logAnnotation)")
	public void log(JoinPoint joinPoint,LogAnnotation logAnnotation) {
		String className = logAnnotation.className();
		String content = logAnnotation.content();
		try {
			//获取想要打印日志的方法的参数，这里只使用第一个参数，用于爬虫的日志编写
			Object[] args = joinPoint.getArgs();
			
			if(args.length>0) {
			String id = (String) args[0];
			Class<?> cls = Class.forName(className);
			Logger log = Logger.getLogger(cls);
			log.info(content+id);		
			}else {
				Class<?> cls = Class.forName(className);
				Logger log = Logger.getLogger(cls);
				log.info(content);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
