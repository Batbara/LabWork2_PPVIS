package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * Created by Batbara on 15.04.2017.
 */
public class ExtensionFileFilter extends FileFilter {
    String description;
    String extension;
    public ExtensionFileFilter(String description, String extensions){
        this.description = description;
        this.extension = extensions.toLowerCase();
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
