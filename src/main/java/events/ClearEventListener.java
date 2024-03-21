package events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class ClearEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        // retrieve the channel associated with the event/message received
        TextChannel channel = event.getChannel().asTextChannel();

        try{
            if(event.getMessage().getContentRaw().toLowerCase().startsWith("clear")){
                // amount of messages to delete, parseInt throw e if string is not a parsable int
                int amount = Integer.parseInt(event.getMessage().getContentRaw().substring("clear".length()).trim());
                // stores the messages that will be deleted
                List<Message> messageList = channel.getHistory().retrievePast(amount+1).complete();
                channel.deleteMessages(messageList).queue();
            }
        }catch(NumberFormatException e){
            channel.sendMessage("Correct format is: ```clear <number>```").queue();
            System.out.println(e.getMessage());
        }


    }
}
