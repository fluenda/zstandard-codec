/*
 * (C) Copyright 2016-2017 Fluenda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.fluenda.hadoop.io.compress.zstandard;

import org.apache.hadoop.io.compress.CompressorStream;

import java.io.IOException;
import java.io.OutputStream;

public final class OutputStreamDelegatingCompressorStream extends CompressorStream {
    public OutputStreamDelegatingCompressorStream(final OutputStream out) throws IOException {
        super(out);
    }

    @Override
    public void write(final int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(final byte[] data, final int offset, final int length) throws IOException {
        out.write(data, offset, length);
    }

    @Override
    public void close() throws IOException {
        flush();
        out.close();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void finish() throws IOException {
    }

    @Override
    public void resetState() throws IOException {
    }
}