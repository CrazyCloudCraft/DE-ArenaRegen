package me.realized.de.arenaregen.nms.v1_18_R2_paper;

import me.realized.de.arenaregen.nms.NMS;
import net.minecraft.server.v1_18_R2.BlockPosition;
import net.minecraft.server.v1_18_R2.Chunk;
import net.minecraft.server.v1_18_R2.ChunkCoordIntPair;
import net.minecraft.server.v1_18_R2.EnumSkyBlock;
import net.minecraft.server.v1_18_R2.IBlockData;
import net.minecraft.server.v1_18_R2.World;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_18_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

public class NMSHandler implements NMS {

    @Override
    public void sendChunkUpdate(final Player player, final org.bukkit.Chunk chunk) {
        ((CraftPlayer) player).getHandle().chunkCoordIntPairQueue.add(new ChunkCoordIntPair(chunk.getX(), chunk.getZ()));
    }

    @Override
    public void setBlockFast(final Block bukkitBlock, final Material material, final int data) {
        final int x = bukkitBlock.getX(), y = bukkitBlock.getY(), z = bukkitBlock.getZ();
        final BlockPosition position = new BlockPosition(x, y, z);
        final Chunk chunk = ((CraftChunk) bukkitBlock.getChunk()).getHandle();
        final net.minecraft.server.v1_8_R3.Block block = CraftMagicNumbers.getBlock(material);
        final IBlockData blockData = block.fromLegacyData(data);
        chunk.a(position, blockData);
    }

    @Override
    public void updateLighting(Block bukkitBlock) {
        final int x = bukkitBlock.getX(), y = bukkitBlock.getY(), z = bukkitBlock.getZ();
        final BlockPosition position = new BlockPosition(x, y, z);
        final World world = ((CraftWorld) bukkitBlock.getWorld()).getHandle();
        world.updateLight(EnumSkyBlock.BLOCK, position);
        
    }
}
