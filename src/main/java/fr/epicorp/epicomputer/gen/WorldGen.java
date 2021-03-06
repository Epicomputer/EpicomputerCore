package fr.epicorp.epicomputer.gen;

import java.util.Random;

import fr.epicorp.epicomputer.init.BlocksMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator{

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
	    switch(world.provider.getDimension())

	    {

	        case 0:

	        generateSurface(world, random, chunkX * 16, chunkZ * 16);

	        break;
	    }
		
	}

	    private void generateSurface(World world, Random random, int x, int z) {
	    	
	    	//this.addOreSpawn('BlocÀGénérer', 'MétadataDuBloc', 'BlocÀRemplacer', world, random, x, z, 16, 16, 'TailleMaximaleDUnFilon', 'Rareté', 'PositionYMinimum', 'PositionYMaximum'); 
	    	this.addOreSpawn(BlocksMod.SILICIUM_ORE, BlocksMod.SILICIUM_ORE.getDefaultState(), Blocks.STONE, world, random, x, z, 16, 16, 5, 8, 20, 60); 
	    	
	    }	
	
	    public void addOreSpawn(Block block, IBlockState metadata, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {

	    	 

	        assert maxY > minY : "La position Y maximum doit être supérieure à la position Y minimum.";

	        assert maxX > 0 && maxX <= 16 : "X doit se trouver entre 0 et 16.";

	        assert minY > 0 : "La position Y minimum doit être supérieure à 0.";

	        assert maxY < 256 && maxY > 0 : "La position Y maximum doit se trouver entre 0 et 256.";

	        assert maxZ > 0 && maxZ <= 16 : "Z doit se trouver entre 0 et 16.";

	     

	        for(int i = 0; i < chancesToSpawn; i++)

	        {

	            int posY = random.nextInt(128);

	     

	            if((posY <= maxY) && (posY >= minY))

	            {

	     

	            (new WorldGenMinable( metadata, maxVeinSize)).generate(world, random, new BlockPos(blockXPos + random.nextInt(16), posY, blockZPos + random.nextInt(16)));

	            }

	        }

	    }
	    
}

