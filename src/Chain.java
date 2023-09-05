import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class Chain {

    public static Scanner keyboard;
    private ArrayList<Block> blockchain;
    private int difficulty;

    public Chain(Scanner scanner,int difficulty) {
        this(new ArrayList<>(), difficulty, scanner);
    }

    public Chain(ArrayList<Block> blockchain, int difficulty, Scanner scanner) {
        this.blockchain = blockchain;
        this.difficulty = difficulty;
        keyboard = scanner;
    }

    public void Start() {

        int index = 1;
        String previousHashValue = "0"; // 第一个block初始为0

        do {
            System.out.printf("Please enter block %d Date: \n", index);

            blockchain.add(new Block(keyboard.nextLine(), previousHashValue));
            System.out.printf("Trying to Mine block %d\n", index++);

            blockchain.get(index - 2).mineBlock(difficulty); // 挖矿
            // 获取前一个block的hash值
            previousHashValue = blockchain.get(index - 2).getHash();

        } while (isContinue());

        System.out.println("\nBlockchain is Valid: " + isChainValid());
        System.out.println("\nThe block chain: ");
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }

    /**
     * Check chainValid
     * 检查chain的合法性
     * @return
     */
    public boolean isChainValid() {

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

    public boolean isContinue() {

        String flag;
        do {
            System.out.println("If you need to continue, please enter [Yes], otherwise enter [No].");
            flag = keyboard.nextLine();
        } while (!flag.equalsIgnoreCase("yes") && !flag.equalsIgnoreCase("no"));

       return flag.equalsIgnoreCase("yes");
    }
}
