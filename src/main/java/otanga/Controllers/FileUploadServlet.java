package otanga.Controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.io.*;
import org.apache.commons.io.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

/**
 * Created with IntelliJ IDEA.
 * User: Polys
 * Date: 10/05/13
 * Time: 15:16
 */
public class FileUploadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setStatus(200); // Status: OK
            resp.setContentType("text/plain");

            ServletFileUpload fileUpload = new ServletFileUpload();
            FileItemIterator items = fileUpload.getItemIterator(req);
            while (items.hasNext()) {
                FileItemStream item = items.next();

                // List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            //for (FileItem item : items) {

                if (item.isFormField())
                    continue;

                String fileName = FilenameUtils.getName(item.getName());
                InputStream fileContent = item.openStream(); // item.getInputStream();

                String fileKey = null;

                int fileSize = fileContent.available();

                resp.getWriter().println("FileName: " + fileName);
                resp.getWriter().println("FileSize: " + fileSize);
                resp.getWriter().println("ContentType: " + item.getContentType());
                resp.getWriter().println();

                if (fileSize > 0)
                {
                    try{
                        byte[] data = new byte[fileSize];
                        if (fileContent.read(data) != fileSize)
                            throw new IOException();

                        resp.getWriter().println("Read " +  data.length + " bytes into the buffer.");

                        resp.getWriter().println();
                        resp.getWriter().println("Trying to store the file...");
                        fileKey = FileStorage.storeImage(data, item.getContentType());
                        resp.getWriter().println("FileStorage.storeImage returned: " +  fileKey);
                    }
                    finally {
                        fileContent.close();
                    }
                }
                else
                {
                    resp.getWriter().println("The file is empty!");
                }

                if (fileKey != null)
                {
                    //TODO: Respond appropriately!
                    // resp.setStatus(200); // Status: OK
                    // resp.setContentType("text/plain");
                    // resp.getWriter().println("Uploaded file '" + fileName + "' with key: " + fileKey);

                    resp.getWriter().println();
                    resp.getWriter().println("Trying to retrieve the file...");

                    //test reading back the file
                    String result = FileStorage.retrieveImage(fileKey, resp.getWriter());
                    resp.getWriter().println("FileStorage.retrieveImage returned: " +  result);
                }
                else
                {
                    resp.getWriter().println("The file key is null!");

                    // resp.setStatus(406); // Status: Not Acceptable
                    // resp.setStatus(400); // Status: Bad Request
                }
            }
        } catch (FileUploadException e) {
            throw new IOException("Cannot parse multipart request.", e);
        }
    }
}
