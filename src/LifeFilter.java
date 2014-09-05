import java.io.File;

import javax.swing.filechooser.FileFilter;

public class LifeFilter extends FileFilter {
	
	 public static String getFileExtension(File file) {
		 String fileName = file.getName();
		 if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			 return fileName.substring(fileName.lastIndexOf(".")+1);
		 else return "";
	 }

	@Override
	public boolean accept(File f) {
		String extension = getFileExtension(f);
        if (f.isDirectory() || (extension != null && extension.equals("life"))) 
            return true;
        return false;
	}

	@Override
	public String getDescription() {
		return "Only 'Game of Life' files";
	}

}
