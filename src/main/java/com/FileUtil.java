package com;

import java.io.File;

public class FileUtil {
	
	/**
	 * created boolean method deleteFiles for deleting files
	 * 
	 * @param contentsToDelete
	 * @return
	 */
	public static boolean deleteFiles(File contentsToDelete) {
		File[] allContents = contentsToDelete.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteFiles(file);
			}
		}
		return contentsToDelete.delete();
	}
}