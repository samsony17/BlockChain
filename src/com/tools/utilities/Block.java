package com.tools.utilities;

import java.util.Date;

/**
 * 
 * @author Samson Yerraguntla
 *
 */
public class Block {

	private String data;
	private long timeStamp;
	public String hash;
	public String previousHash;
	private int nonce;

	// Block Constructor
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	/**
	 * Calculates the hash of a block.
	 * @return hash of the block.
	 */
	public String calculateHash() {
		return StringUtilHelper.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);

	}
	
	/**
	 * Mines the block
	 * @param difficulty
	 */
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0,difficulty).equals(target))
		{
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined -- " + hash);
	}

}
