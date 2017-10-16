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

package com.fluenda.hadoop.io.compress;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ZstandardCodec extends Configured implements CompressionCodec {

    @Override
    public CompressionOutputStream createOutputStream(OutputStream outputStream) throws IOException {

    }

    @Override
    public CompressionOutputStream createOutputStream(OutputStream outputStream, Compressor compressor) throws IOException {

    }

    @Override
    public Class<? extends Compressor> getCompressorType() {

    }

    @Override
    public Compressor createCompressor() {

    }

    @Override
    public CompressionInputStream createInputStream(InputStream inputStream) throws IOException {

    }

    @Override
    public CompressionInputStream createInputStream(InputStream inputStream, Decompressor decompressor) throws IOException {

    }

    @Override
    public Class<? extends Decompressor> getDecompressorType() {

    }

    @Override
    public Decompressor createDecompressor() {

    }

    @Override
    public String getDefaultExtension() {

    }
}