package com.simplilearn.virtualkeyrepo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class VirtualKeyRepositories {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to LockedMe.com");
		System.out.println("Developer: Tamilvanan Shanmugam");
		System.out.println("");

		displayMenu();
		scanner.close();
	}

	/**
	 * This method is used to listout the menu and get the user input in cmd line
	 * 
	 * @param scanner
	 */
	private static void displayMenu() {

		System.out.println("Select the following numeric options to do the mentioned operations");
		System.out.println("1:List all files");
		System.out.println("2:Add/Delete/Search a file");
		System.out.println("Press other key to exit");
		getUserInput();
	}

	private static void getUserInput() {
		int inpNum = 0;

		try {
			inpNum = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException nfex) {
			System.exit(0);
		}

		String fileName = null;
		switch (inpNum) {
		case 1:
			System.out.println("Enter the file path:");
			fileName = getFileName(inpNum);
			listFiles(fileName);
			break;
		case 2:
			doFileOprtn(inpNum, fileName);
		default:
			System.exit(0);
		}
	}

	private static void doFileOprtn(int inpNum, String fileName) {
		int fileOpt = 0;

		System.out.println("1:Add a file");
		System.out.println("2:Delete a file");
		System.out.println("3:Search a file");
		System.out.println("Press other key to Navigate to main context menu");
		try {
			fileOpt = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException nfex) {
			displayMenu();
		}

		switch (fileOpt) {
		case 1:
			System.out.println("Provide the file name and path for adding a file:");
			fileName = getFileName(inpNum);
			addFile(fileName);
			break;
		case 2:
			System.out.println("Provide the file name and path for deleting the file:");
			fileName = getFileName(inpNum);
			deleteFile(fileName);
			break;
		case 3:
			System.out.println("Provide the file name and path to search the file:");
			fileName = getFileName(inpNum);
			searchFile(fileName);
			break;
		default:
			displayMenu();
		}

	}

	/**
	 * This method is used to get the File Name if the file is empty or not not
	 * valid , then display proper message
	 * 
	 * @param inpNum
	 * @return fileName
	 */
	private static String getFileName(int inpNum) {

		String fileName = scanner.nextLine();
		if (fileName != null && fileName.trim().length() > 0) {
			return fileName;
		} else {
			System.out.println(new StringBuilder("Invalid file name:").append(fileName).toString());
			displayMenu();
		}
		return fileName;
	}

	/**
	 * This method is used to list out files under the given specified directories
	 * 
	 * @param fileName
	 */
	private static void listFiles(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			String[] files = file.list();
			if (files != null && files.length > 0) {
				Collections.sort(Arrays.asList(files));
				for (String name : files) {
					System.out.println(name);
				}
			} else {
				System.out.println(
						new StringBuilder("There are NO files in the given path ").append(fileName).toString());
			}
		} else {
			System.out.println(new StringBuilder("Invalid directory/file path:").append(fileName).toString());
		}
		displayMenu();
	}

	private static void addFile(String fileName) {
		File file = new File(fileName);
		boolean oprFlg = false;
		try {
			oprFlg = file.createNewFile();
		} catch (IOException e) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT added ").toString());
		}
		if (oprFlg) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" added successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT added ").toString());
		}

		displayMenu();
	}

	/**
	 * This method is used to delete the files based on given the user input file
	 * name
	 * 
	 * @param fileName
	 */
	private static void deleteFile(String fileName) {
		File file = new File(fileName);
		boolean oprFlg = file.delete();
		if (oprFlg) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" deleted successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT deleted ").toString());
		}
		displayMenu();

	}

	/**
	 * This method is used to search and find based on the given the filename
	 * 
	 * @param fileName
	 */
	private static void searchFile(String fileName) {
		File file = new File(fileName);
		boolean oprFlg = file.exists();
		if (oprFlg) {
			System.out
					.println(new StringBuilder("File ").append(fileName).append(" searched successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT available ").toString());
		}
		displayMenu();
	}

}