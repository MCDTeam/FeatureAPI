package cf.mcdteam.featureAPI.network.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.io.*;

import cf.mcdteam.featureAPI.network.PacketBase;

public abstract class FeaturePacketBase extends PacketBase {

    private ByteArrayOutputStream arrayout;
    private DataOutputStream dataout;
    public DataInputStream datain;

    public FeaturePacketBase() {

        arrayout = new ByteArrayOutputStream();
        dataout = new DataOutputStream(arrayout);
    }

    public FeaturePacketBase(byte[] data) {

        datain = new DataInputStream(new ByteArrayInputStream(data));
    }

    public FeaturePacketBase addString(String theString) {

        try {
            dataout.writeUTF(theString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addInt(int theInteger) {

        try {
            dataout.writeInt(theInteger);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addBool(boolean theBoolean) {

        try {
            dataout.writeBoolean(theBoolean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addByte(byte theByte) {

        try {
            dataout.writeByte(theByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addByte(int theByte) {

        return addByte((byte) theByte);
    }

    public FeaturePacketBase addShort(short theShort) {

        try {
            dataout.writeShort(theShort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addShort(int theShort) {

        return addShort((short) theShort);
    }

    public FeaturePacketBase addByteArray(byte theByteArray[]) {

        try {
            dataout.write(theByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addFloat(float theFloat) {

        try {
            dataout.writeFloat(theFloat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addItemStack(ItemStack theStack) {

        try {
            writeItemStack(theStack);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FeaturePacketBase addCoords(TileEntity theTile) {

        addInt(theTile.xCoord);
        addInt(theTile.yCoord);
        return addInt(theTile.zCoord);
    }

    public FeaturePacketBase addCoords(int x, int y, int z) {

        addInt(x);
        addInt(y);
        return addInt(z);
    }

    public String getString() {

        try {
            return datain.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getInt() {

        try {
            return datain.readInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean getBool() {

        try {
            return datain.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte getByte() {

        try {
            return datain.readByte();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public short getShort() {

        try {
            return datain.readShort();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void getByteArray(byte theByteArray[]) {

        try {
            datain.readFully(theByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getFloat() {

        try {
            return datain.readFloat();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ItemStack getItemStack() {

        try {
            return readItemStack();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] getCoords() {

        return new int[]{getInt(), getInt(), getInt()};
    }

    private void writeItemStack(ItemStack theStack) throws IOException {

        if (theStack == null) {
            addShort(-1);
        } else {
            addShort(Item.getIdFromItem(theStack.getItem()));
            addByte(theStack.stackSize);
            writeNBT(theStack.stackTagCompound);
        }
    }

    public ItemStack readItemStack() throws IOException {

        ItemStack stack = null;
        short itemID = getShort();

        if (itemID >= 0) {
            byte stackSize = getByte();
            short damage = getShort();
            stack = new ItemStack(Item.getItemById(itemID), stackSize, damage);
            stack.stackTagCompound = readNBT();
        }

        return stack;
    }

    public void writeNBT(NBTTagCompound nbt) throws IOException {

        if (nbt == null) {
            addShort(-1);
        } else {
            byte[] abyte = CompressedStreamTools.compress(nbt);
            addShort((short) abyte.length);
            addByteArray(abyte);
        }
    }

    public NBTTagCompound readNBT() throws IOException {

        short nbtLength = getShort();

        if (nbtLength < 0) {
            return null;
        } else {
            byte[] abyte = new byte[nbtLength];
            getByteArray(abyte);
            return CompressedStreamTools.func_152457_a(abyte, new NBTSizeTracker(2097152L));
        }
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

        buffer.writeBytes(arrayout.toByteArray());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

        datain = new DataInputStream(new ByteArrayInputStream(buffer.array()));
        try {
            datain.skipBytes(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleClientSide(EntityPlayer player) {

        handlePacket(player, false);
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

        handlePacket(player, true);
    }

    public abstract void handlePacket(EntityPlayer player, boolean isServer);

}
