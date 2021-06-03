package controller;

import georegression.struct.point.Point3D_F32;
import model.Block;
import service.BlockService;
import service.impl.BlockServiceImpl;

public class BlockController {  
    private BlockService blockService;
    private Block blockModel;

    public BlockController(Point3D_F32 coordinates[]) {
        blockService = new BlockServiceImpl();
        if (coordinates.length == 8) {
            blockModel = new Block(coordinates);
            if (!blockService.checkValidBlock(blockModel)) {
                throw new RuntimeException("Invalid block.");
            }
        } else {
            throw new RuntimeException("Invalid number of points.");
        }
    }
    
    public Block getBlock() {
        return this.blockModel;
    }
}
