package com.test.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static void main(String[] args) {

		// Directory path here
		String path = "/home/shabby/PARENT";

		String childfFolders;
		String childFiles;
		File parentFolder = new File(path);
		File[] listOfFiles = parentFolder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File output = new File("/home/shabby/PARENTOUTPUT/OUTPUT" + i
					+ ".zip");

			if (listOfFiles[i].isDirectory()) {
				childfFolders = listOfFiles[i].getName();
				System.out.println("----" + childfFolders);

				File[] childFilesList = listOfFiles[i].listFiles();
				try {
					zipFile(childFilesList, output);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void zipFile(final File[] files, final File targetZipFile)
			throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(targetZipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			byte[] buffer = new byte[2048];
			for (int i = 0; i < files.length; i++) {

				File currentFile = files[i];

				if (!currentFile.isDirectory()) {

					if (currentFile.getName().endsWith(".txt")
							|| currentFile.getName().endsWith(".TXT")) {
						System.out.println("        ----ADDING ENTRY To ZIP "
								+ currentFile.getName());
						ZipEntry entry = new ZipEntry(currentFile.getName());
						FileInputStream fis = new FileInputStream(currentFile);
						zos.putNextEntry(entry);
						int read = 0;
						while ((read = fis.read(buffer)) != -1) {
							zos.write(buffer, 0, read);
						}
						zos.closeEntry();
						fis.close();

					} else {
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
