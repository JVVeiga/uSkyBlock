package us.talabrek.ultimateskyblock.island.task;

import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import us.talabrek.ultimateskyblock.PluginConfig;
import us.talabrek.ultimateskyblock.api.async.Callback;
import us.talabrek.ultimateskyblock.async.IncrementalRunnable;
import us.talabrek.ultimateskyblock.handler.WorldEditHandler;
import us.talabrek.ultimateskyblock.util.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Incremental task for snapshotting chunks.
 */
public class ChunkSnapShotTask extends IncrementalRunnable {
    private final Location location;
    private final List<BlockVector2> chunks;
    private final List<ChunkSnapshot> snapshots = new ArrayList<>();

    public ChunkSnapShotTask(Scheduler scheduler, PluginConfig config, Location location, ProtectedRegion region, final Callback<List<ChunkSnapshot>> callback) {
        super(scheduler, config, callback);
        this.location = location;
        if (region != null) {
            chunks = new ArrayList<>(WorldEditHandler.getChunks(new CuboidRegion(region.getMinimumPoint(), region.getMaximumPoint())));
        } else {
            chunks = new ArrayList<>();
        }
        callback.setState(snapshots);
    }

    @Override
    protected boolean execute() {
        while (!chunks.isEmpty()) {
            BlockVector2 chunkVector = chunks.remove(0);
            Chunk chunk = location.getWorld().getChunkAt(chunkVector.getBlockX(), chunkVector.getBlockZ());
            if (!chunk.isLoaded()) {
                chunk.load();
            }
            snapshots.add(chunk.getChunkSnapshot(false, false, false));
            if (!tick()) {
                break;
            }
        }
        return chunks.isEmpty();
    }
}
