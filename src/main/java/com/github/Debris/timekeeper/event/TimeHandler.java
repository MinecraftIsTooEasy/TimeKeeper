package com.github.Debris.timekeeper.event;

import com.github.Debris.timekeeper.util.WorldUtil;
import moddedmite.rustedironcore.api.util.LogUtil;
import net.minecraft.ChatMessageComponent;
import net.minecraft.NBTTagCompound;
import net.minecraft.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

public class TimeHandler {
    private TimeHandler() {
    }

    private static final TimeHandler Instance = new TimeHandler();

    public static TimeHandler getInstance() {
        return Instance;
    }

    private static final String NBT_KEY = "timekeeper:time";

    private Long OVERWORLD_TIME = null;
    private final MinecraftServer server = MinecraftServer.getServer();
    private static final Logger LOGGER = LogUtil.getLogger();

    public void onPlayerLoggedIn(ServerPlayer player) {
        int playerCount = WorldUtil.getPlayerCount(this.server);

        if (playerCount == 1 && OVERWORLD_TIME != null) {
            WorldUtil.setOverWorldTime(this.server, OVERWORLD_TIME);
            String info = "[TimeKeeper]已将时间重置到最后一次无人时";
            player.sendChatToPlayer(ChatMessageComponent.createFromText(info));
            LOGGER.info("Set time {} for OverWorld", OVERWORLD_TIME);
            OVERWORLD_TIME = null;
        }
    }

    public void onPlayerLoggedOut(ServerPlayer player) {
        int playerCount = WorldUtil.getPlayerCount(this.server);

        if (playerCount == 0) {
            OVERWORLD_TIME = WorldUtil.getOverWorldTime(this.server);
        }
    }

    public void writeNBT(NBTTagCompound nbt) {
        if (OVERWORLD_TIME != null) nbt.setLong(NBT_KEY, OVERWORLD_TIME);
    }

    public void readNBT(NBTTagCompound nbt) {
        if (nbt.hasKey(NBT_KEY)) OVERWORLD_TIME = nbt.getLong(NBT_KEY);
    }
}
