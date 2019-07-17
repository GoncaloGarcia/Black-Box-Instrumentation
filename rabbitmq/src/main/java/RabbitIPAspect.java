
/*
 * Copyright 2019 Feedzai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.rabbitmq.client.impl.ChannelN;
import io.opentracing.util.GlobalTracer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * AspectJ Aspect that instruments the RabbitMQ driver to automatically tag the Span with the IP address of the contacted node.
 *
 * @author Gonçalo Garcia (goncalo.garcia@feedzai.com)
 */
@Aspect
public class RabbitIPAspect {

    /**
     * Tags the active Span whenever a call to {@link com.rabbitmq.client.impl.ChannelN#basicPublish}
     *
     * @param point The joinpoint that represents the point in the execution of the RabbitMQ driver that will be advised by the following code.
     */
    @After("execution(void com.rabbitmq.client.impl.ChannelN.basicPublish(..))")
    public void runAfterRabbit(JoinPoint point){
        String IP = ((ChannelN) point.getThis()).getConnection().getAddress().getHostAddress();
        if(GlobalTracer.isRegistered() && GlobalTracer.get().activeSpan() != null){
            GlobalTracer.get().activeSpan().setTag("Rabbit-IP", IP);
        }
    }
}
