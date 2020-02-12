package test1.random;

import java.util.Random;

//乱数の生成クラス

public class RandomNumber {
	public static int createRamdomNumber() {
		//インスタンス化
		Random random = new Random();
		//0~9999までの数を一つ生成する
		int randomValue = random.nextInt(10000);
		System.out.println(randomValue);
		return randomValue;
	}
	public static void main(String[] args) {
		createRamdomNumber();
	}
}
