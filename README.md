## Basic BlockChain-Java

- [From](https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa)

### Block

#### 属性

  - 数字指纹(Hash)

  - Date

  - previousHash

#### 数字指纹生成

  - 使用`SHA256`

    ```java
      import java.security.MessageDigest;
      
      public class StringUtil {
        //Applies Sha256 to a string and returns the result. 
        public static String applySha256(String input){		
          
          try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
            //Applies sha256 to our input, 
            byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
              String hex = Integer.toHexString(0xff & hash[i]);
              if(hex.length() == 1) hexString.append('0');
              hexString.append(hex);
            }
            return hexString.toString();
          }
          catch(Exception e) {
            throw new RuntimeException(e);
          }

        }	
      }
    ```

### Chain

- 使用`ArraysList`管理chain

- 使用GSON将`Object`转化为`Json`格式

### 检查chain的合法性

1. 当前`Block`的`Hash`值是通过计算得到的

2. 当前`Block`的`Hash`值是等于前一个`Block`的`Hash`

### 模拟（挖矿）🧺

- 通过`difficulty`进行难度控制

- 在`Block`增加`nonce`属性表示随机数（将`nonce`添加到生成Hash函数中）

- 大致流程

  - 通过`difficulty`为长度创建`String`类型的字符串，且字符串的形式为`.replace('\0', '0')`

  - 寻找每次生成的`Hash`值与目标相等，则为成功🏅️

  - 若不成功将`nonce++`，继续生成`Hash`
