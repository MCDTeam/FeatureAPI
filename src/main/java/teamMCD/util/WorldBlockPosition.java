package teamUnknown.immersion.core.utils;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 *
 */
public class WorldBlockPosition extends BlockPosition {

    private World _world;

    public World getWorld(){
        return this._world;
    }

    public WorldBlockPosition(World world, int x, int y, int z){
        super(x, y, z);
        this._world = world;
    }

    public WorldBlockPosition(World world, BlockPosition position){
        super(position.getX(), position.getY(), position.getZ());
        this._world = world;
    }

    public WorldBlockPosition createWorldBlockPosition(BlockPosition position){
        return new WorldBlockPosition(this._world, position);
    }

    /*
    *   BlockPosition Adapter
    */

    @Override
    public WorldBlockPosition direction(ForgeDirection direction) {
        return this.createWorldBlockPosition(super.direction(direction));
    }

    @Override
    public WorldBlockPosition direction(ForgeDirection direction, int count){
        return this.createWorldBlockPosition(super.direction(direction, count));
    }

    @Override
    public WorldBlockPosition top(){
        return this.createWorldBlockPosition(super.top());
    }

    @Override
    public WorldBlockPosition bottom(){
        return this.createWorldBlockPosition(super.bottom());
    }

    @Override
    public WorldBlockPosition north(){
        return this.createWorldBlockPosition(super.north());
    }

    @Override
    public WorldBlockPosition west(){
        return this.createWorldBlockPosition(super.west());
    }

    @Override
    public WorldBlockPosition south(){
        return this.createWorldBlockPosition(super.south());
    }

    @Override
    public WorldBlockPosition east(){
        return this.createWorldBlockPosition(super.east());
    }

    public WorldBlockPosition topWorldBlock(int x, int z){
        return this.createWorldBlockPosition(super.topWorldBlock(this._world, x, z));
    }

    public WorldBlockPosition topTerrainBlock(int x, int z){
        return this.createWorldBlockPosition(super.topTerrainBlock(this._world, x, z));
    }

    public Block getBlock(){
        return super.getBlock(this._world);
    }

    public boolean setBlock(Block block){
        return super.setBlock(this._world, block);
    }

    public boolean setBlock(Block block, int meta){
        return super.setBlock(this._world, block, meta);
    }

    public boolean isAir() {
        return super.isAir(this._world);
    }

    public boolean is(Block block) {
        return super.is(this._world, block);
    }
}
