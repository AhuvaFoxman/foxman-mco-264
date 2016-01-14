package fileRecursion;

import java.io.File;

public class FileRecursion {

	private StringBuilder build;
	private File filePath;

	public FileRecursion(File folderPath) {
		build = new StringBuilder();
		filePath = folderPath;

	}

	private void printFiles(File files, int number) throws InvalidFileException {
		if (files.listFiles() != null) {
			for (File file : files.listFiles()) {
				for (int i = 0; i < number; i++) {
					build.append("\t");
				}

				if (file.isDirectory()) {

					build.append("Folder Name: " + file.getName());
					build.append("\n");
					printFiles(file, (number + 1));

				} else {

					this.build.append("Name: " + file.getName() + " Size: "
							+ file.length() + " ");
					build.append("\n");

				}
			}
		}

	}

	public String getPrintFiles() throws InvalidFileException {
		printFiles(this.filePath, 0);
		return build.toString();
	}

	public static void main(String[] args) throws InvalidFileException {
		File file = new File("C:/Users/AFoxman/Documents");
		FileRecursion filer = new FileRecursion(file);
		try {
			System.out.println(filer.getPrintFiles());
		} catch (NullPointerException e) {
			System.out.println("Invalid File");
		}
	}

}
