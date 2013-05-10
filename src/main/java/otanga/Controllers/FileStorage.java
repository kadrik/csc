package otanga.Controllers;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 5/10/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
import com.google.appengine.api.files.*;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;
import sun.security.ssl.Debug;

import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.UUID;
import java.io.BufferedReader;

public class FileStorage {
    // Get the file service
    private final static FileService fileService = FileServiceFactory.getFileService();

    private final static String _bucketName = "OtangaImages";

    private FileStorage() {}

    public static UUID storeImage(byte[] fileContent, String contentType){
        UUID key = UUID.randomUUID();
        GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
            .setBucket(_bucketName)
            .setKey(key.toString().replace("-", "").toLowerCase())
            .setMimeType(contentType)
            .setAcl("private");

        try {
            AppEngineFile writableFile = fileService.createNewGSFile(optionsBuilder.build());

            FileWriteChannel writeChannel = fileService.openWriteChannel(writableFile, true);

            ByteBuffer byteBuffer = ByteBuffer.allocate(fileContent.length);
            byteBuffer.put(fileContent);

            writeChannel.write(byteBuffer, "UTF-8");

            writeChannel.closeFinally();


        } catch (IOException e) {
            e.printStackTrace();
            key = null;
        }

        return key;
    }

    public static String retrieveImage(UUID imageKey)
    {
        if (imageKey == null)
            throw new IllegalArgumentException("imageKey");

        String output = null;

        String filename = "/gs/" +  _bucketName + "/" +  imageKey.toString().replace("-", "").toLowerCase();

        AppEngineFile readableFile = new AppEngineFile(filename);
        try {
            FileStat stat = fileService.stat(readableFile);
            output = stat.getLength().toString();

            //FileReadChannel readChannel = fileService.openReadChannel(readableFile, false);
            // // TODO: read the file contents here
            //readChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
            output = e.getMessage();
        }

        return output;
    }
}
