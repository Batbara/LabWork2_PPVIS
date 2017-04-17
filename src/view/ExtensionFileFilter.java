package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * Created by Batbara on 15.04.2017.
 */
public class ExtensionFileFilter extends FileFilter {
    private final String description;
    private final String extension;
    public ExtensionFileFilter(){
        this.description = "Student Data Base (*.sdb)";
        this.extension = "SDB".toLowerCase();
    }
    public String getDescription() {
        return description;
    }
    public boolean accept(File file){
        if(file.isDirectory()){
            return true;
        }
        else {
            String path = file.getAbsolutePath().toLowerCase();
            if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                return true;
            }
        }
        return false;
    }
}
