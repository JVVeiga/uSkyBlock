package us.talabrek.ultimateskyblock.command.island;

import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import us.talabrek.ultimateskyblock.command.admin.AbstractIslandInfoCommand;
import us.talabrek.ultimateskyblock.island.IslandInfo;
import us.talabrek.ultimateskyblock.player.PlayerInfo;
import us.talabrek.ultimateskyblock.uSkyBlock;

import static dk.lockfuglsang.minecraft.po.I18nUtil.marktr;

public class MobLimitCommand extends AbstractIslandInfoCommand {
    private final uSkyBlock plugin;

    @Inject
    public MobLimitCommand(@NotNull uSkyBlock plugin) {
        super("limits", "usb.island.limit", marktr("show the islands limits"));
        this.plugin = plugin;
    }

    @Override
    protected void doExecute(CommandSender sender, PlayerInfo playerInfo, IslandInfo islandInfo, String... args) {
        sender.sendMessage(plugin.getLimitLogic().getSummary(islandInfo).split("\n"));
    }
}
