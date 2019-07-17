
import io.opentracing.util.GlobalTracer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class AppAspect {



    @After("execution(boolean com.datastax.driver.core.RequestHandler.SpeculativeExecution.query(..))")
    public void runAfterCassandra(JoinPoint point){
        String IP = point.getArgs()[0].toString() + " " + Thread.currentThread().getId();
        if(GlobalTracer.isRegistered() && GlobalTracer.get().activeSpan() != null){
            System.out.println(GlobalTracer.get().activeSpan());
            GlobalTracer.get().activeSpan().setTag("Cassandra-IP", IP);
        }
    }
}
