package otanga.Controllers;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Polys
 * Date: 12/05/13
 * Time: 18:09
 */

public final class FileWriter extends OutputStream {

    private final static FileService _fileService = FileServiceFactory.getFileService();
    private final static String _bucketName = "otangaimages";
    private final String _fileKey;
    private final FileWriteChannel _writeChannel;
    private int _size;

    public FileWriter(String contentType) throws IOException
    {
        if (contentType == null || contentType.length() == 0)
            contentType = "application/octet-stream";

        _fileKey = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "!" + contentType.replace('/','!');

        GSFileOptions.GSFileOptionsBuilder optionsBuilder = new GSFileOptions.GSFileOptionsBuilder()
                .setBucket(_bucketName)
                .setKey(_fileKey)
                .setMimeType(contentType)
                .setAcl("private");

        try {
            AppEngineFile writableFile = _fileService.createNewGSFile(optionsBuilder.build());
            _writeChannel = _fileService.openWriteChannel(writableFile, true);
        } catch (IOException e) {
            throw e;
        }

        _size = 0;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] buffer = new byte[1];
        buffer[0] = (byte)b;
        _size += _writeChannel.write(ByteBuffer.wrap(buffer));
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        _size += _writeChannel.write(ByteBuffer.wrap(b, off, len));
    }

    @Override
    public void write(byte b[]) throws IOException {
        _size += _writeChannel.write(ByteBuffer.wrap(b));
    }

    @Override
    public void close() throws IOException {
        _writeChannel.closeFinally();
    }

    public int size() {
        return _size;
    }

    public String fileKey() {
        return _fileKey;
    }
}
