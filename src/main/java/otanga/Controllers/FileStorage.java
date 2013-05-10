package otanga.Controllers;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 5/10/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;


import org.apache.commons.fileupload.*;

import java.io.IOException;
import java.util.UUID;

public class FileStorage {
    // Get the file service
    private final static FileService fileService = FileServiceFactory.getFileService();

    private FileStorage() {}

    public static UUID storeImage(byte[] fileContent, String contentType){
        UUID key = UUID.randomUUID();
        GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
            .setBucket("images")
            .setKey(key.toString())
            .setAcl("private");

        // Create your object

        try {
            AppEngineFile writableFile = fileService.createNewGSFile(optionsBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return key;
    }
}
