package teamUnknown.immersion.core.utils;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 *
 */
public class BlockPosition {

    private int _x;
    private int _y;
    private int _z;

    public BlockPosition(int x, int y, int z){
        this._x = x;
        this._y = y;
        this._z = z;
    }

    public int getZ() {
        return this._z;
    }

    public int getY() {
        return this._y;
    }

    public int getX() {
        return this._x;
    }

    public BlockPosition direction(ForgeDirection direction) {
        return direction(direction, 1);
    }

    public BlockPosition direction(ForgeDirection direction, int count){
        return new BlockPosition(this._x + direction.offsetX * count, this._y + direction.offsetY * count, this._z + direction.offsetZ * count);
    }

    public BlockPosition top(){
        return this.direction(ForgeDirection.UP);
    }

    public BlockPosition bottom(){
        return this.direction(ForgeDirection.DOWN);
    }

    public BlockPosition north(){
        return this.direction(ForgeDirection.NORTH);
    }

    public BlockPosition west(){
        return this.direction(ForgeDirection.WEST);
    }

    public BlockPosition south(){
        return this.direction(ForgeDirection.SOUTH);
    }

    public BlockPosition east(){
        return this.direction(ForgeDirection.EAST);
    }

    public BlockPosition topWorldBlock(World world, int x, int z){
        int y = world.getTopSolidOrLiquidBlock(x, z);
        return new BlockPosition(x, y, z);
    }

    public BlockPosition topTerrainBlock(World world, int x, int z){
        int y = world.getHeightValue(x, z);
        return new BlockPosition(x, y, z);
    }

    public Block getBlock(World world){
        return world.getBlock(this._x, this._y, this._z);
    }

    public boolean setBlock(World world, Block block){
        return world.setBlock(this._x, this._y, this._z, block);
    }


    public boolean setBlock(World world, Block block, int meta) {
        return world.setBlock(this._x, this._y, this._z, block, meta, 3);
    }


    public boolean isAir(World world) {
        return world.isAirBlock(this._x, this._y, this._z);
    }

    public boolean is(World world, Block block) {
        return this.getBlock(world) == block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockPosition that = (BlockPosition) o;

        if (_x != that._x) return false;
        if (_y != that._y) return false;
        if (_z != that._z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _x;
        result = 31 * result + _y;
        result = 31 * result + _z;
        return result;
    }


    //TODO the as much world function as possible


}
