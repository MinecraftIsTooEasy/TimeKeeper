package com.github.Debris.timekeeper.util;

import net.minecraft.WorldServer;
import net.minecraft.server.MinecraftServer;

public class WorldUtil {
    public static int getPlayerCount(MinecraftServer server) {
        return server.getCurrentPlayerCount();
    }

    public static int getPlayerCount(WorldServer worldServer) {
        return worldServer.playerEntities.size();
    }

    public static long getTime(WorldServer worldServer) {
        return worldServer.getTotalWorldTime();
    }

    public static void setTime(WorldServer worldServer, long time) {
        worldServer.setTotalWorldTime(time, true);
    }

    public static WorldServer getOverWorld(MinecraftServer server) {
        return server.getEntityWorld().getAsWorldServer();
    }

    public static long getOverWorldTime(MinecraftServer server) {
        return getTime(getOverWorld(server));
    }

    public static void setOverWorldTime(MinecraftServer server, long time) {
        setTime(getOverWorld(server), time);
    }
}
