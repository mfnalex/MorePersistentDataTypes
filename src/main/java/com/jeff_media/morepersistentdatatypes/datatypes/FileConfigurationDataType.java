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

package com.jeff_media.morepersistentdatatypes.datatypes;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PersistentDataType} for {@link FileConfiguration}s
 */
public class FileConfigurationDataType implements PersistentDataType<String, FileConfiguration> {
    @NotNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<FileConfiguration> getComplexType() {
        return FileConfiguration.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull final FileConfiguration fileConfiguration, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        return fileConfiguration.saveToString();
    }

    @NotNull
    @Override
    public FileConfiguration fromPrimitive(@NotNull final String s, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            final YamlConfiguration yaml = new YamlConfiguration();
            yaml.loadFromString(s);
            return yaml;
        } catch (final InvalidConfigurationException e) {
            e.printStackTrace();
            return new YamlConfiguration();
        }
    }
}
