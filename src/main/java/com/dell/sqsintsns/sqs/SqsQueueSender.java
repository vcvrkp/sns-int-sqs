package com.dell.sqsintsns.sqs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQSAsync;

@Service
public class SqsQueueSender {

	private final QueueMessagingTemplate queueMessagingTemplate;
	
	AmazonSQSAsync amazonSqs;
	
	public SqsQueueSender(AmazonSQSAsync amazonSqs) {
		this.amazonSqs = amazonSqs;
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);

	}

	public void send(String message) {
		this.queueMessagingTemplate.send("sqs-int-sns", MessageBuilder.withPayload(message).build());
	}
	
	public void sendToFifo(String message) {
		Map<String, Object> headers = new HashMap<>();
		headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, "sqs-int-sns");
		headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
		queueMessagingTemplate.convertAndSend("sqs-int-sns.fifo",message,headers);
	}
	
}