package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class BestScores {
	public int[] bestScore = new int[3];
	public final int level;
	
	public BestScores(int level) {
		this.level = level;
		searchFile();
		}
	public int[] getBestScore() {return bestScore;}
	
	//Check if the file exists, else create it
	private void searchFile() {
		File tmpDir = new File("bestScores/BestScore_" + level + ".txt");
		if (!tmpDir.exists())
			createFile();
		else {
			retriveScores(tmpDir);
		}
	}
	// Create File
	private void createFile() {
		try {
			PrintWriter writer = new PrintWriter(new File("bestScores/BestScore_" + level + ".txt"));
			writer.println("BestScores: ************Level:" + level + "************");
			writer.close();
		}catch (FileNotFoundException e) {
			System.err.println("problema a escrever o ficheiro");
		}
	}
	
	public int getTopOne() {
		return bestScore[0];
	}
	
	public int getLevel() {
		return level;
	}
	
	//Read File and return Best Scores, so that we don't lose the bestScores even after closing the game :D.
	private void retriveScores(File file) {
		int[] array = new int[3];
			try {
			Scanner scanner = new Scanner(file);
			String title_line = scanner.nextLine();
			int i = 0;
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" ");
				array[i] = Integer.parseInt(line[1]);
				i++;
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("problema a escrever o ficheiro");
		}
		bestScore = array;
	}

	//Register BestScores
	public void setBestScore(int score) {
		for(int i = 0; i < bestScore.length; i++) {
			if(bestScore[i] == 0) {
				bestScore[i] = score;
				firstSort();
				createFileWithScores();	
				return;
			}
			if(bestScore[bestScore.length-i-1] < score && bestScore[i+1] != 0) {
					bestScore[bestScore.length-i-1] = score;
					Arrays.sort(bestScore);
					createFileWithScores();	
					return;
			}

		}
	}
	private void firstSort() {
		for(int i = 0; i < bestScore.length-1; i++) {
			if(bestScore[i] > bestScore[i+1] || bestScore[i] == 0) {
				int box = bestScore[i];
				bestScore[i] = bestScore[i+1];
				bestScore[i+1] = box;
			}
		}
	}
	private void createFileWithScores() {
		try {
			PrintWriter writer = new PrintWriter(new File("bestScores/BestScore_" + level + ".txt"));
			writer.println("BestScores: ************Level:" + level + "************");
			for(int i = 0; i < bestScore.length; i++) {
				if(bestScore[i] != 0)
					writer.println( i+1 + "-º " + bestScore[i]);
			}
			writer.close();
		}catch (FileNotFoundException e) {
			System.err.println("problema a escrever o ficheiro");
		}
	}
	
	public static void main(String[] args) {
//		BestScores bS = new BestScores(4);
		//test1 No BEstScores
//		bS.searchFile();
		//test2 WithBestScores
//		bS.setBestScore(40);
//		bS.setBestScore(15);
// 		bS.setBestScore(50);
//		bS.setBestScore(30);
//		bS.setBestScore(10);
//		bS.searchFile();
//		int[] test = bS.getBestScore();
//		for(int i = 0; i < test.length; i++)
//			System.out.println(test[i]);
		//test3 With file with Scores
//		bS.searchFile();
//		int[] test = bS.getBestScore();
//		for(int i = 0; i < test.length; i++)
//			System.out.println(test[i]);
	}

}
