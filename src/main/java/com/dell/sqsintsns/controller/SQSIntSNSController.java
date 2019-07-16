/**
 * 
 */
package com.dell.sqsintsns.controller;

import com.dell.sqsintsns.sns.SnsNotificationSender;
import com.dell.sqsintsns.sqs.SqsQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vcvr
 *
 */
@Controller
@RequestMapping("/test")
public class SQSIntSNSController {

	
	@Autowired
	private SnsNotificationSender snsNotificationSender;
	
	@Autowired 
	private SqsQueueSender sqsQueueSender;
	
	
	@GetMapping
	public String test(@RequestParam("messageString")String messageString) {
		System.out.println("Sedning Message to SQS :" + messageString);
		// send message to queue
		sqsQueueSender.sendToFifo(messageString);
		return messageString;
	}
	
	@SqsListener("sqs-int-sns.fifo")
	public void sendMessageToSNSFifo(String messageString) {
		System.out.println("Recieved Message From SQS And Seding to SNS :" + messageString);
		snsNotificationSender.send("same subject", messageString);
		System.out.println("Completed sending to SNS");
	}

	@SqsListener("sqs-int-sns")
	public void sendMessageToSNS(String message) {
		System.out.println("Recieved Message From SQS And Seding to SNS :" + message);
		snsNotificationSender.send("same subject", message);
		System.out.println("Completed sending to SNS");
	}

	@SqsListener("sns-sqs1")
	public void recieveMsgFromSNS1(String messageString) {
		System.out.println("Recieved Message at sns-sqs1:" + messageString);
	}
	
	@SqsListener("sns-sqs2")
	public void recieveMsgFromSNS2(String messageString) {
		System.out.println("Recieved Message at sns-sqs1:" + messageString);
	}

	@SqsListener("subscriber1")
	public void recieveMsgFromSNS3(String messageString) {
		System.out.println("Recieved Message at subscriber1:" + messageString);
	}
	
	@SqsListener("subscriber2")
	public void recieveMsgFromSNS4(String messageString) {
		System.out.println("Recieved Message at subscriber2:" + messageString);
	}
}
