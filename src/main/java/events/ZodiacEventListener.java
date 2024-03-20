//package events;
//
//import Client.JokeClient;
//import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.dv8tion.jda.api.interactions.components.buttons.Button;
//
//public class ZodiacEventListener extends ListenerAdapter {
//    JokeClient client = new JokeClient();
//    @Override
//    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
//        super.onSlashCommandInteraction(event);
//        // setEmphemeral true part just means only target user can see message response
//            if(event.getName().equals("aries")){
//                event.reply("What day would you like your horoscope for?")
//                        .addActionRow(
//                                Button.primary("todayAries","Today"),
//                                Button.success("tomorrowAries", "Tomorrow"),
//                                Button.secondary("yesterdayAries", "Yesterday")).queue();
//            }
//    }
//
//    @Override
//    public void onButtonInteraction(ButtonInteractionEvent event) {
//        super.onButtonInteraction(event);
//        if(event.getComponentId().equals("todayAries")){
//            event.reply(client.zodiacPostReq("aries","today")).queue();
//        }else if(event.getComponentId().equals("tomorrowAries")){
//            event.reply(client.zodiacPostReq("aries","tomorrow")).queue();
//        } else if (event.getComponentId().equals("yesterdayAries")) {
//            event.reply(client.zodiacPostReq("aries","yesterday")).queue();
//        }
//    }
//}
