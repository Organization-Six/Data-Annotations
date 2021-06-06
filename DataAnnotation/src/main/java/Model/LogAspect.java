package Model;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;




public class LogAspect {

		public static void Log(String className , String content) {
		try {
			Class<?> cls = Class.forName(className);
			Logger log = Logger.getLogger(cls);
			log.info(content);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
