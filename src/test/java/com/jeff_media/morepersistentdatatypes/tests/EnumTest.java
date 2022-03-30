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

package com.jeff_media.morepersistentdatatypes.tests;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.MorePersistentDataTypesUnitTest;
import com.jeff_media.morepersistentdatatypes.TestData;
import org.junit.jupiter.api.Test;

import java.nio.file.StandardCopyOption;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTest extends MorePersistentDataTypesUnitTest {

    @Test
    void testEnum() {
        final StandardCopyOption original = StandardCopyOption.ATOMIC_MOVE;
        pdc.set(key, DataType.asEnum(StandardCopyOption.class), original);
        assertEquals(original, pdc.get(key, DataType.asEnum(StandardCopyOption.class)));
    }

    @Test
    void testEnumMap() {
        pdc.set(key, DataType.asEnumMap(StandardCopyOption.class,DataType.STRING), TestData.ENUM_MAP);
        final EnumMap<StandardCopyOption, String> savedMap = pdc.get(key, DataType.asEnumMap(StandardCopyOption.class, DataType.STRING));
        assertEquals(TestData.ENUM_MAP,savedMap);
    }

    @Test
    void testEnumSet() {
        pdc.set(key, DataType.asEnumSet(StandardCopyOption.class), TestData.ENUM_SET);
        assertEquals(TestData.ENUM_SET, pdc.get(key, DataType.asEnumSet(StandardCopyOption.class)));
    }
}
