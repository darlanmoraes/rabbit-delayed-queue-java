package com.darlan.rabbit.delayed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.darlan.rabbit.delayed.RabbitConfiguration.DELAYED_QUEUE;

import java.util.UUID;

@RestController
public class RabbitSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSender.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }    

    @RequestMapping(value = "/rabbit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody MessageResponse sendMessage() {
        final String message = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(DELAYED_QUEUE, message);
        LOGGER.info("Sent <{}>", message);
        return new MessageResponse(message);
    }

    public static final class MessageResponse {

        private final String message;

        public MessageResponse(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

}
