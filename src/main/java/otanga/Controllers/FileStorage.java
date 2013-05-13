package otanga.Controllers;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileStat;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

import java.nio.ByteBuffer;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.security.MessageDigest;

public class FileStorage {
    // Get the file service
    private final static FileService fileService = FileServiceFactory.getFileService();

    private final static String _bucketName = "otangaimages";

    private FileStorage() {}

    /**
     * @deprecated use {@link otanga.Controllers.FileWriter} instead.
     */
    @Deprecated
    public static String storeImage(byte[] fileContent, String contentType, java.io.PrintWriter responseWriter){

        String key = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "!" + contentType.replace('/','!');

        responseWriter.println();
        responseWriter.println("\t[FileStorage.storeImage] Bucket: " + _bucketName);
        responseWriter.println("\t[FileStorage.storeImage] Key: " + key);
        responseWriter.println("\t[FileStorage.storeImage] ContentType: " + contentType);
        responseWriter.println("\t[FileStorage.storeImage] Length: " + fileContent.length + " bytes");

        GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
            .setBucket(_bucketName)
            .setKey(key)
            .setMimeType(contentType)
            .setAcl("project-private");

        try {
            // responseWriter.println("\t[FileStorage.storeImage] DefaultGsBucketName: " + fileService.getDefaultGsBucketName());

            AppEngineFile writableFile = fileService.createNewGSFile(optionsBuilder.build());

            FileWriteChannel writeChannel = fileService.openWriteChannel(writableFile, true);

            ByteBuffer byteBuffer = ByteBuffer.allocate(fileContent.length);
            byteBuffer.put(fileContent);
            byteBuffer.rewind();

            int writeResult = writeChannel.write(byteBuffer, "UTF-8");

            responseWriter.println("\t[FileStorage.storeImage] WriteResult: " + writeResult);
            responseWriter.println();

            writeChannel.closeFinally();

        } catch (IOException e) {
            key = null;
            responseWriter.println("\t[FileStorage.storeImage] IOException: " + e.getMessage());
            e.printStackTrace(responseWriter);
            responseWriter.println();

            e.printStackTrace();
        }

        return key;
    }

    public static String retrieveImage(String imageKey, java.io.PrintWriter responseWriter)
    {
        if (imageKey == null || imageKey.length() == 0)
            throw new IllegalArgumentException("imageKey");

        String output = null;

        String filename = "/gs/" +  _bucketName + "/" +  imageKey;

        String contentType = imageKey.substring(imageKey.indexOf('!') + 1).replace('!','/');

        MessageDigest fileHash = null;
        try {
            fileHash = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (fileHash == null)
            return "(FileHashError)";

        AppEngineFile readableFile = new AppEngineFile(filename);
        try {
            FileStat stat = fileService.stat(readableFile);
            output = stat.getLength().toString() + " bytes";

            responseWriter.println();
            responseWriter.println("\t[FileStorage.retrieveImage] FileName: " + stat.getFilename());
            responseWriter.println("\t[FileStorage.retrieveImage] ContentType: " + contentType);
            responseWriter.println();

            FileReadChannel readChannel = fileService.openReadChannel(readableFile, false);

            ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
            int count;
            while ((count = readChannel.read(buffer)) > 0)
            {
                buffer.rewind();

                byte[] byteArray = new byte[count];
                buffer.get(byteArray, 0, count);
                fileHash.update(byteArray);

                /*
                if (contentType.toLowerCase().startsWith("text/"))
                {
                    byte[] byteArray = new byte[count];
                    buffer.get(byteArray, 0, count);
                    responseWriter.print(new String(byteArray));
                }
                else
                {
                    for (int i = 0; i < count; i++)
                    {
                        responseWriter.print(("0" + Integer.toHexString(buffer.get() & 0xff)).substring(0, 2) + " ");
                    }
                    responseWriter.println();
                }
                */

                buffer.clear();
            }

            responseWriter.print("\t[FileStorage.retrieveImage] SHA-1 Digest: " );
            byte[] hash = fileHash.digest();
            for (int i=0; i < hash.length; i++) {
                responseWriter.print( Integer.toString( ( hash[i] & 0xff ) + 0x100, 16).substring(1) );
            }
            responseWriter.println();

            responseWriter.println();

            readChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
            output = "(IOException)";
        }

        return output;
    }
}
