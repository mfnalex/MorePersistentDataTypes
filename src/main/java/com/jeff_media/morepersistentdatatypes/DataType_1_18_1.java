/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of  MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
