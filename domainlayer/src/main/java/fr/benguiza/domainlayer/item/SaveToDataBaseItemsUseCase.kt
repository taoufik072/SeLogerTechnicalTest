package fr.benguiza.domainlayer.item
import fr.benguiza.commons.os.OpenWhenTesting

@OpenWhenTesting
class SaveToDataBaseItemsUseCase(private val localItemsRepository: LocalItemsRepository) {
    fun execute(items: List<Item>) = localItemsRepository.saveToDataBaseItems(items)
}