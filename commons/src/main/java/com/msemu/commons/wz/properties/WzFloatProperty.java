/*
 * MIT License
 *
 * Copyright (c) 2018 msemu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.msemu.commons.wz.properties;

import com.msemu.commons.wz.WzImageProperty;
import com.msemu.commons.wz.WzObjectType;
import com.msemu.commons.wz.WzPropertyType;

/**
 * Created by Weber on 2018/4/20.
 */
public class WzFloatProperty extends WzImageProperty {

    private float value;

    public WzFloatProperty(String name) {
        super(name);
    }

    @Override
    public WzPropertyType propType() {
        return WzPropertyType.Float;
    }

    @Override
    public WzObjectType type() {
        return WzObjectType.Property;
    }

    @Override
    public int getInt() {
        return (int) value;
    }

    @Override
    public short getShort() {
        return (short) value;
    }

    @Override
    public float getFloat() {
        return value;
    }

    @Override
    public double getDouble() {
        return (double) value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String getString() {
        return toString();
    }

    @Override
    public void setValue(Object value) {
        this.value = (float) value;
    }
}
