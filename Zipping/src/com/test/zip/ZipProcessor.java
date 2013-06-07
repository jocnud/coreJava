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
 * @author shahbazk
 * 
 */
public class ZipProcessor {

	String path;

	public void process() {

		System.out.println("inside processor");

		String files;

		String outZIP = "";

		System.out.println("THIS IS TE PATH" + path);

		File folder = new File(path);

		String outfileName = "";

		/* File output = new File("/data/" + outfileName); */

		File[] listOfFiles = folder.listFiles();

		System.out.println("size of the file is --- " + listOfFiles.length);

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();

				outfileName = listOfFiles[i].getName();

				outZIP = outfileName.substring(0, outfileName.length() - 4);
				System.out.println("ZIP FILE NAME ----------------" + outZIP);

				System.out.println("filenames -------------------" + files);
			}
		}

		File output = new File("/data/" + outZIP + ".ZIP");

		try {

			if (listOfFiles.length == 0) {
				System.out.println("NO FILES FOUND");
			} else {
				for (int i = 0; i < listOfFiles.length; i++) {
					zipFile(listOfFiles, output);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
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

				System.out.println("currentfile -------"
						+ currentFile.getName());
				if (!currentFile.isDirectory()) {

					if (currentFile.getName().endsWith(".xml")
							|| currentFile.getName().endsWith(".XML")) {
						System.out.println("ADDING ENTRY To ZIP "
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
						currentFile.delete();
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
