package pasteBox.pasteboxApp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pasteBox.pasteboxApp.api.request.*;
import pasteBox.pasteboxApp.api.response.*;
import pasteBox.pasteboxApp.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@ConfigurationProperties(prefix = "app")

public class PasteBoxServiceImpl implements PastBoxService {

	private String host = "http://abc.ru";
	private int publicListSize=10;

	private final PasteBoxRepository repository;
	private AtomicInteger idGenerator = new AtomicInteger(0);

	@Override
	public PasteBoxResponse getByHash(String hash) {

		PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
		return new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
	}

	@Override
	public List<PasteBoxResponse> getFirstPublicPasteboxes() {

		List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);
		return list.stream().map(pasteBoxEntity ->
				new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
			.collect(Collectors.toList());
	}

	@Override
	public PasteBoxUrlResponse create(PasteBoxRequest request) {

		int hash = generateId();
		PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
		pasteBoxEntity.setData(request.getData());
		pasteBoxEntity.setId(hash);
		pasteBoxEntity.setHash(Integer.toHexString(hash));
		pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC_STATUS);
		pasteBoxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSounds()));
		repository.add(pasteBoxEntity);
		return
			new PasteBoxUrlResponse(host + "/" + pasteBoxEntity.getHash());
	}

	private int generateId() {
		return idGenerator.getAndIncrement();
	}
}