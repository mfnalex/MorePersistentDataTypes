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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimitiveArrayTest extends MorePersistentDataTypesUnitTest {

    @Test
    void testFloatArray() {
        pdc.set(key, DataType.FLOAT_ARRAY, TestData.FLOAT_PRIMITIVE_ARRAY);
        final float[] stored = pdc.get(key, DataType.FLOAT_ARRAY);
        assertArrayEquals(TestData.FLOAT_PRIMITIVE_ARRAY, pdc.get(key, DataType.FLOAT_ARRAY));
    }
}
