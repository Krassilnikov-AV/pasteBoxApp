package pasteBox.pasteboxApp.repository;

import com.pasteBox.pasteboxApp.exception.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * \* @author AleKras on 18.03.2023
 * \* Description:
 * \*
 */
@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository {

	private final Map<String, PasteBoxEntity> vault = new ConcurrentHashMap<>();

	@Override
	public PasteBoxEntity getByHash(String hash) {
		PasteBoxEntity pasteBoxEntity = vault.get(hash);
		if (pasteBoxEntity == null) {
			throw new NotFoundEntityException("Pastebox not found with hash=" + hash);
		}
		return pasteBoxEntity;
	}

	@Override
	public List<PasteBoxEntity> getListOfPublicAndAlive(int amount) {
		LocalDateTime now = LocalDateTime.now();

		return vault.values().stream()
			.filter(PasteBoxEntity::isPublic)
			.filter(pasteBoxEntity -> pasteBoxEntity.getLifetime().isAfter(now))
			.sorted(Comparator.comparing(PasteBoxEntity::getId).reversed())
			.limit(amount)
			.collect(Collectors.toList());
	}

	@Override
	public void add(PasteBoxEntity pasteBoxEntity) {
		vault.put(pasteBoxEntity.getHash(), pasteBoxEntity);
	}
}