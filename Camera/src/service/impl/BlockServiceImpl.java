package service.impl;

import georegression.struct.point.Point3D_F32;
import model.Block;
import service.BlockService;
import service.MathCalService;

public class BlockServiceImpl implements BlockService{
	
	MathCalService mathCalService = new MathCalServiceImpl();

	@Override
	public boolean checkValidBlock(Block block) {
		Point3D_F32[] coordinates = block.getCoordinates();
		if (coordinates.length != 8) {
			return false;
		}else {
			return mathCalService.rectangularCheck(coordinates);
		}
	}

}
