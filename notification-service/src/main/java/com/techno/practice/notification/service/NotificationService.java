package com.techno.practice.notification.service;

import com.techno.practice.order.event.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "order-placed")
    public void handleOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        // Logic to send notification
        logger.info("Notification sent for order: {}" , orderPlacedEvent);
        //send email logic can be added here
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("newshopping@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject("Order Placed Successfully - " + orderPlacedEvent.getOrderNumber());
            messageHelper.setText("Your order with order number " + orderPlacedEvent.getOrderNumber() + " has been placed successfully.");
        };
        try {
            javaMailSender.send(messagePreparator);
            logger.info("Notification email sent to: {}" , orderPlacedEvent.getEmail());
        } catch (Exception e) {
            logger.error("Error while sending email: {}" , e.getMessage());

        }
    }
}
