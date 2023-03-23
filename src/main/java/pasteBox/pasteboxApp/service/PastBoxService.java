package pasteBox.pasteboxApp.service;


import com.pasteBox.pasteboxApp.api.request.PasteBoxRequest;
import com.pasteBox.pasteboxApp.api.response.*;

import java.util.List;

public interface PastBoxService {

	PasteBoxResponse getByHash(String hash);
	List<PasteBoxResponse> getFirstPublicPasteboxes();

	PasteBoxUrlResponse create(PasteBoxRequest request);
}