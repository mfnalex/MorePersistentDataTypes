/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * If you need help or have any suggestions, feel free to join my Discord and head to #programming-help:
 *
 * Discord: https://discord.jeff-media.com/
 *
 * If you find this library helpful or if you're using it one of your paid plugins, please consider leaving a donation
 * to support the further development of this project :)
 *
 * Donations: https://paypal.me/mfnalex
 */

package com.jeff_media.morepersistentdatatypes;

import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;

/**
 * Custom {@link PersistentDataType}s for versions 1.18.1 and later
 */
@SuppressWarnings("unused")
public interface DataType_1_18_1 {
    /**
     * {@link PersistentDataType} for {@link PlayerProfile}s
     */
    PersistentDataType<byte[], PlayerProfile> PLAYER_PROFILE = new ConfigurationSerializableDataType<>(PlayerProfile.class);
    /**
     * {@link PersistentDataType} for {@link PlayerProfile} arrays
     */
    PersistentDataType<byte[], PlayerProfile[]> PLAYER_PROFILE_ARRAY = new ConfigurationSerializableArrayDataType<>(PlayerProfile[].class);
}
