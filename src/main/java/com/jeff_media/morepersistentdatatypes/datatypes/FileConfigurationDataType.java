/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.jeff_media.morepersistentdatatypes.datatypes;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import lombok.NonNull;

public class FileConfigurationDataType implements PersistentDataType<String, FileConfiguration> {
    @NonNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NonNull
    @Override
    public Class<FileConfiguration> getComplexType() {
        return FileConfiguration.class;
    }

    @NonNull
    @Override
    public String toPrimitive(@NonNull final FileConfiguration fileConfiguration, @NonNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        return fileConfiguration.saveToString();
    }

    @NonNull
    @Override
    public FileConfiguration fromPrimitive(@NonNull final String s, @NonNull final PersistentDataAdapterContext persistentDataAdapterContext) {
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
