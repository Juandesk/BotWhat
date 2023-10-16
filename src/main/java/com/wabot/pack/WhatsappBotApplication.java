package com.wabot.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.WhatsappApiServiceGenerator;
import com.whatsapp.api.domain.messages.Message.MessageBuilder;
import com.whatsapp.api.domain.messages.TextMessage;
import com.whatsapp.api.domain.messages.response.MessageResponse;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import com.whatsapp.api.utils.Formatter;

@SpringBootApplication
public class WhatsappBotApplication {

	public static void main(String[] args) {
		
		//https://github.com/Bindambc/whatsapp-business-java-api#hammer_and_wrench-installation
		SpringApplication.run(WhatsappBotApplication.class, args);
		System.out.println("Hola");
		
		WhatsappApiFactory factory = WhatsappApiFactory.newInstance("Token");
		WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
		 
        // Set http proxy without credentials before the creation of the api instance
        WhatsappApiServiceGenerator.setHttpProxy("localhost", 8080, null,null);
        
        var message = MessageBuilder.builder()//
                .setTo("3028556706")//
                .buildTextMessage(new TextMessage()//
                        .setBody(Formatter.bold("Hello world!") + "\nSome code here: \n" + Formatter.code("hello world code here"))//
                        .setPreviewUrl(false));

        MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("phone_num_id", message);

        System.out.println(messageResponse);
	}

}
