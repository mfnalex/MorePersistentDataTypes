package com.jeff_media.morepersistentdatatypes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Notepad {

    {
        PersistentDataContainer pdc = null;
        NamespacedKey key = null;

        List<ItemStack> map = pdc.get(key, DataType.asList(DataType.ITEM_STACK));
    }
}
