package service;

import model.Block;

public interface BlockService {
	
	/**
	 * check if block is a valid block
	 */
	boolean checkValidBlock(Block block);
}
