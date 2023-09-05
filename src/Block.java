import util.StringUtil;
import java.util.Date;

public class Block {

    private String hash;
    private String previousHash;
    private String date;
    private long timeStamp;
    private int nonce;

    public Block(String date, String previousHash) {
        this.date = date;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public  String calculateHash() {
        return StringUtil.applySha256(
            previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + date
        );
    }

    public void mineBlock(int difficulty) {

        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
