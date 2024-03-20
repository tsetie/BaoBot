package events;

import Client.JokeClient;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class JokeEventListener extends ListenerAdapter {
    JokeClient client = new JokeClient();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        // setEmphemeral true part just means only target user can see message response
        if (event.getName().equals("joke")) {
            event.reply(client.JokeGetRequest()).queue();

        }

    }
}
