package com.jeff_media.morepersistentdatatypes;

import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;

/**
 * Custom {@link PersistentDataType}s for versions 1.18.1 and later
 */
public interface DataType_1_18_1 {
    /**
     * DataType for {@link PlayerProfile}s
     */
    PersistentDataType<byte[], PlayerProfile> PLAYER_PROFILE = new ConfigurationSerializableDataType<>(PlayerProfile.class);
    /**
     * DataType for {@link PlayerProfile} arrays
     */
    PersistentDataType<byte[], PlayerProfile[]> PLAYER_PROFILE_ARRAY = new ConfigurationSerializableArrayDataType<>(PlayerProfile[].class);
}
