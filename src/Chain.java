import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Chain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static void main(String[] args) {

        blockchain.add(new Block("first block 1", "0"));
        System.out.println("Trying to Mine block 1...");
        blockchain.get(0).mineBlock(difficulty);
//
//        blockchain.add(new Block("second block 2",
//                blockchain.get(blockchain.size()-1).getHash()));
//        blockchain.add(new Block("third block 3",
//                blockchain.get(blockchain.size()-1).getHash()));

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("CurrentBlock hash not equals");
                return false;
            }

            if (!currentBlock.getHash().equals(previousBlock.getHash())) {
                System.out.println("previousBlock hash not equals");
                return false;
            }
        }

        return true;
    }
}
