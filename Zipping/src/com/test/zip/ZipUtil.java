package com.test.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author shabby
 *
 */
public class ZipUtil {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * Parent Directory
		 */
		final String path = "/home/shabby/PARENT";

		/**
		 * Child Folders
		 */
		String childfFolders;
		/**
		 * Parent Folder
		 */
		File parentFolder = new File(path);
		/**
		 * List of files in parent folder.
		 * Here we will use isDirectory() method 
		 * to check if this is directory 
		 */
		final File[] listOfFiles = parentFolder.listFiles();

		
		/**
		 * Loop to traverse through the parent folder
		 */
		for (int i = 0; i < listOfFiles.length; i++) {
			/**
			 * The final output.zip file
			 */
			File output = new File("/home/shabby/PARENTOUTPUT/OUTPUT" + i
					+ ".zip");

			/**
			 * checking if it is a directory or not
			 */
			if (listOfFiles[i].isDirectory()) {
				/**
				 * getting the sub-directories
				 */
				childfFolders = listOfFiles[i].getName();
				System.out.println("----" + childfFolders);

				/**
				 * Files in the sub-directories
				 */
				File[] childFilesList = listOfFiles[i].listFiles();
				try {
					zipFile(childFilesList, output);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param files
	 * @param targetZipFile
	 * @throws IOException
	 */
	public static void zipFile(final File[] files, final File targetZipFile)
			throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(targetZipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			byte[] buffer = new byte[2048];
			for (int i = 0; i < files.length; i++) {

				File currentFile = files[i];

				/**
				 * checking for directory
				 */
				if (!currentFile.isDirectory()) {

					/**
					 * We are on,y zipping the .txt files but can be removed 
					 * according to need.
					 */
					if (currentFile.getName().endsWith(".txt")
							|| currentFile.getName().endsWith(".TXT")) {
						System.out.println("        ----ADDING ENTRY To ZIP "
								+ currentFile.getName());
						/**
						 * Adding the files to be zipped
						 */
						ZipEntry entry = new ZipEntry(currentFile.getName());
						FileInputStream fis = new FileInputStream(currentFile);
						zos.putNextEntry(entry);
						int read = 0;
						/**
						 * writing into the file
						 */
						while ((read = fis.read(buffer)) != -1) {
							zos.write(buffer, 0, read);
						}
						zos.closeEntry();
						fis.close();

					}
					/**
					 * If it is no .txt we will omit it. 
					 */
					else {
						
						System.out.println("something else found");
					}
				}
			}
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + e);
		}

	}

}
