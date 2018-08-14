package com.rabbitmq;

import com.rabbitmq.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;


	/**
	 * 点对点
	 */
	@Test
	public void contextLoads() {
		//message需要自己构造，定制消息体内容和消息头
//		rabbitTemplate.send();
		//转发并发送，传入发送对象，自动序列化到rabbitmq
		rabbitTemplate.convertAndSend("exchanges.direct","huoshan.news","massage for spring_boot ListenReceive");

	}

	/**
	 * 接收消息
	 */
	@Test
	public void contextLoads2(){
		Object huoshan = rabbitTemplate.receiveAndConvert("huoshan");
		System.out.println(huoshan);

	}
	/**
	 * 插入对象（序列化对象）
	 */
	@Test
	public void contextLoads3() {
		User user = new User("Jerry",20);
		rabbitTemplate.convertAndSend("exchanges.direct","huoshan.news",user);
	}

	/**
	 * 广播
	 */
	@Test
	public void contextLoads4() {
		rabbitTemplate.convertAndSend("exchanges.fanout","","This is fanout type message");
	}

	/**
	 * amqpAdmin代码的层次 创建和删除（交换机、队列、绑定）
	 */
	@Test
	public void contextLoads5() {
		//创建Exchange
//		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
		//创建Queue
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
		//创建绑定 参数1.目的地 参数2.类型（队列） 参数3.交换机类型 参数4.路由键 参数5.map参数
		//Binding(String destination, Binding.DestinationType destinationType, String exchange, String routingKey,
		//		Map<String, Object> arguments)
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqpAdmin",null));

	}


}
