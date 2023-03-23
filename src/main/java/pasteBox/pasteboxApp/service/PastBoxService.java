package pasteBox.pasteboxApp.service;


import pasteBox.pasteboxApp.api.request.PasteBoxRequest;
import pasteBox.pasteboxApp.api.response.*;

import java.util.List;

public interface PastBoxService {

	PasteBoxResponse getByHash(String hash);
	List<PasteBoxResponse> getFirstPublicPasteboxes();

	PasteBoxUrlResponse create(PasteBoxRequest request);
}