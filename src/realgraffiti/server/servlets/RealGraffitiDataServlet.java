package realgraffiti.server.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class RealGraffitiDataServlet extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().write("this is get");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("data");
        
        if (blobKey == null) {
        	resp.sendRedirect("/ServerInfo?action=error");
        } else {
        	resp.sendRedirect("/ServerInfo?action=blobkey&blobkey=" + URLEncoder.encode(blobKey.toString(),"UTF-8"));
        }

	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.getWriter().write("this is put");
	}
}