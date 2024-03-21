import Db.*;
import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.sql.Connection;


public class Main {
    public static void main(String[] args){
        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);

        Database db = new Database();
        Connection conn = db.connectToDb();
        // Ran one time already
        // db.createTable(conn);

       try {

           JDA jda = jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                   .addEventListeners(new ReminderEventListener(), new JokeEventListener(), new ClearEventListener()).build();

           // we want setGuildOnly(true) when developing bot bc slash cmds that are global will take up to an hour to update
           // and by default, if we don't have setGuildOnly(true), it will register the upsertcommand() as a global command.
           // Only use setGuildOnly for dev mode, when ready for production, set it to false. setGuildOnly(false).
           jda.upsertCommand("joke","Time for a fat laugh").setGuildOnly(true).queue();

       }catch(InvalidTokenException exc){
           System.out.println("Token Invalid.");
       }catch(IllegalArgumentException exc2){
           System.out.println("Provided token is empty or null.");
       }

    }

}
