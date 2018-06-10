package com.tools.utilities;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Samson Yerraguntla
 *
 */
public class Application {
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		// adding the block to the blockChain
		blockChain.add(new Block("Hi, I am first block", "0"));
		blockChain.get(0).mineBlock(difficulty);
		blockChain.add(new Block("Hey this is Second", blockChain.get(blockChain.size() - 1).hash));
		blockChain.get(1).mineBlock(difficulty);
		blockChain.add(new Block("This is third", blockChain.get(blockChain.size() - 1).hash));
		blockChain.get(2).mineBlock(difficulty);

		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println(blockChainJson);
	}

	/**
	 * Checks if the blockchain is valid or not.
	 * 
	 * @return True if blockchain is valid, Otherwise False.
	 */
	public static boolean isBlockChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		for (int i = 1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i - 1);
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes are not equal");
				return false;
			}

			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hashes are not equal");
				return false;
			}
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("Block isnt mined");
				return false;
			}
		}
		return true;
	}

}
