package events;

import Db.Database;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;

public class ReminderEventListener extends ListenerAdapter {
    // Connect to db
    Database db = new Database();
    Connection conn = db.connectToDb();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        // retrieve the channel associated with the event
        TextChannel channel = event.getChannel().asTextChannel();
        String username = event.getAuthor().getGlobalName();
        try {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("reminders")) {
                ResultSet rs = db.showAllReminders(conn, username, event);
                // for every row in db, it will print the reminderID and reminder
                // next() method of the rs obj moves the cursor to the next
                // row in the result set and returns true if there is a next row available
                while (rs.next()) {
                    channel.sendMessage("```" + rs.getString("reminderID") + ". " + rs.getString("reminder")+"```").queue();
                }

            } else if (event.getMessage().getContentRaw().equalsIgnoreCase("delete all")) {
                db.deleteAllReminders(conn, username);

            } else if (event.getMessage().getContentRaw().toLowerCase().startsWith("delete")) {
                int reminderID = Integer.parseInt(event.getMessage().getContentRaw().substring("delete".length()).trim());
                db.deleteReminder(conn, username, reminderID);

            } else if (event.getMessage().getContentRaw().toLowerCase().startsWith("reminder")) {
                String reminderMessage = event.getMessage().getContentRaw().substring("reminder".length()).trim();
                // Don't add to db if reminder message empty
                if (reminderMessage.length() != 0) {
                    db.addReminder(conn, username, reminderMessage);
                } else {
                    System.out.println("Empty message not allowed.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}