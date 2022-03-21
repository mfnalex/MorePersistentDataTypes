package com.jeff_media.morepersistentdatatypes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main() {
        PersistentDataContainer pdc = null;
        ArrayList<ItemStack> myList = new ArrayList<ItemStack>();
        pdc.set(new NamespacedKey("asd","asd"),
                new DataCollection<>(ArrayList.class,DataType.ITEM_STACK),myList);

    }
}
