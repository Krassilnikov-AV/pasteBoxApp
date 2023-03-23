package pasteBox.pasteboxApp.repository;

import java.util.List;

/**
 * \* @author AleKras on 14.03.2023
 * \* Description:
 * \*
 */
public interface PasteBoxRepository {
	PasteBoxEntity getByHash(String hash);

	List<PasteBoxEntity> getListOfPublicAndAlive(int amount);

	void add(PasteBoxEntity PasteBoxEntity);
}