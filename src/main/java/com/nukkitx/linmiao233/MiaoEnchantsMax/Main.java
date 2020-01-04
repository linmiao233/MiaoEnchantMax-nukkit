 package com.nukkitx.linmiao233.MiaoEnchantsMax;
 
 import cn.nukkit.Player;
 import cn.nukkit.Server;
 import cn.nukkit.command.Command;
 import cn.nukkit.command.CommandSender;
 import cn.nukkit.item.Item;
 import cn.nukkit.item.enchantment.Enchantment;
 import cn.nukkit.lang.TextContainer;
 import cn.nukkit.lang.TranslationContainer;
 import cn.nukkit.permission.Permission;
 import cn.nukkit.plugin.PluginBase;
 import cn.nukkit.utils.TextFormat;
 
 /**
 * @author linmiao233
 */
 
 public class Main
   extends PluginBase
 {
   private String format(Player player, String message) { return TextFormat.colorize('&', message)
       .replaceAll("%player%", player.getPlayer().getName())
       .replaceAll("%players%", String.valueOf(getServer().getOnlinePlayers().size()))
       .replaceAll("%motd%", getServer().getMotd()); }
 
   
   String prefix = "&e&l[Miao式附魔Max]&a ";
 
   
   public void onLoad() {
     Server.getInstance()
       .getPluginManager()
       .addPermission(new Permission("Miao.enchants", "允许玩家使用Miao附魔来附魔手上的东西.", "op"));
     
     getLogger().info("&e&l加载MiaoEnchantsMax中...");
   }
 
 
   
   public void onEnable() { getLogger().info("成功加载MiaoEnchantsMax."); }
 
   
   
   public boolean onCommand(CommandSender sender, Command command) {
     switch (command.getName()) {
       case "查看附魔表":
         if (!sender.hasPermission("Miao.enchants")) {
           return false;
         }
         sender.sendMessage(
             TextFormat.colorize(TextFormat.GREEN + "请打开网站 http://rrd.me/f7xzU 翻到最下面的基岩版ID开查看附魔ID！ 注意：支持输入中文/数字来附魔，不支持英文！"));
         break;
     } 
 
     
     return true; } public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
     Item item;
     Enchantment enchantment;
     int enchantLevel;
     int enchantId;
     Player player;
     switch (command.getName()) {
       case "喵附魔":
         if (!sender.hasPermission("Miao.enchants")) {
           return false;
         }
         if (args.length < 2) {
           sender.sendMessage(TextFormat.colorize(this.prefix + "%usage"));
           return false;
         } 
         player = sender.getServer().getPlayer(args[0]);
         if (player == null) {
           sender.sendMessage((TextContainer)new TranslationContainer(
                 
                 TextFormat.colorize(this.prefix + TextFormat.RED + "%commands.generic.player.notFound")));
           
           return true;
         } 
 
         
         try {
           enchantId = getIdByName(args[1]);
           enchantLevel = (args.length == 3) ? Integer.parseInt(args[2]) : 1;
         } catch (NumberFormatException e) {
           sender.sendMessage(TextFormat.colorize(this.prefix + TextFormat.RED + "not a number"));
           return true;
         } 
         enchantment = Enchantment.getEnchantment(enchantId);
         if (enchantment == null) {
           sender.sendMessage(
               TextFormat.colorize(this.prefix + TextFormat.RED + "%commands.enchant.notFound" + 
 
 
                 
                String.valueOf(enchantId)));
           return true;
         } 
         enchantment.setLevel(enchantLevel, false);
         item = player.getInventory().getItemInHand();
         if (item.getId() <= 0) {
           sender.sendMessage(
               TextFormat.colorize(this.prefix + TextFormat.RED + "%commands.enchant.noItem"));
           return true;
         } 
         item.addEnchantment(new Enchantment[] { enchantment });
         player.getInventory().setItemInHand(item);
         Command.broadcastCommandMessage(sender, 
             TextFormat.colorize(this.prefix + TextFormat.GREEN + "%commands.enchant.success"));
         return true;
     } 
    return true;
   }
   
   public int getIdByName(String value) throws NumberFormatException {
     switch (value) {
       case "保护":
         return 0;
       case "火焰保护":
         return 1;
       case "摔落保护":
        return 2;
       case "爆炸保护":
        return 3;
       case "弹射物保护":
        return 4;
       case "荆棘":
         return 5;
       case "水下呼吸":
        return 6;
       case "深海探索者":
         return 7;
       case "水下速掘":
         return 8;
       case "锋利":
         return 9;
       case "亡灵杀手":
         return 10;
       case "节肢杀手":
         return 11;
       case "击退":
         return 12;
       case "火焰附加":
         return 13;
       case "抢夺":
       return 14;
       case "效率":
         return 15;
       case "精准采集":
        return 16;
       case "耐久":
        return 17;
       case "时运":
        return 18;
       case "力量":
         return 19;
       case "冲击":
         return 20;
       case "火矢":
         return 21;
       case "无限":
        return 22;
       case "海之眷顾":
        return 23;
       case "饵钓":
        return 24;
     } 
    return Integer.parseInt(value);
   }
 }
