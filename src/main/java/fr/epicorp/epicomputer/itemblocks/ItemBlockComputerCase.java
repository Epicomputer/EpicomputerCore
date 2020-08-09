package fr.epicorp.epicomputer.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockComputerCase extends ItemBlock{

	public ItemBlockComputerCase() {
		super(BlocksMod.computer_case);
		setRegistryName(BlocksMod.computer_case.getRegistryName());
		
		// TODO Auto-generated constructor stub
	}
}
