package events;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        TextChannel channel = event.getChannel().asTextChannel();
        if(event.getMessage().getContentRaw().equals("ping"))
            channel.sendMessage(event.getAuthor().getGlobalName()).queue();
    }
}
