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
			
			Class<?> cls = Class.forName(className);
			Logger log = Logger.getLogger(cls);
			log.info(content);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
