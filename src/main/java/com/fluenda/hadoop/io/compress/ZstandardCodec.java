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

import com.fluenda.hadoop.io.compress.zstandard.InputStreamDelegatingDecompressorStream;
import com.fluenda.hadoop.io.compress.zstandard.OutputStreamDelegatingCompressorStream;
import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ZstandardCodec implements Configurable, CompressionCodec {

    private static final Log LOG = LogFactory.getLog(ZstandardCodec.class);
    private Configuration conf;

    public ZstandardCodec() {
        this(new Configuration());
    }

    public ZstandardCodec(final Configuration conf) {
        this.conf = conf;
    }

    @Override
    public CompressionOutputStream createOutputStream(OutputStream out) throws IOException {
        return (createOutputStream(out, null));
    }

    @Override
    public CompressionOutputStream createOutputStream(OutputStream out, Compressor compressor) throws IOException {
        LOG.info("Creating compressor stream");
        return new OutputStreamDelegatingCompressorStream(new ZstdOutputStream(out));
    }

    @Override
    public Class<? extends Compressor> getCompressorType() {
        return null;

    }

    @Override
    public Compressor createCompressor() {
        return null;

    }

    @Override
    public CompressionInputStream createInputStream(InputStream in) throws IOException {
        return createInputStream(in, null);
    }

    @Override
    public CompressionInputStream createInputStream(InputStream in, Decompressor decompressor) throws IOException {
        LOG.debug("Creating compressor stream");
        return new InputStreamDelegatingDecompressorStream(new ZstdInputStream(in));
    }

    @Override
    public Class<? extends Decompressor> getDecompressorType() {
        return null;

    }

    @Override
    public Decompressor createDecompressor() {
        return null;

    }

    @Override
    public String getDefaultExtension() {
        return ".zst";

    }

    @Override
    public void setConf(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }
}