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

package com.jeff_media.morepersistentdatatypes.datatypes;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

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
