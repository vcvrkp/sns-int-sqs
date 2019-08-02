package com.dell.sqsintsns.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;

@Service
public class SnsNotificationSender {

	private final NotificationMessagingTemplate notificationMessagingTemplate;

	@Autowired
	public SnsNotificationSender(AmazonSNS amazonSns) {
		this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSns);
	}

	public void send(String subject, String message) {
		this.notificationMessagingTemplate.sendNotification("sns-int-sqs", message, subject);
	}
}