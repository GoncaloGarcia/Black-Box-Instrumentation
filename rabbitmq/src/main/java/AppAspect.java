
import com.rabbitmq.client.impl.ChannelN;
import io.opentracing.util.GlobalTracer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class AppAspect {



    @After("execution(void com.rabbitmq.client.impl.ChannelN.basicPublish(..))")
    public void runAfterRabbit(JoinPoint point){
        String IP = ((ChannelN) point.getThis()).getConnection().getAddress().getHostAddress();
        if(GlobalTracer.isRegistered() && GlobalTracer.get().activeSpan() != null){
            GlobalTracer.get().activeSpan().setTag("Rabbit-IP", IP);
        }
    }
}
