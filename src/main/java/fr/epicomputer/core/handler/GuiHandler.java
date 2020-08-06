package fr.epicomputer.core.handler;

import fr.epicomputer.core.container.ComputerCaseContainer;
import fr.epicomputer.core.gui.GuiContenairComputerCase;
import fr.epicomputer.core.tiles.TileEntityComputerCase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
 
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(te instanceof TileEntityComputerCase) {
            return new ComputerCaseContainer((TileEntityComputerCase)te, player.inventory);
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
