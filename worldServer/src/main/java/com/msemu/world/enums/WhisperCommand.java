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

package com.msemu.world.enums;

import lombok.Getter;

/**
 * Created by Weber on 2018/5/12.
 */
public enum WhisperCommand {
    Location(0x1),
    Location_Request(0x1 | 0x4),
    Location_Result(0x1 | 0x8),
    Whisper(0x2),
    Whisper_Request(0x2 | 0x4),
    Whisper_Result(0x2 | 0x8),
    Whisper_Receive(0x2 | 0x10),
    FarmWhisper(0x3),
    Request(0x4),
    Result(0x8),
    Receive(0x10),
    Blocked(0x20),
    Location_F(0x40),
    Location_F_Request(0x40 | 0x4),
    Location_F_Result(0x40 | 0x8),

    Manager(0x80),

    NONE(0xFF)
    ;
    @Getter
    private int value;

    WhisperCommand(int value) {
        this.value = value;
    }

    public static WhisperCommand getByValue(int value) {
        for (WhisperCommand cmd : values()) {
            if (cmd.getValue() == value)
                return cmd;
        }
        return NONE;
    }
}
