package fr.epicorp.epicomputer.handler;

import fr.epicorp.epicomputer.client.gui.GuiContenairComputerCase;
import fr.epicorp.epicomputer.container.ContainerComputerCase;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
 
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(te instanceof TileEntityComputerCase) {
            return new ContainerComputerCase((TileEntityComputerCase)te, player.inventory);
        }
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(te instanceof TileEntityComputerCase) {
            return new GuiContenairComputerCase((TileEntityComputerCase)te, player.inventory);
        }
        return null;
    }

}
