import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Stats.*;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sx.blah.discord.Discord4J;
import sx.blah.discord.DiscordClient;
import sx.blah.discord.handle.IListener;
import sx.blah.discord.handle.impl.events.InviteReceivedEvent;
import sx.blah.discord.handle.impl.events.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Channel;
import sx.blah.discord.handle.obj.Invite;
import sx.blah.discord.handle.obj.Message;
import sx.blah.discord.handle.obj.PrivateChannel;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.Presences;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Zane on 12/21/2015.
 *
 */
public class Bot {
    public static Set<String> swear_words;
    public static Set<String> illegal_words;
    public static void main(String[] args) {
        String swear_array[] = {"fuck","faggot","moist","asshole","nigger","cunt","cumbucket", "mens rights","triggered","fuckin","slut","panflute"};
        String google_swear_array[] = {"4r5e", "5h1t", "5hit", "a55", "anal", "anus", "ar5e", "arrse", "arse", "ass", "ass-fucker", "asses", "assfucker", "assfukka", "asshole", "assholes", "asswhole", "a_s_s", "b!tch", "b00bs", "b17ch", "b1tch", "ballbag", "balls", "ballsack", "bastard", "beastial", "beastiality", "bellend", "bestial", "bestiality", "bi+ch", "biatch", "bitch", "bitcher", "bitchers", "bitches", "bitchin", "bitching", "bloody", "blow job", "blowjob", "blowjobs", "boiolas", "bollock", "bollok", "boner", "boob", "boobs", "booobs", "boooobs", "booooobs", "booooooobs", "breasts", "buceta", "bugger", "bum", "bunny fucker", "butt", "butthole", "buttmuch", "buttplug", "c0ck", "c0cksucker", "carpet muncher", "cawk", "chink", "cipa", "cl1t", "clit", "clitoris", "clits", "cnut", "cock", "cock-sucker", "cockface", "cockhead", "cockmunch", "cockmuncher", "cocks", "cocksuck", "cocksucked", "cocksucker", "cocksucking", "cocksucks", "cocksuka", "cocksukka", "cok", "cokmuncher", "coksucka", "coon", "cox", "crap", "cum", "cummer", "cumming", "cums", "cumshot", "cunilingus", "cunillingus", "cunnilingus", "cunt", "cuntlick", "cuntlicker", "cuntlicking", "cunts", "cyalis", "cyberfuc", "cyberfuck", "cyberfucked", "cyberfucker", "cyberfuckers", "cyberfucking", "d1ck", "damn", "dick", "dickhead", "dildo", "dildos", "dink", "dinks", "dirsa", "dlck", "dog-fucker", "doggin", "dogging", "donkeyribber", "doosh", "duche", "dyke", "ejaculate", "ejaculated", "ejaculates", "ejaculating", "ejaculatings", "ejaculation", "ejakulate", "f u c k", "f u c k e r", "f4nny", "fag", "fagging", "faggitt", "faggot", "faggs", "fagot", "fagots", "fags", "fanny", "fannyflaps", "fannyfucker", "fanyy", "fatass", "fcuk", "fcuker", "fcuking", "feck", "fecker", "felching", "fellate", "fellatio", "fingerfuck", "fingerfucked", "fingerfucker", "fingerfuckers", "fingerfucking", "fingerfucks", "fistfuck", "fistfucked", "fistfucker", "fistfuckers", "fistfucking", "fistfuckings", "fistfucks", "flange", "fook", "fooker", "fuck", "fucka", "fucked", "fucker", "fuckers", "fuckhead", "fuckheads", "fuckin", "fucking", "fuckings", "fuckingshitmotherfucker", "fuckme", "fucks", "fuckwhit", "fuckwit", "fudge packer", "fudgepacker", "fuk", "fuker", "fukker", "fukkin", "fuks", "fukwhit", "fukwit", "fux", "fux0r", "f_u_c_k", "gangbang", "gangbanged", "gangbangs", "gaylord", "gaysex", "goatse", "God", "god-dam", "god-damned", "goddamn", "goddamned", "hardcoresex", "hell", "heshe", "hoar", "hoare", "hoer", "homo", "hore", "horniest", "horny", "hotsex", "jack-off", "jackoff", "jap", "jerk-off", "jism", "jiz", "jizm", "jizz", "kawk", "knob", "knobead", "knobed", "knobend", "knobhead", "knobjocky", "knobjokey", "kock", "kondum", "kondums", "kum", "kummer", "kumming", "kums", "kunilingus", "l3i+ch", "l3itch", "labia", "lmfao", "lust", "lusting", "m0f0", "m0fo", "m45terbate", "ma5terb8", "ma5terbate", "masochist", "master-bate", "masterb8", "masterbat*", "masterbat3", "masterbate", "masterbation", "masterbations", "masturbate", "mo-fo", "mof0", "mofo", "mothafuck", "mothafucka", "mothafuckas", "mothafuckaz", "mothafucked", "mothafucker", "mothafuckers", "mothafuckin", "mothafucking", "mothafuckings", "mothafucks", "mother fucker", "motherfuck", "motherfucked", "motherfucker", "motherfuckers", "motherfuckin", "motherfucking", "motherfuckings", "motherfuckka", "motherfucks", "muff", "mutha", "muthafecker", "muthafuckker", "muther", "mutherfucker", "n1gga", "n1gger", "nazi", "nigg3r", "nigg4h", "nigga", "niggah", "niggas", "niggaz", "nigger", "niggers", "nob", "nob jokey", "nobhead", "nobjocky", "nobjokey", "numbnuts", "nutsack", "orgasim", "orgasims", "orgasm", "orgasms", "p0rn", "pawn", "pecker", "penis", "penisfucker", "phonesex", "phuck", "phuk", "phuked", "phuking", "phukked", "phukking", "phuks", "phuq", "pigfucker", "pimpis", "piss", "pissed", "pisser", "pissers", "pisses", "pissflaps", "pissin", "pissing", "pissoff", "poop", "porn", "porno", "pornography", "pornos", "prick", "pricks", "pron", "pube", "pusse", "pussi", "pussies", "pussy", "pussys", "rectum", "retard", "rimjaw", "rimming", "s hit", "s.o.b.", "sadist", "schlong", "screwing", "scroat", "scrote", "scrotum", "semen", "sex", "sh!+", "sh!t", "sh1t", "shag", "shagger", "shaggin", "shagging", "shemale", "shi+", "shit", "shitdick", "shite", "shited", "shitey", "shitfuck", "shitfull", "shithead", "shiting", "shitings", "shits", "shitted", "shitter", "shitters", "shitting", "shittings", "shitty", "skank", "slut", "sluts", "smegma", "smut", "snatch", "son-of-a-bitch", "spac", "spunk", "s_h_i_t", "t1tt1e5", "t1tties", "teets", "teez", "testical", "testicle", "tit", "titfuck", "tits", "titt", "tittie5", "tittiefucker", "titties", "tittyfuck", "tittywank", "titwank", "tosser", "turd", "tw4t", "twat", "twathead", "twatty", "twunt", "twunter", "v14gra", "v1gra", "vagina", "viagra", "vulva", "w00se", "wang", "wank", "wanker", "wanky", "whoar", "whore", "willies", "willy", "xrated", "xxx"};
        List list = new ArrayList(Arrays.asList(swear_array));
        list.addAll(Arrays.asList(google_swear_array));
//        list.to
        String illegal_array[] ={"child porn"};
        swear_words = new HashSet<String>(list);;
        illegal_words = new HashSet(Arrays.asList(illegal_array));

        try {
            DiscordClient.get().login("jackofhertz@hotmail.com" /* username */, "password1" /* password */);

            DiscordClient.get().getDispatcher().registerListener(new IListener<InviteReceivedEvent>() {
                @Override public void receive(InviteReceivedEvent event) {
                    Invite invite = event.getInvite();
                    try {
                        Invite.InviteResponse response = invite.details();
                        event.getMessage().reply(String.format("you've invited me to join #%s in the %s guild!", response.getChannelName(), response.getGuildName()));
                        invite.accept();
                        DiscordClient.get().sendMessage(String.format("Hello, #%s and the \\\"%s\\\" guild! I was invited by %s!",
                                response.getChannelName(), response.getGuildName(), event.getMessage().getAuthor()),
                                response.getChannelID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            DiscordClient.get().getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
                @Override public void receive(MessageReceivedEvent messageReceivedEvent) {
                    Message m = messageReceivedEvent.getMessage();
                    if (m.getContent().startsWith(".meme")
                            || m.getContent().startsWith(".nicememe")) {
                        new MessageBuilder().appendContent("MEMES REQUESTED:", MessageBuilder.Styles.UNDERLINE_BOLD_ITALICS)
                                .appendContent(" http://niceme.me/").withChannel(messageReceivedEvent.getMessage().getChannel())
                                .build();
                    } else if (m.getContent().startsWith(".clear")) {
                        Channel c = DiscordClient.get().getChannelByID(m.getChannel().getID());
                        if (null != c) {
                            c.getMessages().stream().filter(message -> message.getAuthor().getID()
                                    .equalsIgnoreCase(DiscordClient.get().getOurUser().getID())).forEach(message -> {
                                try {
                                    Discord4J.logger.debug("Attempting deletion of message {} by \"{}\" ({})", message.getID(), message.getAuthor().getName(), message.getContent());
                                    DiscordClient.get().deleteMessage(message.getID(), message.getChannel().getID());
                                } catch (IOException e) {
                                    Discord4J.logger.error("Couldn't delete message {} ({}).", message.getID(), e.getMessage());
                                }
                            });
                        }
                    } else if (m.getContent().startsWith(".name ")) {
                        String s = m.getContent().split(" ", 2)[1];
                        try {
                            DiscordClient.get().changeAccountInfo(s, "", "");
                            m.reply("is this better?");
                        } catch (ParseException | IOException e) {
                            e.printStackTrace();
                        }
                    } else if(m.getContent().startsWith(".pm")) {
                        try {
                            PrivateChannel channel = DiscordClient.get().getOrCreatePMChannel(m.getAuthor());
                            new MessageBuilder().withChannel(channel).withContent("SUP DUDE").build();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }  else if(m.getAuthor().getName().equals("marpants") && m.getContent().contains("i love zane")) {
                        try {
                            PrivateChannel channel = DiscordClient.get().getOrCreatePMChannel(m.getAuthor());
                            new MessageBuilder().withChannel(channel).withContent("Zane loves you too! :heart:").build();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (m.getContent().startsWith(".presence")) {
                        DiscordClient.get().updatePresence(!DiscordClient.get().getOurUser().getPresence().equals(Presences.IDLE),
                                DiscordClient.get().getOurUser().getGameID());
                    } else if(m.getContent().startsWith("!hello")) {
                        try {
                            m.reply("Hello " + m.getAuthor().getName() + ", you enormous salty faggot!");
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(m.getContent().startsWith("!help")) {
                        try {
                            m.reply("\n!hello - Greeting" +
                                    "\n!help - Show this help" +
                                    "\n!linkme <searchterm> - Return first Google result"+
                                    "\n!summoner <summonername> - Return first League of Legends stats for a summoner");
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(m.getContent().startsWith("!linkme")) {
                        try {
                            String s = m.getContent();

                            int pos = s.indexOf(" ");
                            if(pos == -1){
                                m.reply("What the fuck should I link retard?");
                            } else {
                                s = s.substring(pos,s.length());
                                if(s.length()>0){
                                    m.reply(searchGoogle(s));
                                    //m.getAuthor().get
                                }
                            }


//                                m.reply("Link: "+link);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    } else if(m.getContent().startsWith("!image")) {
                        try {
                            String s = m.getContent();

                            int pos = s.indexOf(" ");
                            if(pos == -1){
                                m.reply("What the fuck should I get a picture of retard?");
                            } else {
                                s = s.substring(pos,s.length());
                                if(s.length()>0){
                                    m.reply(searchGoogleImages(s));
                                    //m.getAuthor().get
                                }
                            }


//                                m.reply("Link: "+link);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                     else if(swear_words.contains(m.getContent())){
                        try{
                            String reply = "Watch your fucking language "+m.getAuthor();
                            DiscordClient.get().sendMessage(reply,m.getChannel().getID());
                        } catch (IOException | ParseException e){
                            e.printStackTrace();
                        }
                    } else if(m.getContent().contains("starwars")|| m.getContent().contains("star wars")) {
                        try {
                            String reply = "Remember when Matt spoiled that?";
                            DiscordClient.get().sendMessage(reply, m.getChannel().getID());
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                        else if(m.getContent().startsWith("!summoner")) {
                            try {
                                String s = m.getContent();

                                int pos = s.indexOf(" ");
                                if(pos == -1){
                                    //System.out.println(getSummonerId("seriena"));
                                } else {
                                    s = s.substring(pos,s.length());
                                    SummonerStats stats = getSummonerStats(getSummonerId(s.trim()));
//                                    PlayerStatSummary rankedSolo = null;
//                                    PlayerStatSummary aram = null;
                                    String reply = "";
                                    String ranked = "";
                                    for (PlayerStatSummary summary:stats.getPlayerStatSummaries()) {
                                        if (summary.getPlayerStatSummaryType().startsWith("Ranked")) {
                                            ranked += "" +
                                                    "    __" + summary.getPlayerStatSummaryType() + "__\n" +
                                                    "    **Wins: **" + summary.getWins() + "\n" +
                                                    "    **Champion Kills: **" + summary.getAggregatedStats().getTotalChampionKills() + "\n" +
                                                    "    **Assists: **" + summary.getAggregatedStats().getTotalAssists() + "\n" +
                                                    "    **Total Minions: **" + summary.getAggregatedStats().getTotalMinionKills() + "\n\n";
                                        } else {
                                            reply += "" +
                                                    "    __" + summary.getPlayerStatSummaryType() + "__\n" +
                                                    "    **Wins: **" + summary.getWins() + "\n" +
                                                    "    **Champion Kills: **" + summary.getAggregatedStats().getTotalChampionKills() + "\n" +
                                                    "    **Assists: **" + summary.getAggregatedStats().getTotalAssists() + "\n" +
                                                    "    **Total Minions: **" + summary.getAggregatedStats().getTotalMinionKills() + "\n\n";
                                        }
                                    }
                                        reply="Stats for: "+s+"\n"+"**RANKED**\n--------------------------\n"+ranked+"**UNRANKED**\n--------------------------\n"+reply;
//                                    dto.Stats.ChampionStats[] champs = getSummonerStatsRanked(getSummonerId(s.trim())).getChampions();
//                                    long mostPlayedId = -1;
//                                    long mostSessions = -1;
//                                    for (dto.Stats.ChampionStats cs:champs) {
//                                        if(cs.getStats().getTotalSessionsPlayed() >mostSessions){
//                                            mostPlayedId = cs.getId();
//                                            mostSessions = cs.getStats().getTotalSessionsPlayed();
//                                        }
//
//                                    }
//                                    String reply = ""+s+"\n";
//                                    if(rankedSolo !=null){
//                                        reply+="__Ranked solo 5x5 ("+rankedSolo.getPlayerStatSummaryType()+")__\n" +
//                                                "**Wins: **"+rankedSolo.getWins()+"\n" +
//                                                "**Champion Kills: **"+rankedSolo.getAggregatedStats().getTotalChampionKills()+"\n" +
//                                                "**Total Minions: **"+rankedSolo.getAggregatedStats().getTotalMinionKills()+"\n\n";
//                                    }
//                                    if(aram!=null){
//                                        reply+="__ARAM ("+aram.getPlayerStatSummaryType()+")__\n" +
//                                                "**Wins: **"+aram.getWins()+"\n" +
//                                                "**Champion Kills: **"+aram.getAggregatedStats().getTotalChampionKills()+"\n" +
//                                                "**Total Assists: **"+aram.getAggregatedStats().getTotalAssists() +"\n\n";
//                                                //"**Most played Champion(Ranked): "+mostPlayedId
//                                    }
                                    m.reply(reply);



                                }
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                    } else if(m.getContent().startsWith("!remindme")) {

                        try {
                            String reply = "Remember when Matt spoiled that?";
                            DiscordClient.get().sendMessage(reply, m.getChannel().getID());
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }

                } else if(m.getContent().contains("nope")) {
                    try {
                        DiscordClient.get().sendMessage("https://youtu.be/gvdf5n-zI14", m.getChannel().getID());
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                    }
                    else if(m.getContent().contains("milk")){
                        try{
                            String reply = "Matt can't own milk because he spoils it";
                            DiscordClient.get().sendMessage(reply,m.getChannel().getID());
                        } catch (IOException | ParseException e){
                            e.printStackTrace();
                        }
                    }

                else if(m.getContent().startsWith("!remindme")){
                    try{
                        String s = m.getContent();

                        int pos = s.indexOf(" ");
                        if(pos == -1){
                            m.reply("I'll remind you never then.");
                        } else {
                            s = s.substring(pos,s.length());
                            if(s.length()>0){
                                new Reminder(Integer.parseInt(s));
                                m.reply("Timer set for: "+s);

                            }
                        }
                    } catch (IOException | ParseException e){
                        e.printStackTrace();
                    }
                } else if (m.getContent().startsWith(".game")) {
                        String game = m.getContent().length() > 6 ? m.getContent().substring(6) : "null";
                        Long id;
                        try {
                            id = Long.parseLong(game);
                        } catch (NumberFormatException e) {
                            id = DiscordClient.get().getGameIDByGame(game).orElse(null);
                        }
                        DiscordClient.get().getOurUser().getPresence();
                        DiscordClient.get().updatePresence(DiscordClient.get().getOurUser().getPresence().equals(Presences.IDLE),
                                Optional.ofNullable(id));
                    }
                }
            });

            DiscordClient.get().getDispatcher().registerListener(new IListener<MessageDeleteEvent>() {
                @Override public void receive(MessageDeleteEvent event) {
                    try {
                        event.getMessage().reply("you said, \\\"" + event.getMessage().getContent() + "\\\"");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String searchGoogle(String s) throws UnsupportedEncodingException,MalformedURLException,IOException{
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = s;
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        // Show title and URL of 1st result.
//        System.out.println(results.getResponseData().getResults().get(0).getTitle());
        return results.getResponseData().getResults().get(0).getUrl();
    }
    public static String searchGoogleImages(String s) throws UnsupportedEncodingException,MalformedURLException,IOException{
        String google = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
        String search = s;
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        // Show title and URL of 1st result.
//        System.out.println(results.getResponseData().getResults().get(0).getTitle());
        return results.getResponseData().getResults().get(0).getUrl();
    }
    public static long getSummonerId(String s)throws UnsupportedEncodingException,MalformedURLException,IOException{
        String charset = "UTF-8";
        String encString =  URLEncoder.encode(s, charset).replace("+", "%20");
        URL url = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"+encString+"?api_key=3b5a6835-023a-4532-8153-fd2ef067d29f" );
        System.out.println(url.toString());

        Reader reader = new InputStreamReader(url.openStream(), charset);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(reader).getAsJsonObject();
        JsonElement glossary = obj.get(s);
//        System.out.println(reader.);
//        SummonersResults summoners = new Gson().fromJson(reader, SummonersResults.class);
        Summoner summoner = new Gson().fromJson(obj.get(s),Summoner.class);
        return summoner.getId();
    }
    public static SummonerStats getSummonerStats(long s)throws UnsupportedEncodingException,MalformedURLException,IOException{
        String charset = "UTF-8";
        URL url = new URL("https://na.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/"+ URLEncoder.encode(""+s, charset)+"/summary?api_key=3b5a6835-023a-4532-8153-fd2ef067d29f" );
        Reader reader = new InputStreamReader(url.openStream(), charset);
        SummonerStats summonerStats = new Gson().fromJson(reader,SummonerStats.class);
        System.out.println(summonerStats.getPlayerStatSummaries().toString());
        return summonerStats;
    }
    public static RankedStats getSummonerStatsRanked(long s)throws UnsupportedEncodingException,MalformedURLException,IOException{
        String charset = "UTF-8";
        URL url = new URL("https://na.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/"+ URLEncoder.encode(""+s, charset)+"/ranked?api_key=3b5a6835-023a-4532-8153-fd2ef067d29f" );
        Reader reader = new InputStreamReader(url.openStream(), charset);
        RankedStats summonerStats = new Gson().fromJson(reader,RankedStats.class);

        return summonerStats;
    }
}

