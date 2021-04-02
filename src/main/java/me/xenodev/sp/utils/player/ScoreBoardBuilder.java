package me.xenodev.sp.utils.player;

import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.file.TimeFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.StatsSQL;
import me.xenodev.sp.mysql.TimeSQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ScoreBoardBuilder {

    private static Team team;

    public static void setScoreboard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "main");
        obj.setDisplayName(Main.getInstance().scoreboarddisplay.replace("&", "§"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);
        //String amount = String.format("%.2f", Main.eco.getBalance(p));

        // - Rang
        // - Stats <Kills, Tode>
        // - Time
        // - Discord / Teamspeak / Webseite

        obj.getScore("§5").setScore(13);
        obj.getScore("§f§lRang").setScore(12);
        obj.getScore(updateTeam(board, "rang", user.getPrefix().replace("&", "§"), "", ChatColor.BLUE)).setScore(11);
        obj.getScore("§4").setScore(10);
        obj.getScore("§f§lStats").setScore(9);
        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
            obj.getScore(updateTeam(board, "kills", "  §8Kills:", "§e" + StatsFilebuilder.getKills(p), ChatColor.RED)).setScore(8);
            obj.getScore(updateTeam(board, "deaths", "  §8Tode:", "§e" + StatsFilebuilder.getDeaths(p), ChatColor.GREEN)).setScore(8);
            obj.getScore(updateTeam(board, "time", "§e" + TimeFilebuilder.changeTime(p), "", ChatColor.YELLOW)).setScore(4);
        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
            obj.getScore(updateTeam(board, "kills", "  §8Kills:", "§e" + StatsSQL.getKills(p.getUniqueId()), ChatColor.RED)).setScore(8);
            obj.getScore(updateTeam(board, "deaths", "  §8Tode:", "§e" + StatsSQL.getDeaths(p.getUniqueId()), ChatColor.GREEN)).setScore(8);
            obj.getScore(updateTeam(board, "time", "§e" + TimeSQL.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
        }
        obj.getScore("§3").setScore(6);
        obj.getScore("§f§lOnlinezeit").setScore(5);
        obj.getScore("§2").setScore(3);
        if(Main.getInstance().scoreboardinfo.equalsIgnoreCase("Discord")){
            obj.getScore("§f§lDiscord").setScore(2);
            obj.getScore(Main.getInstance().discord).setScore(1);
        }else if(Main.getInstance().scoreboardinfo.equalsIgnoreCase("Teamspeak")){
            obj.getScore("§f§lTeamspeak").setScore(2);
            obj.getScore(Main.getInstance().teamspeak).setScore(1);
        }else if(Main.getInstance().scoreboardinfo.equalsIgnoreCase("Website")){
            obj.getScore("§f§lWebseite").setScore(2);
            obj.getScore(Main.getInstance().website).setScore(1);
        }
        obj.getScore("§1").setScore(0);

        p.setScoreboard(board);
        setTabList(p);
    }

    public static void updateScoreboard(Player p) {
        Scoreboard board = p.getScoreboard();
        Objective obj = board.getObjective("main");

        PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);

        obj.getScore(updateTeam(board, "rang", user.getPrefix().replace("&", "§"), "", ChatColor.BLUE)).setScore(11);
        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
            obj.getScore(updateTeam(board, "kills", "  §8Kills:", "§e" + StatsFilebuilder.getKills(p), ChatColor.RED)).setScore(8);
            obj.getScore(updateTeam(board, "deaths", "  §8Tode:", "§e" + StatsFilebuilder.getDeaths(p), ChatColor.GREEN)).setScore(8);
            obj.getScore(updateTeam(board, "time", "§e" + TimeFilebuilder.changeTime(p), "", ChatColor.YELLOW)).setScore(4);
        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
            obj.getScore(updateTeam(board, "kills", "  §8Kills:", "§e" + StatsSQL.getKills(p.getUniqueId()), ChatColor.RED)).setScore(8);
            obj.getScore(updateTeam(board, "deaths", "  §8Tode:", "§e" + StatsSQL.getDeaths(p.getUniqueId()), ChatColor.GREEN)).setScore(8);
            obj.getScore(updateTeam(board, "time", "§e" + TimeSQL.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
        }

        setTabList(p);
    }

    public static Team getTeam(Scoreboard board, String Team, String prefix, String suffix) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);

        return team;

    }

    public static String updateTeam(Scoreboard board, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());

        return entry.toString();
    }

    public static void setTabList(final Player p) {
        for (final Player p2 : Bukkit.getOnlinePlayers()) {
            String pn = p2.getName();
            final String getGroups = getGroup(p2);
            pn = pn.substring(0, Math.min(pn.length(), 12));
            try {
                if (p.getScoreboard().getTeam(getGroups.toString()) == null) {
                    ScoreBoardBuilder.team = p.getScoreboard().registerNewTeam(getWeight(getGroups).toString());
                }
            }
            catch (Exception e1) {
                ScoreBoardBuilder.team = p.getScoreboard().getTeam(getWeight(getGroups).toString());
            }
            ScoreBoardBuilder.team.addPlayer((OfflinePlayer)p2);
            ScoreBoardBuilder.team.setPrefix(String.valueOf(String.valueOf(ChatColor.translateAlternateColorCodes('&', PermissionsEx.getPermissionManager().getUser(p2).getPrefix()))) + " §8| ");
        }
    }

    public static String getGroup(final Player p) {
        final PermissionUser user = PermissionsEx.getUser(p);
        final String[] allGroups = user.getGroupNames();
        final String group = allGroups[0];
        return group;
    }

    public static String getWeight(final String g) {
        final int w = PermissionsEx.getPermissionManager().getGroup(g).getWeight();
        String we = Integer.toString(w);
        we = String.valueOf(String.valueOf(we)) + "0000000";
        String wee = "";
        if (we.charAt(0) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(0) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(0) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(0) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(0) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(0) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(0) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(0) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(0) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(0) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(1) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(1) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(1) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(1) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(1) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(1) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(1) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(1) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(1) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(1) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(2) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(2) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(2) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(2) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(2) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(2) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(2) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(2) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(2) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(2) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(3) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(3) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(3) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(3) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(3) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(3) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(3) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(3) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(3) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(3) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        return wee;
    }

}
